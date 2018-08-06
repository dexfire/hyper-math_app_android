package work.mathwiki.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

public class ThemedAlertDialog extends AlertDialog {


    public ThemedAlertDialog(@NonNull Context context) {
        super(context);
    }

    public ThemedAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    // 这个才是 baseConstructor ， 重写这一个就好
    public ThemedAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }
}
