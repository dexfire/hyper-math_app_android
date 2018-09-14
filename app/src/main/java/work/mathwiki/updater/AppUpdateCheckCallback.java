package work.mathwiki.updater;

import work.mathwiki.core.network.AppUpdateInfo;
import work.mathwiki.core.network.HttpResponseInfo;

public interface AppUpdateCheckCallback {
    void onCheckResult(boolean hasUpdate, AppUpdateInfo updateInfoinfo, HttpResponseInfo responseInfo);
}
