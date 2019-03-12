package info.cc.com;

/**
 * Created by admin on 2017/12/5.
 */

public class NewsData {

    public int id;

    public String img_url;

    public String name;

    public String title;

    public String date;

    public String newsfrom;

    public String content;

    public String content_detail;

    public String content_url;

    public String collectnew;

    public String getCollectnew() {
        return collectnew;
    }

    public void setCollectnew(String collectnew) {
        this.collectnew = collectnew;
    }

    public NewsData() {}

    public NewsData(String img_url, String name, String title, String date, String newsfrom, String content, String content_detail, String content_url) {
        this.img_url = img_url;
        this.name = name;
        this.title = title;
        this.date = date;
        this.newsfrom = newsfrom;
        this.content = content;
        this.content_detail = content_detail;
        this.content_url = content_url;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "id=" + id +
                ", img_url='" + img_url + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", newsfrom='" + newsfrom + '\'' +
                ", content='" + content + '\'' +
                ", content_detail='" + content_detail + '\'' +
                ", content_url='" + content_url + '\'' +
                ", collectnew='" + collectnew + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNewsfrom() {
        return newsfrom;
    }

    public void setNewsfrom(String newsfrom) {
        this.newsfrom = newsfrom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_detail() {
        return content_detail;
    }

    public void setContent_detail(String content_detail) {
        this.content_detail = content_detail;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
}
