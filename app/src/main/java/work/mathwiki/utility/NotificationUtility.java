package work.mathwiki.utility;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatDialog;
import android.widget.TextView;

import work.mathwiki.MainActivity;
import work.mathwiki.R;


public class NotificationUtility {
    private Context context;
    private static NotificationUtility ourInstance;
    private static final String NOTIFICATION_CHANNEL_ID = "mathwiki.work";
    private static final int NOTIFICATION_ID = 2018;
    public static NotificationUtility getInstance(Context context) {
        if(ourInstance==null){
            ourInstance = new NotificationUtility(context);
        }
        return ourInstance;
    }

    private NotificationUtility(Context context) {
        this.context = context;
    }

    private PendingIntent getIntent(){
        return PendingIntent.getActivity(context,0,new Intent(context, MainActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void makeStatusBarNotification(String title, String content,String subtext, @DrawableRes int icon){
        // TODO: not implemented function

        NotificationManager notificationManager =  (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title).
                setAutoCancel(true).
                setContentText(content).
                setWhen(System.currentTimeMillis()).
                setLargeIcon(BitmapFactory.decodeResource(context.getResources(),icon)).
                setSubText(subtext).
                setContentIntent(getIntent());
        Notification notification = builder.build();
        notificationManager.notify(2018,notification);
    }

    public void makeDialogNotification(String title, String subtitle, int type){
        AppCompatDialog dialog = new AppCompatDialog(context);
        dialog.setTitle(title);
        dialog.setContentView(android.R.layout.select_dialog_item);
        TextView tv = dialog.findViewById(android.R.id.text1);
        tv.setText(R.string.notification_test);
        dialog.setCancelable(true);
        dialog.setFeatureDrawable(AppCompatDialog.BUTTON_POSITIVE,context.getDrawable(R.drawable.ic_circle));
        dialog.setFeatureDrawable(AppCompatDialog.BUTTON_NEGATIVE,context.getDrawable(R.drawable.ic_clear));
        dialog.show();
    }
}
