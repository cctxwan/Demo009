package data.cc.com;

import java.util.List;

/**
 * Created by admin on 2017/12/18.
 */

public class MessageData {

    private int mid;

    private String content;

    private List<String> imgUrl;

    @Override
    public String toString() {
        return "MessageData{" +
                "mid=" + mid +
                ", content='" + content + '\'' +
                ", imgUrl=" + imgUrl +
                '}';
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
