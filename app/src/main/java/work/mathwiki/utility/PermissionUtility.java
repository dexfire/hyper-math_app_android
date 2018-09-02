package work.mathwiki.utility;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;

import work.mathwiki.R;

public class PermissionUtility {

    private static final String[] permissions = {
            //Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    public static void checkAllNeededPermissions(Activity activity){
        String[] notes = activity.getResources().getStringArray(R.array.permissions_notation);
        for(int i=0;i<permissions.length;i++){
//            if(i==0)//checkPermission(activity,permissions[i],notes[i]);
//                else
            checkPermission(activity,permissions[i],notes[i]);
        }

    }

    private static boolean dialogPermission(Activity activity, String permission){
        ActivityCompat.requestPermissions(activity,permissions, PermissionChecker.PERMISSION_GRANTED);
        return false;
    }

    public static boolean checkPermission(Activity activity,String permission,String note){
        int result = PermissionChecker.checkSelfPermission(activity,permission);
        if(result!=PermissionChecker.PERMISSION_GRANTED){
            NotificationUtility.makeDialogNotification(activity,"权限请求",note);
            return dialogPermission(activity,permission);
        }
        return true;
    }
}
