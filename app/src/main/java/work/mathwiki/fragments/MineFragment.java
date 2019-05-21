package work.mathwiki.fragments;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import work.mathwiki.MainActivity;
import work.mathwiki.R;
import work.mathwiki.account.LoginActivity;
import work.mathwiki.activities.SettingsActivity;
import work.mathwiki.base.fragments.BaseFragment;
import work.mathwiki.core.network.AppUpdateInfo;
import work.mathwiki.core.network.HttpResponseInfo;
import work.mathwiki.dialogs.ShareDialog;
import work.mathwiki.updater.AppUpdateCheckCallback;
import work.mathwiki.updater.AppUpdateManager;
import work.mathwiki.views.StyledToast;

/**
 *  <h1>“我的” 页面</h1>
 *  ============================
 *  <b>描述<b/>: 用于展示用户信息，用户收藏、通知、购买、关注、书签、设置等信息
 *
 **/

public class MineFragment extends BaseFragment implements View.OnClickListener{

    String[] name = new String[]{
            "我的消息",
            "我的收藏",
            "分享应用",
            "检查更新",
            "意见反馈",
            "设置",
            "关于",
            "退出"
    };
    @DrawableRes
    int[] icon = new int[]{
            R.drawable.ic_notifications_none,
            R.drawable.ic_favorite_border,
            R.drawable.ic_share,
            R.drawable.ic_update,
            R.drawable.ic_event_note,
            R.drawable.ic_settings,
            R.drawable.ic_whatshot,
            R.drawable.ic_exit
    };

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (position){
                case 2:
                    new ShareDialog(getContext()).show();
                    break;
                case 3:
                    StyledToast.makeText(getContext(),StyledToast.TYPE_INFO_GREEN,"正在检查新版本，请稍候 o(>ω<)o",300).show();
                    AppUpdateManager.getInstance().autoCheckUpdates(getContext());
                    break;
                case 5:
                    try{
                        SettingsActivity.show(Objects.requireNonNull(getContext()));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }

        @Override
        public void onItemLongClick(View v, int position) {

        }
    };

    public static final String TAG = MineFragment.class.getName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.mine_header)
    ViewGroup header;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_mine;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        MineMenuAdapter adapter = new MineMenuAdapter();
        header.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        try{
            recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_header:
                LoginActivity.show(getContext());
                break;
        }
    }

    private class MineMenuAdapter extends RecyclerView.Adapter<MineMenuItem> {

        @NonNull
        @Override
        public MineMenuItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_mine_menu,viewGroup,false);
            return new MineMenuItem(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MineMenuItem item, int i) {
            item.setIcon(icon[i]);
            item.setTitle(name[i]);
            item.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v,i));
            item.itemView.setOnLongClickListener(v -> {
                onItemClickListener.onItemLongClick(v,i);return false;
            });
        }

        @Override
        public int getItemCount() {
            return name.length;
        }
    }

    // RecyclerView 的对象容器
    private class MineMenuItem extends RecyclerView.ViewHolder{

        View itemView;
        ImageView icon;
        TextView title;

        MineMenuItem(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }

        protected void setIcon(@DrawableRes int id){
            if(icon!=null) icon.setImageResource(id);
        }

        protected void setTitle(@StringRes int id){
            if(title!=null) title.setText(id);
        }

        protected void setTitle(String str) {
            if(title!=null) title.setText(str);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
        void onItemLongClick(View v, int position);
    }

}
