package work.mathwiki.base.activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import work.mathwiki.R;
import work.mathwiki.core.logger.Logger;
import work.mathwiki.views.TopBarView;

/**
 * Created by Dexfire on 2018/8/29 0029.
 */

public class ExtendedActivityWithTopBar extends ExtendedActivity {
    private TopBarView mTopBarView;


    @Override
    public void setContentView(int layoutResID) {
        RelativeLayout root = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_root_layout,null);
        mTopBarView = root.findViewById(R.id.topbar);
        initTopBarView();
        getLayoutInflater().inflate(layoutResID,root.findViewById(R.id.base_root_container));
        super.setContentView(root);
    }

    @Override
    public void setContentView(View view) {
        RelativeLayout root = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_root_layout,null);
        mTopBarView = root.findViewById(R.id.topbar);
        initTopBarView();
        ((ViewGroup)(root.findViewById(R.id.base_root_container))).addView(view);
        super.setContentView(root);
    }

    private void initTopBarView() {
        Logger.sd("Init TopBarView Internal");

    }
}
