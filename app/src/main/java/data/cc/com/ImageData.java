package data.cc.com;

/**
 * Created by admin on 2017/12/18.
 */

public class ImageData {

    public ImageData(String url){
        this.url = url;
    }

    public String url;

    @Override
    public String toString() {
        return "ImageData{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
