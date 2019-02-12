package work.mathwiki.core.notes;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.mathwiki.R;
import work.mathwiki.utility.ConstFieleds;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesListItemHolder> {
    
    enum Key{
        name, size, type, date, file
    }

    enum FileType{
        folder, markdown
    }
    // 当前工作路径
    private File notesDirectory;
    private List<Map<Key,Object>> data = new ArrayList<>();

    public NotesAdapter(){
        init();
        refresh();
    }

    private void init() {
        notesDirectory = new File(ConstFieleds.Default_Notes_Path);
    }

    public void refresh() {
        File[] notesFileList;
        if (notesDirectory.exists() && notesDirectory.isDirectory() && notesDirectory.canRead()){
            notesFileList = notesDirectory.listFiles();
        }else{
            performCreateNotesDirectionary();
            return;
        }
        data.clear();
        if (!notesDirectory.getAbsolutePath().contentEquals(ConstFieleds.Default_Notes_Path)){
            File parent = notesDirectory.getParentFile();
            data.add(createMap("...",FileType.folder,0,parent.lastModified(),parent));
        }
        if (notesFileList !=null){
            for (File f: notesFileList){
                if (f.canRead()){
                    if (f.isDirectory()){
                        data.add(createMap(f.getName(), FileType.folder,-1,f.lastModified(),f));
                    }else{ // 文件
                        String name = f.getName();
                        if (isMdFile(name)){
                            data.add(createMap(name,FileType.markdown, f.length(),f.lastModified(),f));
                        }
                    }
                }
            }
            Collections.sort(data, (o1, o2) -> {
                String n1 = (String) o1.get(Key.name);
                String n2 = (String) o2.get(Key.name);
                if ((long)o1.get(Key.size) ==-1 && (long)o2.get(Key.size)==-1){
                    return n1.compareToIgnoreCase(n2);
                } else {
                    return (long)o1.get(Key.size) ==-1?1:((long)o2.get(Key.size) ==-1?-1:0);
                }
            });
        }
    }

    private void performCreateNotesDirectionary() {

    }

    /***
     *  创建一个包含目录数据map 的辅助函数
     * @param name         文件名
     * @param size           文件大小，人类可读形式, 目录大小为 -1
     * @param type           类型
     * @param date          修改日期
     * @param file             文件对象，用于快速操作文件
     * @return  生成的map对象
     */
    private static Map<Key,Object> createMap(String name, FileType type, long size, long date, File file){
        HashMap<Key,Object> map = new HashMap<>();
        map.put(Key.name,name);
        map.put(Key.size,size);
        map.put(Key.type,type);
        map.put(Key.date,date);
        map.put(Key.file,file);
        return map;
    }

    private static boolean isMdFile(String name){
        String s = name.toLowerCase();
        return s.endsWith(".md")||s.endsWith(".markdown");
    }

    private static String size2str(long size){
        String unit = "B";
        if (size>1024){
            size/=1024;
            unit="KB";
        }
        if (size>1024){
            size/=1024;
            unit="MB";
        }
        if (size>1024){
            size/=1024;
            unit="GB";
        }
        return size+unit;
    }

    @NonNull
    @Override
    public NotesListItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card_notes,viewGroup);
        return new NotesListItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListItemHolder holder, int i) {
        Map<Key,Object> map = data.get(i);
        holder.title.setText((String) map.get(Key.name));
        switch ((FileType)map.get(Key.type)){
            case folder:
                holder.icon.setImageResource(R.drawable.ic_folder);
                holder.size.setText("");
                break;
            case markdown:
                holder.icon.setImageResource(R.drawable.ic_file);
                holder.size.setText(size2str((Long) map.get(Key.size)));
        }
        holder.date.setText(new Date((Long) map.get(Key.date)).toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class NotesListItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        AppCompatTextView title;
        @BindView(R.id.icon)
        AppCompatImageView icon;
        @BindView(R.id.text_date)
        AppCompatTextView date;
        @BindView(R.id.text_size)
        AppCompatTextView size;

        NotesListItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
