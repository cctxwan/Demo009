package info.cc.com;

/**
 * Created by admin on 2017/12/4.
 */

public class ChildStatusEntity {

    /** 预计完成时间 **/
    private String completeTime;
    /** 是否已完成 **/
    private boolean isfinished;

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public boolean isIsfinished() {
        return isfinished;
    }

    public void setIsfinished(boolean isfinished) {
        this.isfinished = isfinished;
    }

}
