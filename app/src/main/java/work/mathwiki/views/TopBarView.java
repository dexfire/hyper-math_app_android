package work.mathwiki.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import work.mathwiki.R;

/**
 * Created by Dexfire on 2018/8/29 0029.
 */

public class TopBarView extends RelativeLayout implements View.OnClickListener {
    private RelativeLayout topbar;
    private RelativeLayout leftContainer;
    private RelativeLayout rightContainer;
    private TextView titletext;
    private ImageView leftIcon;
    private ImageView rightIcon1;
    private ImageView rightIcon2;
    private TextView rightDot1;
    private TextView rightDot2;
    private Context mContext;

    public interface TopBarCallBacks{
        void onLeftIconClick();
        void onRightIcon1Click();
        void onRightIcon2Click();
        void onTitleClick();
    }

    public TopBarView(Context context){
        super(context,null);
    }

    public TopBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        updateTopBarView();
    }

    private void initView() {
        topbar = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_custom_topbar,null);
        leftContainer = topbar.findViewById(R.id.topbar_left_container);
        rightContainer = topbar.findViewById(R.id.topbar_right_container);

        titletext = topbar.findViewById(R.id.title_text);
        leftIcon = topbar.findViewById(R.id.topbar_left_Icon);
        rightIcon1 = topbar.findViewById(R.id.topbar_right_Icon1);

        leftContainer.setOnClickListener(this);

    }



    private void updateTopBarView() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topbar_left_container:

        }
    }


}
