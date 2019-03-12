package data.cc.com;

/**
 * Created by admin on 2017/12/4.
 */

public class lin_two_fragment_refresh_data {

    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String img_url;

    public String name;

    public String title;

    public String date;

    public String from;

    public String content;

    public String content_url;

    public lin_two_fragment_refresh_data() {}

    public lin_two_fragment_refresh_data(String img_url, String name, String title, String date, String from, String content, String content_url) {
        this.img_url = img_url;
        this.name = name;
        this.title = title;
        this.date = date;
        this.from = from;
        this.content = content;
        this.content_url = content_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "lin_two_fragment_refresh_data{" +
                "img_url='" + img_url + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", from='" + from + '\'' +
                ", content='" + content + '\'' +
                ", content_url='" + content_url + '\'' +
                '}';
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
}
