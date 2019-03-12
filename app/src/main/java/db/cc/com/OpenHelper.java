package db.cc.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2017/10/25.
 */
public class OpenHelper extends SQLiteOpenHelper {

    //建表语句（创建用户表）
    public static final String CREATE_USER = "create table CC_Demo009_Info ("
            + "uid integer primary key autoincrement, "
            + "username text, "
            + "password text)";

    //建表语句（时间轴数据表）
    public static final String CREATE_DATA = "create table time_line_data ("
            + "id integer primary key autoincrement, "
            + "content text, "
            + "timedata text)";

    //建表语句（新闻数据表）
    public static final String CREATE_NEWS = "create table news_data ("
            + "id integer primary key autoincrement, "
            + "img_url text,"
            + "name text,"
            + "title text,"
            + "date text,"
            + "newsfrom text,"
            + "content text,"
            + "content_detail text,"
            + "content_url text,"
            + "collectnew text DEFAULT '0' )";

    //建表语句（Message数据表）
    public static final String CREATE_MESSAGE = "create table message_data ("
            + "id integer primary key autoincrement, "
            + "img_url text,"
            + "content text)";

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_USER);//创建用户表
        db.execSQL(CREATE_DATA);//时间轴数据表
        db.execSQL(CREATE_NEWS);//新闻数据表
        db.execSQL(CREATE_MESSAGE);//Message表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
