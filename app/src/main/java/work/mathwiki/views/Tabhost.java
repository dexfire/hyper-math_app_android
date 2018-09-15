package work.mathwiki.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import work.mathwiki.R;
import java.util.ArrayList;
import java.util.List;

/***
 * 自定义实现的Tabhost类
 * 提供的接口：
 *
 *
 *
 */

public class Tabhost extends LinearLayout {

    private int mSelectedTabIndex = 0;
    private int mSelectedTabId = 0;
    private List<Tab> mTabsList;
    private LinearLayout mTabsContainer;
    private View.OnClickListener mTabsClickedCallback;
    private OnTabSelectedStateChangedListener mTabsStateChangedCallback;

    // 初始化Tabs的工具类
    public class Tab {
        int tabViewGroupId;
        String title;

        @DrawableRes
        int iconId;
        Drawable icon;
    }

    public interface OnTabSelectedStateChangedListener{
        void onTabSelected(int indexofChild, View v);
        void onTabSelectedClickedAgain(int indexofChild, View v);
    }

    public Tabhost(@NonNull Context context) {
        super(context);
    }

    public Tabhost(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Tabhost(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTabsList = new ArrayList<>();
        LayoutInflater.from(context).inflate(R.layout.layout_tab_host_container,this,true);
        mTabsContainer = findViewById(R.id.tab_host_container);
        mTabsClickedCallback = v -> {
            int index = mTabsContainer.indexOfChild(v);
            if(mSelectedTabIndex != index){     //发生状态切换
                setSelectedState(Tabhost.this.mSelectedTabIndex,false); //调用
                setSelectedState(index,true);
                Tabhost.this.mSelectedTabIndex = index;
                Tabhost.this.mSelectedTabId = v.getId();
                if(Tabhost.this.mTabsStateChangedCallback!=null){
                    Tabhost.this.mTabsStateChangedCallback.onTabSelected(index,v);
                }
            }else{      //仅仅是再次单击
                setSelectedState(index,true);
                Tabhost.this.mSelectedTabIndex = index;
                Tabhost.this.mSelectedTabId = v.getId();
                if(Tabhost.this.mTabsStateChangedCallback!=null){
                    Tabhost.this.mTabsStateChangedCallback.onTabSelectedClickedAgain(index,v);
                }
            }
        };
    }
    private void setSelectedState(int index,boolean state){

    }

    private void updateTabSelectedState(){

    }

    private View makeTabView(Tab tab,int tabid){
        ViewGroup vg = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.list_item_tab_host,this,false);
        ImageView icon = vg.findViewById(R.id.tab_icon);
        TextView title = vg.findViewById(R.id.tab_title);
        // Init View Appearance
        title.setText(tab.title);
        if(tab.icon!=null){
            icon.setImageDrawable(tab.icon);
        }else{
            icon.setImageResource(tab.iconId);
        }
        // setup viewgroup attributes
        // TODO: 这里width设置为0真的没问题吗？
        vg.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1.0f));
        tabid= tab.tabViewGroupId!=0? tab.tabViewGroupId:tabid;
        vg.setId(tabid);
        vg.setOnClickListener(mTabsClickedCallback);
        // TODO: theme stylize here
        // using android.content.res.ColorStateList
        return vg;
    }

    // region public initializing func

    public void setTabs(List<Tab> list){
        // this.mTabsList = list; 这种写法似乎有问题
        // 更新数据
        mTabsList.clear();
        mTabsList.addAll(list);
        // 更新view
        mTabsContainer.removeAllViews();
        for(int i=1;i<=mTabsList.size();i++){
            mTabsContainer.addView(makeTabView(list.get(i),i),i);
        }
    }


    public void setOnTabSelectedStateChangedListerer(OnTabSelectedStateChangedListener listener){
        this.mTabsStateChangedCallback = listener;
    }
    // endregion

    // region public state operations
    public void setCurrentSelectdTab(int tabid,boolean selected){
        if(mTabsList!=null && tabid>-1 && tabid < getTabsCount()){
            View v = getTabViewAt(tabid);
            if(mSelectedTabIndex!=tabid){
                setSelectedState(mSelectedTabIndex,false);
                setSelectedState(tabid,true);
                mSelectedTabIndex = tabid;
                mSelectedTabId = v.getId();
                if(selected && mTabsStateChangedCallback!=null){
                    mTabsStateChangedCallback.onTabSelected(tabid,v);
                }
            }
        }
    }
    // endregion

    // region public utility
    public int getTabsCount(){
        return mTabsList.size();
    }

    public ViewGroup getTabViewAt(int index){
        return (ViewGroup) mTabsContainer.getChildAt(index);
    }
}
