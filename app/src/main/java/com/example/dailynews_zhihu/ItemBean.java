package com.example.dailynews_zhihu;


import java.util.List;

public class ItemBean {
    private String text;
    /**
     * date : 20191124
     * stories : [{"image_hue":"0x665883","title":"明日方舟的干员身上，有哪些不易察觉的小细节或小彩蛋？","url":"https://daily.zhihu.com/story/9717353","hint":"掘墓的老实人 · 3 分钟阅读","ga_prefix":"112416","images":["https://pic3.zhimg.com/v2-8ba8c3bfea8990803ae394307c3bc082.jpg"],"type":0,"id":9717353},{"image_hue":"0x6d839c","title":"建筑学都学些什么？","url":"https://daily.zhihu.com/story/9717322","hint":"李乐贤 · 2 分钟阅读","ga_prefix":"112409","images":["https://pic2.zhimg.com/v2-c6889e6e00f982b468788b2319ebb7fd.jpg"],"type":0,"id":9717322},{"image_hue":"0x040404","title":"为什么 SpaceX 这个私人企业可以随意发射卫星？","url":"https://daily.zhihu.com/story/9717414","hint":"赵泠 · 2 分钟阅读","ga_prefix":"112407","images":["https://pic2.zhimg.com/v2-93a774f0792d33846797ffa22b976049.jpg"],"type":0,"id":9717414}]
     * top_stories : [{"image_hue":"0x8d8962","hint":"作者 / SME情报员","url":"https://daily.zhihu.com/story/9717311","image":"https://pic1.zhimg.com/v2-b8c741043e5b4f31f75f8936eb809b88.jpg","title":"如何证明斑马是长着白色条纹的黑马？","ga_prefix":"111807","type":0,"id":9717311},{"image_hue":"0x2d4036","hint":"作者 / 一筑一事 大管家","url":"https://daily.zhihu.com/story/9717474","image":"https://pic2.zhimg.com/v2-60ca7cdc749dd163992b60a1ba212215.jpg","title":"成都的哪一点，让你很喜欢？","ga_prefix":"111909","type":0,"id":9717474},{"image_hue":"0xa29c43","hint":"作者 / 难得糊涂","url":"https://daily.zhihu.com/story/9717218","image":"https://pic1.zhimg.com/v2-b38c2359e7c081ab73509edb828a0f08.jpg","title":"是「非常的大」还是「非常地大」？","ga_prefix":"112307","type":0,"id":9717218},{"image_hue":"0xb38049","hint":"作者 / 2001室的库布里克","url":"https://daily.zhihu.com/story/9717181","image":"https://pic3.zhimg.com/v2-aa024b6af1f9a4d6862d117b14976f8e.jpg","title":"为什么真正的电影，都应该在电影院看？","ga_prefix":"111707","type":0,"id":9717181},{"image_hue":"0x6b5b4b","hint":"作者 / 菲利普医生","url":"https://daily.zhihu.com/story/9717007","image":"https://pic3.zhimg.com/v2-39597c2ea2dacfd2e1ff07d205f30e6e.jpg","title":"猫脑袋经常不小心撞到硬物会傻吗？","ga_prefix":"111616","type":0,"id":9717007}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * image_hue : 0x665883
         * title : 明日方舟的干员身上，有哪些不易察觉的小细节或小彩蛋？
         * url : https://daily.zhihu.com/story/9717353
         * hint : 掘墓的老实人 · 3 分钟阅读
         * ga_prefix : 112416
         * images : ["https://pic3.zhimg.com/v2-8ba8c3bfea8990803ae394307c3bc082.jpg"]
         * type : 0
         * id : 9717353
         */

        private String image_hue;
        private String title;
        private String url;
        private String hint;
        private String ga_prefix;
        private int type;
        private String id;
        private String images;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

    }

    public static class TopStoriesBean {
        /**
         * image_hue : 0x8d8962
         * hint : 作者 / SME情报员
         * url : https://daily.zhihu.com/story/9717311
         * image : https://pic1.zhimg.com/v2-b8c741043e5b4f31f75f8936eb809b88.jpg
         * title : 如何证明斑马是长着白色条纹的黑马？
         * ga_prefix : 111807
         * type : 0
         * id : 9717311
         */

        private String image_hue;
        private String hint;
        private String url;
        private String image;
        private String title;
        private String ga_prefix;
        private int type;
        private String id;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

