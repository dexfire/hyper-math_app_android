package work.mathwiki.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import work.mathwiki.core.logger.Logger;

public class NetworkUtil {

    public interface ReqProgressCallBack<T> {
        void onProgress(long total, long current);
    }

    public static int getNetworkState(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo active = manager.getActiveNetworkInfo();
            if(wifi.isAvailable()){
                return 0;
            }else if(mobile.isConnected()){
                return 1;
            }else if(active.isConnected()){
                return 2;
            }else{
                return -1;
            }
        }else{
            Logger.se("NetworkUtil: Failed to get ConnectivityManager instance...");
            return -2;
        }
    }

    public <T> void download(String url, final String destFolder, final ReqProgressCallBack<T> callBack){
        final String fileName = MD5.encode(url);
        final File file = new File (destFolder,fileName);
        if (file.exists()){
            // TODO: 结束下载，校验文件
            return;
        }
        final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url(url).build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Logger.se(e.toString());
                //TODO: Fail to download
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                InputStream is = null;
                byte[] buff = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body() != null ? response.body().contentLength() : 0;
                    Logger.si("total --> "+ total);
                    long current = 0;
                    if (response.body() != null) {
                        is = response.body().byteStream();
                        fos = new FileOutputStream(file);
                        while((len=is.read(buff))!=-1){
                            current += len;
                            fos.write(buff,0,len);
                            Logger.si("current --> "+current);
                            // TODO: progress update progressCallBack(total,current,callback)
                        }
                        fos.flush();
                        // TODO Success to download callback successCallback((T)file,callback)
                    }else{  // response.body() == null
                        Logger.se("response has a NULL body");
                    }
                } catch (IOException e){
                    Logger.se(e.toString());
                    // TODO fail to download failedCallback("下载失败",callback)
                } finally {
                    if (is != null) is.close();
                    if (fos!=null) fos.close();
                }
            }
        });
    }
}
