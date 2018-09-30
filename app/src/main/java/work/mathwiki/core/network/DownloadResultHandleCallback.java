package work.mathwiki.core.network;


public interface DownloadResultHandleCallback {
    int RESULT_OK = 0;
    int RESULT_FAILED = -1;
    int TYPE_APK = 1;
    int TYPE_DATA_PACKAGE = 2;
    int TYPE_PLUGIN = 3;
    void onDownloadResult(int resultCode,String filePath);
}
