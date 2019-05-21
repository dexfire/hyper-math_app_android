/* The MIT License (MIT)
 * Copyright (c) 2018 OSChina.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package work.mathwiki.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class AppSharedPreference extends SharedPreferenceUtil {

    private static AppSharedPreference mInstance;

    public static void init(Context context, String name) {
        if (mInstance == null) {
            mInstance = new AppSharedPreference(context, name);
        }
    }

    public static AppSharedPreference getInstance() {
        return mInstance;
    }
    private AppSharedPreference(Context context, String name) {
        super(context, name);
    }

    /**
     * 点击更新过的版本
     */
    void putUpdateVersion(int code) {
        put("osc_update_code", code);
    }

    /**
     * 设置更新过的版本
     */
    public int getUpdateVersion() {
        return getInt("osc_update_code", 0);
    }

    /**
     * 设置不弹出更新
     */
    public void putShowUpdate(boolean isShow) {
        put("osc_update_show", isShow);
    }

    /**
     * 是否弹出更新
     * 或者是新版本重新更新 259200000
     */
    boolean isShowUpdate() {
        return getBoolean("osc_update_show", true);
    }

    /**
     * 是否已经弹出更新
     *
     * @return 不弹出更新代表已经更新
     */
    public boolean hasShowUpdate() {
        return !getBoolean("osc_update_show", true);
    }

    /**
     * 设备唯一标示
     *
     * @param id id
     */
    public void putDeviceUUID(String id) {
        put("osc_device_uuid", id);
    }

    /**
     * 设备唯一标示
     */
    public String getDeviceUUID() {
        return getString("osc_device_uuid", "");
    }


    /**
     * 第一次安装
     */
    public void putFirstInstall() {
        put("osc_first_install", false);
    }

    /**
     * 第一次安装
     */
    public boolean isFirstInstall() {
        return getBoolean("osc_first_install", true);
    }

    /**
     * 第一次使用
     */
    public void putFirstUsing() {
        put("osc_first_using_v2", false);
    }

    /**
     * 第一次使用
     */
    public boolean isFirstUsing() {
        return getBoolean("osc_first_using_v2", true);
    }


    /**
     * putLastNewsId
     *
     * @param id id
     */
    public void putLastNewsId(long id) {
        put("last_news_id", id);
    }

    /**
     * 获取最新的id
     *
     * @return return
     */
    public long getLastNewsId() {
        return getLong("last_news_id", 0);
    }


    /**
     * 返回新闻有多少
     */
    public long getTheNewsId() {
        return getLong("the_last_news_id", 0);
    }

    /**
     * 存储最新新闻数量
     */
    public void putTheNewsId(long count) {
        put("the_last_news_id", count);
    }


    /**
     * 关联剪切版
     *
     * @param isRelate isRelate
     */
    public void putRelateClip(boolean isRelate) {
        put("osc_is_relate_clip", isRelate);
    }

    /**
     * 是否关联剪切版
     *
     * @return 是否关联剪切版
     */
    public boolean isRelateClip() {
        return getBoolean("osc_is_relate_clip", true);
    }

    /**
     * 最后一次分享的url
     *
     * @param url 最后一次分享的url
     */
    public void putLastShareUrl(String url) {
        if (TextUtils.isEmpty(url))
            return;
        put("osc_last_share_url", url);
    }

    /**
     * 最后一次分享的url
     *
     * @return 最后一次分享的url
     */
    public String getLastShareUrl() {
        return getString("osc_last_share_url", "");
    }


    public boolean isFirstOpenUrl() {
        return getBoolean("osc_is_first_open_url", true);
    }

    public void putFirstOpenUrl() {
        put("osc_is_first_open_url", false);
    }
}

