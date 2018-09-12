package work.mathwiki.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import work.mathwiki.MainActivity;
import work.mathwiki.R;


public class NotificationUtility {

    private static final String NOTIFICATION_CHANNEL_ID = "mathwiki.work";
    private static final int NOTIFICATION_ID = 2018;
    public static final int DIALOG_NOTIFICATION_PERMISSION_GRANT = 1;
    private PendingIntent getPendingIntent(Context context){
        return PendingIntent.getActivity(context,0,new Intent(context, MainActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void makeStatusBarNotification(Context context,String title, String content,String subtext, @DrawableRes int icon){
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

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.setTitle(title).setMessage(message).setPositiveButton("确定", (dialog1, which) -> {
            dialog1.dismiss();
        }).show();
    }

    public static void makeShortToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
    public static void makeLongToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
