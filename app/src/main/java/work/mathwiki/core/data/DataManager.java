package work.mathwiki.core.data;

import android.os.Environment;

import java.io.File;
import java.util.List;

import work.mathwiki.core.logger.Logger;

public class DataManager {

    private Logger log;
    private static final String File_Scheme = "file://";
    private static final String Product_Name = "Hyper-Math";
    private static final String Data_Storage_Path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +Product_Name;
    private static final String Version_Desc_FN = "version.json";
    private List<String> data;
    private static DataManager singleDataManager = new DataManager();

    private DataManager(){}

    public static DataManager getInstance(){
        return singleDataManager;
    }

    private boolean checkDAtaOkay(){
        File dataDir = new File(Data_Storage_Path);
        if(!dataDir.exists() || !dataDir.isDirectory()) return false;
        File versionDesc = new File(dataDir,Version_Desc_FN);
        if(versionDesc.exists() || versionDesc.isDirectory()) return false;
        // TODO : Json to process data
        return true;
    }

    public String getHomeUrl(){
        if(checkDAtaOkay()){
            return File_Scheme + Data_Storage_Path + File.separator + "index.html";
        }else{
            return File_Scheme + Data_Storage_Path+ File.separator + "404.html";
        }
    }

    public String getContextUrl(){
        if(checkDAtaOkay()){
            return File_Scheme + Data_Storage_Path+ File.separator + "context.html";
        }else{
            return File_Scheme + Data_Storage_Path+ File.separator + "404.html";
        }
    }

    public String getDataPath(){
        return Data_Storage_Path;
    }
}
