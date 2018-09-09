package work.mathwiki.views;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.util.AttributeSet;

public class MarkdownEditor extends AppCompatEditText {
    public MarkdownEditor(Context context) {
        super(context,null);
    }

    public MarkdownEditor(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MarkdownEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getCursorPosition(){
        return 0;
    }
}
