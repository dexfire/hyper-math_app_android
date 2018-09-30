package work.mathwiki.core.settings;

import android.content.Context;

/**
 * Created by Dexfire on 2018/8/29 0029.
 *
 *  全App的设置管理器，使用getter & setter 来获取和修改设置。
 *
 *  修改设置时会同时写入 preference 中。
 *
 */

public class SettingsManager {

    private static SettingsManager ourInstance = new SettingsManager();

    // region setting_vars
    private boolean General_First_Welcome_Splash_Showed = false;

    // 是否允许使用移动网络下载
    private boolean General_Allow_Download_via_Mobile = true;

    // 是否自动换行
    private boolean Markdown_Editor_Auto_Changeline = true;

    private int Markdown_Editor_Highlight_Level = 5;

    private boolean Markdown_Editor_AutoComplete = true;

    private int Markdwon_Editor_Space_Indent = -1;

    //endregion

    public boolean isGeneral_Allow_Download_via_Mobile() {
        return General_Allow_Download_via_Mobile;
    }

    public void setGeneral_Allow_Download_via_Mobile(boolean general_Allow_Download_via_Mobile) {
        General_Allow_Download_via_Mobile = general_Allow_Download_via_Mobile;
        Pref;
    }

    public boolean isMarkdown_Editor_Auto_Changeline() {
        return Markdown_Editor_Auto_Changeline;
    }

    public void setMarkdown_Editor_Auto_Changeline(boolean markdown_Editor_Auto_Changeline) {
        Markdown_Editor_Auto_Changeline = markdown_Editor_Auto_Changeline;
    }

    public int getMarkdown_Editor_Highlight_Level() {
        return Markdown_Editor_Highlight_Level;
    }

    public void setMarkdown_Editor_Highlight_Level(int markdown_Editor_Highlight_Level) {
        Markdown_Editor_Highlight_Level = markdown_Editor_Highlight_Level;
    }

    public boolean isMarkdown_Editor_AutoComplete() {
        return Markdown_Editor_AutoComplete;
    }

    public void setMarkdown_Editor_AutoComplete(boolean markdown_Editor_AutoComplete) {
        Markdown_Editor_AutoComplete = markdown_Editor_AutoComplete;
    }

    public int getMarkdown_RecyclerView_Lazy_Load_Count() {
        return Markdown_RecyclerView_Lazy_Load_Count;
    }

    public void setMarkdown_RecyclerView_Lazy_Load_Count(int markdown_RecyclerView_Lazy_Load_Count) {
        Markdown_RecyclerView_Lazy_Load_Count = markdown_RecyclerView_Lazy_Load_Count;
    }

    // 笔记列表一次加载笔记数目
    private int Markdown_RecyclerView_Lazy_Load_Count = 15;

    public static SettingsManager getInstance() {
        if (ourInstance == null){
            synchronized(SettingsManager.class){
                if (ourInstance == null)
                    ourInstance = new SettingsManager();
            }
        }
        return ourInstance;
    }

    private SettingsManager() {
    }

    public static void init(Context context){
        ourInstance = getInstance();
        // TODO: load preferences
    }

    public int getMarkdwon_Editor_Space_Indent() {
        return Markdwon_Editor_Space_Indent;
    }

    public void setMarkdwon_Editor_Space_Indent(int markdwon_Editor_Space_Indent) {
        Markdwon_Editor_Space_Indent = markdwon_Editor_Space_Indent;
    }

    public boolean isGeneral_First_Welcome_Splash_Showed() {
        return General_First_Welcome_Splash_Showed;
    }

    public void setGeneral_First_Welcome_Splash_Showed(boolean general_First_Welcome_Splash_Showed) {
        General_First_Welcome_Splash_Showed = general_First_Welcome_Splash_Showed;
    }
}
