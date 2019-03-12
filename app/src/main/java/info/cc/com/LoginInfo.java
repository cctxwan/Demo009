package info.cc.com;

/**
 * Created by admin on 2017/12/1.
 */

public class LoginInfo {

    /**  */
    private int id;

    /**  */
    private String username;
    /**  */
    private String password;

    private int phonenum;

    private String islogin;

    private int sex;

    public int getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(int phonenum) {
        this.phonenum = phonenum;
    }

    public String getIslogin() {
        return islogin;
    }

    public void setIslogin(String islogin) {
        this.islogin = islogin;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {

        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {

        return password;
    }

}
