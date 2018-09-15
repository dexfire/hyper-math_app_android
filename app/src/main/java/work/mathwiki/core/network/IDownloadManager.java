package work.mathwiki.core.network;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Debug;
import android.os.Environment;
import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import retrofit2.Retrofit;
import work.mathwiki.MainActivity;
import work.mathwiki.core.logger.Logger;
import work.mathwiki.utility.ClipBoardUtility;
import work.mathwiki.utility.NotificationUtility;


public class IDownloadManager {
    private static final String baseUrl = "http://api.github.com/repos/dexfire/mathwiki/releases/latest";
    private static Logger log = Logger.build("IDownloadManager");
    private static IDownloadManager singleInstance = null;
    private static Context context;
    public static IDownloadManager getInstance(Context context) {
        if(singleInstance==null){
            IDownloadManager.context = context;
            singleInstance = new IDownloadManager(context);
        }
        return singleInstance;
    }

    private IDownloadManager(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
    }

    public static void downloadOverHttps(Context context){
        // on okhttp: create a named Runnable
//
//        Runnable runnable = new Runnable(){
//            @Override
//            public void run() {
//                int HttpResponse = 0;
//                String Content = "";
//                try {
//                    URL url = new URL("https","api.github.com","/repos/dexfire/mathwiki/releases/latest");
//                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//
//                    // Debug 信息
//                    if(Logger.DEBUG){
//                        String info = Logger.toString(connection);
//                        MainActivity.getHandler().post(() -> {
//                            log.ii_toast(context,info);
//                            ClipBoardUtility.copy2cb(context,"github api info",info);
//                        });
//                    }
//
//                    HttpResponse = connection.getResponseCode();
//                    if(HttpResponse==HttpsURLConnection.HTTP_OK){
//                        int len = connection.getContentLength();
//                        String encording = connection.getContentEncoding();
//                        String contentType = connection.getContentType();
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
//                        StringBuilder sb = new StringBuilder();
//                        String line = reader.readLine();
//                        if (line!=null){
//                            sb.append(line).append("\n");
//                            line = reader.readLine();
//                        }
//                        Content  = sb.toString();
//                        if(Logger.DEBUG){
//                            String finalContent = Content;
//                            MainActivity.getHandler().post(() -> {
//                                NotificationUtility.makeLongToast(context, finalContent);
//                            });
//                            ClipBoardUtility.copy2cb(context,"github api result",Content);
//                            log.i(Content);
//                        }
//                        AppUpdateInfo info = new AppUpdateInfo();
//                        JsonReader jsr = new JsonReader(new StringReader(Content));
//                        jsr.beginObject();
//                        while(jsr.hasNext()){
//                            switch (jsr.nextName()){
//                                case "tag_name":
//                                    info.version_code = jsr.nextInt();
//                                    break;
//                                case "name":
//                                    info.version_name = jsr.nextString();
//                                    break;
//                                case "assets":
//                                    jsr.beginArray();
//                                    jsr.beginObject();
//                                    while(jsr.hasNext()){
//                                        if(jsr.nextName().equals("browser_download_url"))
//                                            info.download_url = jsr.nextString();
//                                         else
//                                             jsr.skipValue();
//                                    }
//                                    jsr.endObject();
//                                    jsr.endArray();
//                                    break;
//                                case "body":
//                                    info.desc = jsr.nextString();
//                                    break;
//                                default:
//                                    jsr.skipValue();
//                            }   // end switch
//                        }  // end while
//                        jsr.endObject();
//                        MainActivity.getHandler().post(() -> {
//                            NotificationUtility.makeAppUpdateDialog(context,info);
//                        });
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        new Thread(runnable).start();
    }

    public static void downloadWithSystemDM(String link){

        Uri uri =  Uri.parse(link);

//        if(uri.isHierarchical())
//        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        dm.enqueue(new DownloadManager.Request());
        //TODO : not implemented
    }
}
