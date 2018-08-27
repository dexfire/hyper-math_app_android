package work.mathwiki.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LoadingProgressBar extends View{
    private int sequence;
    public LoadingProgressBar(Context context){
        super(context);
    }

    public LoadingProgressBar(Context context, AttributeSet as){
        super(context,as);
    }

    public LoadingProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadingProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void record() {
        sequence++;
        Thread.currentThread().getStackTrace();
    }
}
