package work.mathwiki.core.viewmodel;

/***
 *  用于管理 MainActivity 中的 Container 展示的 View视图
 *  需要实现
 *      - Views 的复用
 *          -  持有 View 的引用来防止 GC 回收
 *          -  添加 ViewGroup 由本类托管
 *          -  通过 tag 找到对应的 ViewGroup
 *          -  处理 View 展示时的还原状态操作
 *
 */

import android.view.ViewGroup;

import java.util.HashMap;

import work.mathwiki.core.logger.Logger;

public class ContentManager {
    private static Logger log = Logger.build("ContentManager");

    public interface ContentCallback{
        void onInit(ContentViewsEnum key, ViewGroup view);
        void onShow(ContentViewsEnum key, ViewGroup view);
        void onHide(ContentViewsEnum key, ViewGroup view);
        boolean onBackPressed();
    }

    private static ContentViewsEnum mCurrent;

    private static HashMap<ContentViewsEnum,ViewGroup> mViewGroups = new HashMap<>(3);
    private static HashMap<ContentViewsEnum,ContentCallback> mCallbacks = new HashMap<>(3);

    public static ContentCallback getCurrentCallback(){
        return mCallbacks.get(mCurrent);
    }

    public static void showContent(ContentViewsEnum key, ViewGroup container){
        if(mViewGroups.containsKey(key) || container!=null || mViewGroups.get(key)!=null){
            log.ii("显示ViewGroup : "+ key.name());
            if (container != null) {
                container.removeAllViews();
                container.addView(getView(key),-1,-1);
            }else{
                log.e("The ContainerView is null! This is abnormal.");
            }
            // 旧 View 的 onHide 事件
            if(mCallbacks.get(mCurrent)!=null){
                mCallbacks.get(mCurrent).onHide(key,getView(mCurrent));
            }
            // 当前view的 onShow 事件
            if(mCallbacks.get(key)!=null){
                mCallbacks.get(key).onShow(key,container);
            }
            mCurrent = key;
        }
    }

    public static ViewGroup getCurrentView(){
        return getView(mCurrent);
    }

    public static ContentViewsEnum getCurrent(){
        return mCurrent;
    }

    public static void addView(ContentViewsEnum key, ViewGroup vg){
        mViewGroups.put(key,vg);
    }

    public static void addView(ContentViewsEnum key, ViewGroup vg, ContentCallback cb){
        mViewGroups.put(key,vg);
        mCallbacks.put(key,cb);
        if(cb!=null)
            cb.onInit(key,vg);
    }

    public static ViewGroup getView(ContentViewsEnum key){
        return mViewGroups.get(key);
    }

}
