package work.mathwiki.core.network;

import android.support.annotation.NonNull;

public class AppUpdateInfo {
    public int version_code = 0;
    @NonNull public String version_name ="";
    @NonNull public String desc = "";
    @NonNull public String download_url = "";

    @Override
    public String toString(){
        return "新版本：\n" + version_name + "\n" +
                "版本代号:" + version_code + "\n" +
                "更新地址：" + download_url + "\n" +
                "更新日志：\n" + desc;
    }
}
