package data.cc.com;

/**
 * Created by admin on 2017/12/4.
 */

public class TimeLineData {

    public String content;

    public String data;

    public TimeLineData(){}

    public TimeLineData(String content, String data){
        this.content = content;
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TimeLineData{" +
                "content='" + content + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
