package work.mathwiki.views;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import work.mathwiki.R;

public class StyledToast {

    public static final int TYPE_INFO_GREEN = 0;
    public static final int TYPE_INFO_BLUE = 1;
    public static final int TYPE_WARNING = 2;
    public static final int TYPE_ERROR = 3;

    // 空 private 构造函数，防止被实例化
    private StyledToast(){}

    public static Toast makeText(Context context,int type, @StringRes int resId, int durationMs){
        Toast toast = new Toast(context);
        int layoutId = R.layout.toast_info_green;
        switch (type){
            case TYPE_INFO_GREEN:
                // Do nothing
                break;
            case TYPE_INFO_BLUE:
                layoutId = R.layout.toast_info_blue;
                break;
            case TYPE_WARNING:

                break;
            case TYPE_ERROR:

                break;
        }
        View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutId,null);
        TextView text = view.findViewById(R.id.message);
        text.setText(resId);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER_HORIZONTAL |  Gravity.BOTTOM ,0,-500);
        toast.setDuration(durationMs);
        return toast;
    }

    public static Toast makeText(Context context,int type, CharSequence str, int durationMs){
        Toast toast = new Toast(context);
        int layoutId = R.layout.toast_info_green;
        switch (type){
            case TYPE_INFO_GREEN:
                // Do nothing
                break;
            case TYPE_INFO_BLUE:
                layoutId = R.layout.toast_info_blue;
                break;
            case TYPE_WARNING:

                break;
            case TYPE_ERROR:

                break;
        }
        View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutId,null);
        TextView text = view.findViewById(R.id.message);
        text.setText(str);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER_HORIZONTAL ,0,-500);
        toast.setDuration(durationMs);
        return toast;
    }


}
