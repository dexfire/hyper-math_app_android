package work.mathwiki.utility;

import android.os.Environment;

import java.io.File;
import java.util.List;

public class DataManager {

    public static final String Product_Name = "Hyper-Math";
    public static final String Data_Storage_Path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+Product_Name;
    private static final String Version_Desc_FN = "version.json";
    private List<String> data;
    private DataManager(){}

    private static DataManager singleDataManager = new DataManager();

    public static DataManager getInstance(){
        return singleDataManager;
    }

    public boolean checkDAtaOkay(){
        File dataDir = new File(Data_Storage_Path);
        if(dataDir.exists() || !dataDir.isDirectory()) return false;
        File versionDesc = new File(dataDir,Version_Desc_FN);
        if(versionDesc.exists() || versionDesc.isDirectory()) return false;
        // TODO : Json to process data
        return false;
    }
}
