package work.mathwiki.utility;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import retrofit2.Retrofit;


public class IDownloadManager {
    private static final String baseUrl = "http://api.github.com/repos/dexfire/mathwiki/releases/latest";
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

    private static void downloadOverHttps(){
        // on okhttp: create a named Runnable
        new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL("https","api.github.com","/repos/dexfire/mathwiki/releases/latest");
                    HttpsURLConnection connection = new HttpsURLConnection(url) {
                        @Override
                        public String getCipherSuite() {
                            return null;
                        }

                        @Override
                        public Certificate[] getLocalCertificates() {
                            return new Certificate[0];
                        }

                        @Override
                        public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
                            return new Certificate[0];
                        }

                        @Override
                        public void disconnect() {

                        }

                        @Override
                        public boolean usingProxy() {
                            return false;
                        }

                        @Override
                        public void connect() throws IOException {

                        }
                    };

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        };




    }

    public static void downloadWithSystemDM(String link){

        Uri uri =  Uri.parse(link);
//        if(uri.isHierarchical())
//        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        dm.enqueue(new DownloadManager.Request());
        //TODO : not implemented
    }
}
