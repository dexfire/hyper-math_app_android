/* The MIT License (MIT)
 * Copyright (c) 2018 OSChina.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package work.mathwiki.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 涟漪View
 * Created by thanatosx on 2016/11/15.
 */

public class RippleIntroView extends RelativeLayout implements Runnable {

    private int mMaxRadius = 70;
    private int mInterval = 20;
    private int count = 0;

    private Bitmap mCacheBitmap;
    private Paint mRipplePaint;
    private Paint mCirclePaint;
    private Path mArcPath;

    public RippleIntroView(Context context) {
        this(context, null);
    }

    public RippleIntroView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleIntroView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRipplePaint = new Paint();
        mRipplePaint.setAntiAlias(true);
        mRipplePaint.setStyle(Paint.Style.STROKE);
        mRipplePaint.setColor(Color.WHITE);
        mRipplePaint.setStrokeWidth(2.f);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.WHITE);

        mArcPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mCacheBitmap != null) {
            mCacheBitmap.recycle();
            mCacheBitmap = null;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 我知道直接getChildAt(int)很挫，但是我就是要这么简单粗暴！
        View mPlusChild = getChildAt(0);
        View mRefsChild = getChildAt(1);
        if (mPlusChild == null || mRefsChild == null) return;

        final int pw = mPlusChild.getWidth();
        final int ph = mPlusChild.getHeight();

        final int fw = mRefsChild.getWidth();
        final int fh = mRefsChild.getHeight();

        if (pw == 0 || ph == 0) return;

        final float px = mPlusChild.getX() + pw / 2;
        final float py = mPlusChild.getY() + ph / 2;
        final float fx = mRefsChild.getX();
        final float fy = mRefsChild.getY();
        final int rw = pw / 2;
        final int rh = ph / 2;

        if (mCacheBitmap == null) {
            mCacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas cv = new Canvas(mCacheBitmap);
            super.onDraw(cv);

            mArcPath.reset();
            mArcPath.moveTo(px, py + rh + mInterval);
            mArcPath.quadTo(px, fy - mInterval, fx + fw * 0.618f, fy - mInterval);
            mRipplePaint.setAlpha(255);
            cv.drawPath(mArcPath, mRipplePaint);
            cv.drawCircle(px, py + rh + mInterval, 6, mCirclePaint);
        }
        canvas.drawBitmap(mCacheBitmap, 0, 0, mCirclePaint);

        int save = canvas.save();
        for (int step = count; step <= mMaxRadius; step += mInterval) {
            mRipplePaint.setAlpha(255 * (mMaxRadius - step) / mMaxRadius);
            canvas.drawCircle(px, py, (float) (rw + step), mRipplePaint);
        }
        canvas.restoreToCount(save);
        postDelayed(this, 80);
    }

    @Override
    public void run() {
        removeCallbacks(this);
        count += 2;
        count %= mInterval;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mCacheBitmap != null) {
            mCacheBitmap.recycle();
            mCacheBitmap = null;
        }
    }
}
