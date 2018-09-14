package work.mathwiki.updater;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.util.JsonReader;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import work.mathwiki.MainActivity;
import work.mathwiki.R;
import work.mathwiki.core.logger.Logger;
import work.mathwiki.core.network.AppUpdateInfo;
import work.mathwiki.core.network.HttpResponseInfo;
import work.mathwiki.utility.NotificationUtility;

/**
 *  App 更新管理器
 *  ===============================
 *  checkUpdate() ： 检查是否有更新
 *
 * Created by Dexfire on 2018/8/13 0013.
 */

public class AppUpdateManager {

    private static AppUpdateManager instance;
    private static Logger log = Logger.build("AppUpdateManager.java :");
    private ArrayList<Integer> ignoredUpdateVersion;
    // 空构造函数
    private AppUpdateManager(){
        ignoredUpdateVersion = new ArrayList<>();
    };

    // 单例模式
    public static AppUpdateManager getInstance(){
        if(instance==null){
            synchronized (AppUpdateManager.class){
                if(instance==null) instance=new AppUpdateManager();
            }
        }
        return instance;
    }

    public void checkUpdates(Context context){
        // TODO: 未实现
        //        if(checkAppUpdate()){
//
//        }
        log.ii("Checking Updates");
        checkAppUpdate(context,(has_update,updateInfo,responseInfo)->{
            log.i("get update info okay...");
            if(has_update){
                if(AppUpdateManager.getInstance().ifItNewVersion(context,updateInfo)){
                    showAppUpdateDialog(context,updateInfo);
                }
            }else{
                log.e("更新错误： "+responseInfo.response_code+" "+responseInfo.response_message);
                // TODO: 分析错误原因
                switch (responseInfo.response_code){

                }

            }
        });
        // TODO: check
    }

    public void checkAppUpdate(Context context,AppUpdateCheckCallback callback){
        if(callback==null){
            log.e("检查更新时未设置回调函数！");
            return;
        }
        Runnable runnable = () -> {
            AppUpdateInfo updateInfo = null;
            HttpResponseInfo responseInfo = new HttpResponseInfo();
            boolean hasUpdate = false;
            try {
                URL url = new URL("https","api.github.com","/repos/dexfire/mathwiki/releases/latest");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if(Logger.DEBUG){
                    String info = Logger.toString(connection);
                }

                connection.connect();

                // Debug 信息

                if(Logger.DEBUG){
                    String info = Logger.toString(connection);
                }

                responseInfo.response_code = connection.getResponseCode();
                responseInfo.response_message = connection.getResponseMessage();

                if(responseInfo.response_code==HttpsURLConnection.HTTP_OK){
                    hasUpdate = true;
                    int len = connection.getContentLength();
                    String encording = connection.getContentEncoding();
                    String contentType = connection.getContentType();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
//                    StringBuilder sb = new StringBuilder();
//                    String line = reader.readLine();
//                    if (line!=null){
//                        sb.append(line).append("\n");
//                        line = reader.readLine();
//                    }
//                    Content  = sb.toString();
//                    if(Logger.DEBUG){
//                        log.i(Content);
//                    }
                    updateInfo = pharseUpdateJSON(reader);
                }else{
                    hasUpdate = false;
                    log.e("检查更新失败, " + responseInfo.response_code+" "+ connection.getResponseMessage());
                }
                boolean finalHasUpdate = hasUpdate;
                AppUpdateInfo finalUpdateInfo = updateInfo;
                MainActivity.getHandler().post(()->{
                    callback.onCheckResult(finalHasUpdate, finalUpdateInfo,responseInfo);
                });

            } catch (IOException e) {
                // IO错误来源于 Json时解析
                if(Logger.DEBUG)
                    e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }


    /***
     *  更新信息采用github api 格式
     *
     * @return update_info ， 当读取错误时结果未知
     */
    private @NonNull AppUpdateInfo pharseUpdateJSON(Reader reader) throws IOException {
        AppUpdateInfo info = new AppUpdateInfo();
        JsonReader jsr = new JsonReader(reader);
        jsr.beginObject();
        while(jsr.hasNext()){
            switch (jsr.nextName()){
                case "tag_name":
                    info.version_code = jsr.nextInt();
                    break;
                case "name":
                    info.version_name = jsr.nextString();
                    break;
                case "assets":
                    jsr.beginArray();
                    jsr.beginObject();
                    while(jsr.hasNext()){
                        if(jsr.nextName().equals("browser_download_url"))
                            info.download_url = jsr.nextString();
                        else
                            jsr.skipValue();
                    }
                    jsr.endObject();
                    jsr.endArray();
                    break;
                case "body":
                    info.desc = jsr.nextString();
                    break;
                default:
                    jsr.skipValue();
            }   // end switch
        }  // end while
        jsr.endObject();
        return info;
    }

    public boolean checkPluginsUpdate(){
        return true;
    }

    public boolean checkDataUpdate(){
        return true;
    }

    private void addVersionToIgnore(int version_code){
        // TODO: 未实现
    }

    private void ifVerionIgnored(){
        // TODO: 未实现
    }

    private void startDownloadInstallFromUrl(Context context,AppUpdateInfo updateInfo){
        // TODO: 未实现
        NotificationUtility.makeShortToast(context,"拼命下载更新中...");
    }

    private void showAppUpdateDialog(Context context, AppUpdateInfo info){
        AppCompatDialog dialog =  NotificationUtility.makeAppUpdateDialog(context,info);
        ((AppCompatTextView)dialog.findViewById(R.id.text)).setText(info.toString());
        View bt_ok = dialog.findViewById(R.id.button_ok);
        View bt_no = dialog.findViewById(R.id.button_no);
        View bt_nomore = dialog.findViewById(R.id.button_nomore);
        bt_ok.setOnClickListener(v -> {
            startDownloadInstallFromUrl(context,info);
            dialog.dismiss();
        });
        bt_no.setOnClickListener(v -> {
            dialog.dismiss();
        });
        bt_nomore.setOnClickListener(v -> {
            addVersionToIgnore(info.version_code);
            dialog.dismiss();
        });
        View close = dialog.findViewById(R.id.close);
        close.setOnClickListener(v->{
            dialog.dismiss();
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private boolean ifItNewVersion(Context context, AppUpdateInfo updateInfo){
        if (!ignoredUpdateVersion.contains(updateInfo.version_code)){
            PackageManager pm = context.getPackageManager();
            try {
                if(pm.getPackageInfo(context.getPackageName(),0).versionCode < updateInfo.version_code){
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e) {
                if(Logger.DEBUG)
                e.printStackTrace();
            }
        }
        // Debug 模式下，始终检查更新
        if(Logger.DEBUG) return true;
        return false;
    }

}
