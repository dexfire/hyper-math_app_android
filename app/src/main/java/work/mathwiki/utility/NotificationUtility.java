package work.mathwiki.utility;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import work.mathwiki.MainActivity;
import work.mathwiki.R;
import work.mathwiki.core.network.AppUpdateInfo;
import work.mathwiki.views.StyledToast;


public class NotificationUtility {

    private static final String NOTIFICATION_CHANNEL_ID = "mathwiki.work";
    private static final int NOTIFICATION_ID = 2018;
    public static final int DIALOG_NOTIFICATION_PERMISSION_GRANT = 1;
    private static PendingIntent getPendingIntent(Context context){
        return PendingIntent.getActivity(context,0,new Intent(context, MainActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public static void makeStatusBarNotification(Context context,String title, String content,String subtext, @DrawableRes int icon){
        // TODO: not implemented function

        NotificationManager notificationManager =  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title).
                setAutoCancel(true).
                setContentText(content).
                setWhen(System.currentTimeMillis()).
                setLargeIcon(BitmapFactory.decodeResource(context.getResources(),icon)).
                setSubText(subtext).
                setContentIntent(getPendingIntent(context));
        Notification notification = builder.build();
        notificationManager.notify(2018,notification);
    }

    public static void makeDialogNotification(Context context,String title, String message){

        AppCompatDialog dialog = new AppCompatDialog(context);
        dialog.setTitle(title);
        AppCompatTextView text = new AppCompatTextView(context);
        text.setText(message);
        dialog.setContentView(text);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public static @NonNull AppCompatDialog makeAppUpdateDialog(Context context, AppUpdateInfo info){
        AppCompatDialog dialog = new AppCompatDialog(context);
        WindowManager wm = dialog.getWindow().getWindowManager();
        if(wm!=null){
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = (int)(wm.getDefaultDisplay().getWidth() * 0.90f);
            dialog.getWindow().setAttributes(lp);
        }
        dialog.setContentView(R.layout.dialog_app_update);
        try {
            ((AppCompatTextView)dialog.findViewById(R.id.title)).setText("更新 " + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName + " -> " + info.version_name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return dialog;
    }

    public static void makeShortToast(Context context,String text){
        StyledToast.makeText(context,StyledToast.TYPE_INFO_GREEN,text,Toast.LENGTH_SHORT).show();
    }
    public static void makeLongToast(Context context,String text){
        StyledToast.makeText(context,StyledToast.TYPE_INFO_BLUE,text,Toast.LENGTH_LONG).show();
    }
}
