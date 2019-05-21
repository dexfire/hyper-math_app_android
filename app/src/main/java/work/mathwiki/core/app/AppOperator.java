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

package work.mathwiki.core.app;

import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

//import net.oschina.app.improve.account.AccountHelper;
import work.mathwiki.core.account.AccountHelper;
import work.mathwiki.core.app.gson.DoubleJsonDeserializer;
import work.mathwiki.core.app.gson.FloatJsonDeserializer;
import work.mathwiki.core.app.gson.ImageJsonDeserializer;
import work.mathwiki.core.app.gson.IntegerJsonDeserializer;
import work.mathwiki.core.app.gson.StringJsonDeserializer;
import work.mathwiki.core.bean.Tweet;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JuQiu
 * on 16/6/24.
 */
public final class AppOperator {
    private static Handler mHandler;
    private static ExecutorService EXECUTORS_INSTANCE;
    private static Gson GSON_INSTANCE;

    public static Executor getExecutor() {
        if (EXECUTORS_INSTANCE == null) {
            synchronized (AppOperator.class) {
                if (EXECUTORS_INSTANCE == null) {
                    EXECUTORS_INSTANCE = Executors.newFixedThreadPool(6);
                }
            }
        }
        return EXECUTORS_INSTANCE;
    }


    public static void runOnMainThread(Runnable runnable) {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.post(runnable);
    }

    public static void runOnThread(Runnable runnable) {
        getExecutor().execute(runnable);
    }

    public static GlideUrl getGlideUrlByUser(String url) {
        if (AccountHelper.isLogin()) {
            return new GlideUrl(url,
                    new LazyHeaders
                            .Builder()
                            .addHeader("Cookie", AccountHelper.getCookie())
                            .build());
        } else {
            return new GlideUrl(url);
        }
    }

    public static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.setExclusionStrategies(new SpecificClassExclusionStrategy(null, Model.class));
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");

        JsonDeserializer deserializer = new IntegerJsonDeserializer();
        gsonBuilder.registerTypeAdapter(int.class, deserializer);
        gsonBuilder.registerTypeAdapter(Integer.class, deserializer);

        deserializer = new FloatJsonDeserializer();
        gsonBuilder.registerTypeAdapter(float.class, deserializer);
        gsonBuilder.registerTypeAdapter(Float.class, deserializer);

        deserializer = new DoubleJsonDeserializer();
        gsonBuilder.registerTypeAdapter(double.class, deserializer);
        gsonBuilder.registerTypeAdapter(Double.class, deserializer);

        deserializer = new StringJsonDeserializer();
        gsonBuilder.registerTypeAdapter(String.class, deserializer);

        gsonBuilder.registerTypeAdapter(Tweet.Image.class, new ImageJsonDeserializer());

        return gsonBuilder.create();
    }

    public synchronized static Gson getGson() {
        if (GSON_INSTANCE == null)
            GSON_INSTANCE = createGson();
        return GSON_INSTANCE;
    }

}