package work.mathwiki.utility;

import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileUtil {
    @Nullable
    public static char[] content(String path){
        File file = new File(path);
        if (file.exists() && file.isFile()){
            try {
                return readAll(new FileReader(file), (int) file.length());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @NotNull
    public static char[] readAll(@NotNull Reader reader, int lenth){
        char[] result = new char[lenth];  // 1MB
        int idx = 0;
        char[] buffer = new char[lenth>1024?lenth/10:lenth];
        int count;
        try{
            while((count=reader.read(buffer))!=-1){
                System.arraycopy(buffer,0,result,idx,count);
                idx+=count;
            }
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
