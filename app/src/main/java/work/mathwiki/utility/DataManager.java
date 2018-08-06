package work.mathwiki.utility;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Dexfire on 2018/8/6 0006.
 */

public class DataManager {

    private DataManager(){}

    private List<String> data;

    private static DataManager singleDataManager = new DataManager();

    public static DataManager getInstance(){
        return singleDataManager;
    }
}
