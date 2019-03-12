package info.cc.com;

/**
 * Created by admin on 2017/11/30.
 */

public class InstallListViewInfo {

    public String title;

    public String detail;

    public InstallListViewInfo(String title, String detail){
        this.title = title;
        this.detail = detail;
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
        return "InstallListViewInfo{" +
                "title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
