package info.cc.com;

/**
 * 第四个页面list加载对象
 * @author 寇财玮
 * @version 2017年11月30日17:49:53
 */
public class FourFragmentListViewDataInfo {

    public String img_url;

    public String title;

    public String detail;

    public FourFragmentListViewDataInfo(String img_url, String title, String detail) {
        this.img_url = img_url;
        this.title = title;
        this.detail = detail;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "FourFragmentListViewDataInfo{" +
                "img_url='" + img_url + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
