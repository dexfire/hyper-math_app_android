package work.mathwiki.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.mathwiki.R;
import work.mathwiki.base.fragments.BaseFragment;
import work.mathwiki.utility.SimpleListAdapterUtil;

/**
 *  Home Page Fragment
 *
 *
 */
public class HomeFragment extends BaseFragment {

    // 不要使用构造函数向Fragment传递参数

    //    @BindView(R.id.webview)
    //    WebView mWebView;

    @BindView(R.id.layout_home_list)
    ListView mListView;

    public HomeFragment() {
        super();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_list;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList<HashMap<String,Object>> data = new ArrayList<>();
        String[] keys = new String[]{"icon","title"};
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_play,getString(R.string.content_start)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_toc,getString(R.string.content_context)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_limit)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_differential)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_differential_equation)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_differential_multivariable)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_integration)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_integration_definite)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_integration_indefinite)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_integration_multivariable)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_integration_vector)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_integration_tips)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_folder,getString(R.string.content_infinite_series)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_formula)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_file,getString(R.string.content_alphaset)}));
        data.add(SimpleListAdapterUtil.makeMap(keys,new Object[]{R.drawable.ic_info,getString(R.string.content_about)}));
        mListView.setAdapter(new SimpleAdapter(getContext(),data,R.layout.list_item_simple_icon_with_text,keys,new int[]{R.id.icon,R.id.title}));
        mListView.setOnItemClickListener(onclick);
    }

    private AdapterView.OnItemClickListener onclick = (parent, view, position, id) -> {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            default:
                // TODO go home
//                if (mWebView!=null)
//                    mWebView.loadUrl("content://work.mathwiki.data/index.html");
        }
    };

}
