package work.mathwiki.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;

import work.mathwiki.R;

public class ExtendedAlertDialog extends AppCompatDialog {


    public ExtendedAlertDialog(Context context) {
        super(context);
    }

    public ExtendedAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_overlay);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }
}
