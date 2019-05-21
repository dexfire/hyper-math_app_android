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
                layoutId = R.layout.toast_info_green;
                break;
            case TYPE_INFO_BLUE:
                layoutId = R.layout.toast_info_blue;
                break;
            case TYPE_WARNING:
                layoutId = R.layout.toast_warning;
                break;
            case TYPE_ERROR:
                layoutId = R.layout.toast_error;
                break;
        }
        View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutId,null);
        TextView text = view.findViewById(R.id.message);
        text.setText(resId);
        toast.setView(view);
//        toast.setGravity(0 ,0,-200);
        toast.setDuration(durationMs);
        return toast;
    }

    public static Toast makeText(Context context,int type, CharSequence str, int durationMs){
        Toast toast = new Toast(context);
        int layoutId = R.layout.toast_info_green;
        switch (type){
            case TYPE_INFO_GREEN:
                // Do nothing
                layoutId = R.layout.toast_info_green;
                break;
            case TYPE_INFO_BLUE:
                layoutId = R.layout.toast_info_blue;
                break;
            case TYPE_WARNING:
                layoutId = R.layout.toast_warning;
                break;
            case TYPE_ERROR:
                layoutId = R.layout.toast_error;
                break;
        }
        View view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutId,null);
        TextView text = null;
        if(view !=null) text = view.findViewById(R.id.message);
        if(text !=null) text.setText(str);
        toast.setView(view);
//        toast.setGravity(Gravity.CENTER_HORIZONTAL |  Gravity.BOTTOM ,0,-200);
        toast.setDuration(durationMs);
        return toast;
    }


}
