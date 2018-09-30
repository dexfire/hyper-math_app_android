package work.mathwiki.core.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.io.File;

import work.mathwiki.utility.ConstFieleds;

public class NotesAdapter extends RecyclerView.Adapter {

    private File notesDirectory;

    private File[] notesFileList;

    private int item_counts_lazy = 0;

    public NotesAdapter(){
        init();
        refresh();
    }

    private void init() {
        notesDirectory = new File(ConstFieleds.Default_Notes_Path);
    }

    private void refresh() {
        if (notesDirectory.exists() && notesDirectory.isDirectory() && notesDirectory.canRead()){
            notesFileList = notesDirectory.listFiles();
        }else{
            performCreateNotesDirectionary();
        }

    }

    private void performCreateNotesDirectionary() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
