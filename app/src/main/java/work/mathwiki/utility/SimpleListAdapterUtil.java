package work.mathwiki.utility;

import java.util.HashMap;

public class SimpleListAdapterUtil {

    public static HashMap<String,Object> makeMap(String[] key,Object[] obj){
        HashMap<String,Object> map = new HashMap<>();
        if (obj == null || key == null) return map;
        int len = key.length>obj.length? obj.length:key.length;
        for(int i=0;i<len;i++){
            map.put(key[i],obj[i]);
        }
        return map;
    }

}
