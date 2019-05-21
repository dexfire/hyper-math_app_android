package work.mathwiki.base.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.Serializable;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import work.mathwiki.core.logger.Logger;
import work.mathwiki.utility.ImageLoader;

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected View mRoot;
    protected Bundle mBundle;
    private RequestManager mImgLoader;
    private Unbinder mUnbinder;
    private boolean isDestoryed;

    protected LayoutInflater mInflater;

    protected abstract int getLayoutId();

    protected void onBindViewBefore(View root) {
        // ...
    }


    protected void initBundle(Bundle bundle) {
        // ...
    }

    protected void initWidget(View root) {
        // ...
    }

    protected void initData() {
        // ...
    }

    protected <T extends View> T findView(int viewId) {
        return (T) mRoot.findViewById(viewId);
    }

    protected <T extends Serializable> T getBundleSerializable(String key) {
        if (mBundle == null) {
            return null;
        }
        return (T) mBundle.getSerializable(key);
    }

    /**
     * 获取一个图片加载管理器
     *
     * @return RequestManager
     */
    public synchronized RequestManager getImgLoader() {
        if (mImgLoader == null)
            mImgLoader = Glide.with(this);
        return mImgLoader;
    }

    /***
     * 从网络中加载数据
     *
     * @param viewId   view的id
     * @param imageUrl 图片地址
     */
    protected void setImageFromNet(int viewId, String imageUrl) {
        setImageFromNet(viewId, imageUrl, 0);
    }

    /***
     * 从网络中加载数据
     *
     * @param viewId      view的id
     * @param imageUrl    图片地址
     * @param placeholder 图片地址为空时的资源
     */
    protected void setImageFromNet(int viewId, String imageUrl, int placeholder) {
        ImageView imageView = findView(viewId);
        setImageFromNet(imageView, imageUrl, placeholder);
    }

    /***
     * 从网络中加载数据
     *
     * @param imageView imageView
     * @param imageUrl  图片地址
     */
    protected void setImageFromNet(ImageView imageView, String imageUrl) {
        setImageFromNet(imageView, imageUrl, 0);
    }


    /***
     * 从网络中加载数据
     *
     * @param imageView   imageView
     * @param imageUrl    图片地址
     * @param placeholder 图片地址为空时的资源
     */
    protected void setImageFromNet(ImageView imageView, String imageUrl, int placeholder) {
        ImageLoader.loadImage(getImgLoader(), imageView, imageUrl, placeholder);
    }

    protected void setText(int viewId, String text) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        textView.setText(text);
    }

    protected void setText(int viewId, String text, String emptyTip) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setText(emptyTip);
            return;
        }
        textView.setText(text);
    }

    protected void setTextEmptyGone(int viewId, String text) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setText(text);
    }

    protected <T extends View> T setGone(int id) {
        T view = findView(id);
        view.setVisibility(View.GONE);
        return view;
    }

    protected <T extends View> T setVisibility(int id) {
        T view = findView(id);
        view.setVisibility(View.VISIBLE);
        return view;
    }

    protected void setInVisibility(int id) {
        findView(id).setVisibility(View.INVISIBLE);
    }

    protected void onRestartInstance(Bundle bundle) {

    }

    protected void setStatusBarPadding() {
        mRoot.setPadding(0, getStatusHeight(mContext), 0, 0);
    }

    @SuppressLint("ObsoleteSdkInt,PrivateApi")
    private static int getStatusHeight(Context context) {
        int statusHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                Object object = clazz.newInstance();
                int height = Integer.parseInt(clazz.getField("status_bar_height")
                        .get(object).toString());
                statusHeight = context.getResources().getDimensionPixelSize(height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    @NonNull
    @Override
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle savedInstanceState) {
        Logger.si("onGetLayoutInflater");
        return super.onGetLayoutInflater(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        Logger.si("onAttach(Context content) ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
        Logger.si(" onCreate(@Nullable Bundle savedInstanceState)");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.si("onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) ");
        isDestoryed = false;
        if (mRoot != null) {
            Logger.si(" mRoot not null! ");
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
            mInflater = inflater;
            // Do something
            onBindViewBefore(mRoot);
            // Bind view
            mUnbinder = ButterKnife.bind(this, mRoot);
            // Get savedInstanceState
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
            // Init
            initWidget(mRoot);
            initData();
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.si("onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.si("onActivityCreated(@Nullable Bundle savedInstanceState)");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.si("onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.si("onResume()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.si("onSaveInstanceState(@NonNull Bundle outState)");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.si("onPause() ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.si("onStop() ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{
            if(mUnbinder!=null && !isDestoryed)
                mUnbinder.unbind();
        }catch(Exception e){
            e.printStackTrace();
        }
        mImgLoader = null;
        isDestoryed = true;
        mBundle = null;
        Logger.si("onDestroyView() ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.si("onDestroy() ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        Logger.si("onDetach() ");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Logger.si("onCreateOptionsMenu(Menu menu, MenuInflater inflater) ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.si("onOptionsItemSelected(MenuItem item)");
        return super.onOptionsItemSelected(item);
    }
}
