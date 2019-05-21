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

package work.mathwiki.core.app.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import work.mathwiki.core.bean.Tweet;
import work.mathwiki.utility.TLog;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */

public class ImageJsonDeserializer implements JsonDeserializer<Tweet.Image> {
    @Override
    public Tweet.Image deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.isJsonObject()) {
                Tweet.Image image = new Tweet.Image();
                // The whole object is available
                final JsonObject jsonObject = json.getAsJsonObject();
                image.setThumb(context.deserialize(jsonObject.get("thumb"), String.class));
                image.setHref(context.deserialize(jsonObject.get("href"), String.class));
                image.setH(context.<Integer>deserialize(jsonObject.get("h"), int.class));
                image.setW(context.<Integer>deserialize(jsonObject.get("w"), int.class));
                if (Tweet.Image.check(image))
                    return image;
                else
                    return null;
            }
        } catch (Exception e) {
            TLog.error("ImageJsonDeserializer-deserialize-error:" + (json != null ? json.toString() : ""));
        }
        return null;
    }
}