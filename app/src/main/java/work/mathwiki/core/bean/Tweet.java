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

package work.mathwiki.core.bean;

import android.text.TextUtils;

import net.oschina.common.utils.CollectionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import work.mathwiki.core.bean.simple.About;
import work.mathwiki.core.bean.simple.Author;

/**
 * Created by huanghaibin_dev
 * on 2016/7/18.
 */
public class Tweet implements Serializable {
    private long id;
    private String content;
    private int appClient;
    private int commentCount;
    private int likeCount;
    private boolean liked;
    private String pubDate;
    private Author author;
    private Code code;
    private String href;
    private Audio[] audio;
    private Image[] images;
    private Statistics statistics;
    private About about;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAppClient() {
        return appClient;
    }

    public void setAppClient(int appClient) {
        this.appClient = appClient;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Audio[] getAudio() {
        return audio;
    }

    public void setAudio(Audio[] audio) {
        this.audio = audio;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static class Code implements Serializable {
        private String brush;
        private String content;

        public String getBrush() {
            return brush;
        }

        public void setBrush(String brush) {
            this.brush = brush;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class Audio implements Serializable {
        private String href;
        private long timeSpan;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public long getTimeSpan() {
            return timeSpan;
        }

        public void setTimeSpan(long timeSpan) {
            this.timeSpan = timeSpan;
        }
    }

    public static class Image implements Serializable {
        private String thumb;
        private String href;
        private String name;
        private int type;
        private int w;
        private int h;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public static Image create(String href) {
            Image image = new Image();
            image.thumb = href;
            image.href = href;
            return image;
        }

        public static String[] getImagePath(Image[] images) {
            if (images == null || images.length == 0)
                return null;

            List<String> paths = new ArrayList<>();
            for (Image image : images) {
                if (check(image))
                    paths.add(image.href);
            }

            return CollectionUtil.toArray(paths, String.class);
        }

        public static boolean check(Image image) {
            return image != null
                    && !TextUtils.isEmpty(image.getThumb())
                    && !TextUtils.isEmpty(image.getHref());
        }
    }

    public static class Statistics implements Serializable {
        private int comment;
        private int transmit;
        private int like;

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getTransmit() {
            return transmit;
        }

        public void setTransmit(int transmit) {
            this.transmit = transmit;
        }
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", appClient=" + appClient +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", liked=" + liked +
                ", pubDate='" + pubDate + '\'' +
                ", author=" + author +
                ", code=" + code +
                ", audio=" + Arrays.toString(audio) +
                ", images=" + Arrays.toString(images) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Tweet) {
            return ((Tweet) o).getId() == id;
        }
        return false;
    }
}
