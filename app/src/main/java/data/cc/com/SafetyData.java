package data.cc.com;

/**
 * Created by admin on 2017/12/6.
 */

public class SafetyData {

    public String img_url;

    public String name;

    public SafetyData(String img_url, String name) {
        this.img_url = img_url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "SafetyData{" +
                "img_url='" + img_url + '\'' +
                ", name='" + name + '\'' +
                '}';
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
}
