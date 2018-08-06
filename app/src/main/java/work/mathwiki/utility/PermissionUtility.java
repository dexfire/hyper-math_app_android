package work.mathwiki.utility;

import android.content.Context;
import android.support.v4.content.PermissionChecker;

public class PermissionUtility {

    public boolean dialogPermission(Context context,String permission){
        //TODO: not implemented func
        return false;
    }

    public boolean checkPermission(Context context,String permission){
        int result = PermissionChecker.checkSelfPermission(context,permission);
        if(result!=PermissionChecker.PERMISSION_GRANTED){
            return dialogPermission(context,permission);
        }
        return true;
    }
}
