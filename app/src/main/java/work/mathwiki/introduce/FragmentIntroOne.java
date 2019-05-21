package work.mathwiki.introduce;

import butterknife.BindView;
import work.mathwiki.R;
import work.mathwiki.base.fragments.BaseFragment;
import work.mathwiki.utility.media.Util;
import work.mathwiki.views.RatioLayout;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * 介绍页1
 * Created by huanghaibin on 2017/11/24.
 */

public class FragmentIntroOne extends BaseFragment {

    @BindView(R.id.ll_logo)
    LinearLayout mLinearLogo;
    @BindView(R.id.ratioLayout)
    RatioLayout mRatioLayout;

    static FragmentIntroOne newInstance() {
        return new FragmentIntroOne();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_intro2;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mLinearLogo.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLinearLogo.getLayoutParams();
                params.setMargins(0, (Util.getScreenHeight(mContext) - mRatioLayout.getRatioHeight()) / 4, 0, 0);
                mLinearLogo.setLayoutParams(params);
            }
        });
    }
}
