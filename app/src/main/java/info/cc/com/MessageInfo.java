package info.cc.com;

/**
 * Created by admin on 2017/12/18.
 */

public class MessageInfo {

    private int mid;

    private String content;

    private String imgUrl;

    @Override
    public String toString() {
        return "MessageInfo{" +
                "mid=" + mid +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
