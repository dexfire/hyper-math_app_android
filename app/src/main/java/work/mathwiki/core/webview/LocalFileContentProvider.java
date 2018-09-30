package work.mathwiki.core.webview;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;

import work.mathwiki.MainActivity;
import work.mathwiki.core.data.DataManager;
import work.mathwiki.core.logger.Logger;
import work.mathwiki.utility.ConstFieleds;

public class LocalFileContentProvider extends ContentProvider {

    public static final String URI_PREFIX = "content://work.mathwiki.data";

    public static String buildUri(String url) {
        Uri uri = Uri.parse(url);
        return uri.isAbsolute() ? url : URI_PREFIX + url;
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        String path = DataManager.getDataPath() + File.separator + uri.getPath();
        File target = new File(path);
        if(!target.exists()) path = DataManager.get404Path();
            else if(target.isDirectory()){
                if(new File(target, ConstFieleds.Index_Html).exists())
                    path += File.separator + ConstFieleds.Index_Html;
            }
        // TODO: resolve dictionary default index web file
        Logger.si("LocalFileContentProvider: Loading file "+ path);
        return ParcelFileDescriptor.open(new File(path),ParcelFileDescriptor.MODE_READ_WRITE);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // NOTICE:  Not Supported Method !
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // NOTICE:  Not Supported Method !
        return uri.getQuery();
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // NOTICE:  Not Supported Method !
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // NOTICE:  Not Supported Method !
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        // NOTICE:  Not Supported Method !
        return 0;
    }

}
