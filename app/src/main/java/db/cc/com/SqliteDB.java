package db.cc.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.cc.com.TimeLineData;
import info.cc.com.LoginInfo;
import info.cc.com.MessageInfo;
import info.cc.com.NewsData;
import utils.cc.com.StringUtil;

/**
 * Created by admin on 2017/10/25.
 */
public class SqliteDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "CC_Demo009_DB";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static SqliteDB sqliteDB;

    private SQLiteDatabase db;

    private SqliteDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取SqliteDB实例
     * @param context
     */
    public synchronized static SqliteDB getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SqliteDB(context);
        }
        return sqliteDB;
    }



    /**
     * 将登录User实例存储到数据库。
     */
    public int saveUser(LoginInfo user) {
        if (user != null) {
           /* ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("userpwd", user.getUserpwd());
            db.insert("User", null, values);*/

            Cursor cursor = db.rawQuery("select * from CC_Demo009_Info where username=?", new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into CC_Demo009_Info(username,password) values(?,?) ", new String[]{user.getUsername().toString(), user.getPassword().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 将时间轴User实例存储到时间轴表中
     */
    public int saveTimeData(TimeLineData data) {
        if (data != null) {
           /* ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("userpwd", user.getUserpwd());
            db.insert("User", null, values);*/

            Cursor cursor = db.rawQuery("select * from time_line_data where content=?", new String[]{data.getContent().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into time_line_data(content,timedata) values(?,?) ", new String[]{data.getContent().toString(), data.getData().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 将新闻存储在数据库表中
     */
    public int saveNews(NewsData data) {
        if (data != null) {
            Cursor cursor = db.rawQuery("select * from news_data where img_url=?", new String[]{data.getContent().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into news_data(img_url,name,title,date,newsfrom,content,content_detail,content_url) values(?,?,?,?,?,?,?,?) ",
                            new String[]{
                                    data.getImg_url(),
                                    data.getName(),
                                    data.getTitle(),
                                    data.getDate(),
                                    data.getNewsfrom(),
                                    data.getContent(),
                                    data.getContent_detail(),
                                    data.getContent_url()
                            }
                    );
                } catch (Exception e) {
                    Log.d("错误", e.getMessage());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 将message存储在数据库表中
     */
    public int saveMessage(MessageInfo messageInfo) {
        if (messageInfo != null) {
            Cursor cursor = db.rawQuery("select * from message_data where img_url=?", new String[]{messageInfo.getImgUrl().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into message_data(img_url,content) values(?,?) ",
                            new String[]{
                                messageInfo.getImgUrl(),
                                messageInfo.getContent()
                            }
                    );
                } catch (Exception e) {
                    Log.d("错误", e.getMessage());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 从数据库Message表中读取数据
     */
    public List<MessageInfo> getMessages() {
        List<MessageInfo> list = new ArrayList<MessageInfo>();
        Cursor cursor = db
//                .rawQuery("select * from message_data", null);
                .query("message_data", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                MessageInfo data = new MessageInfo();
                data.setMid(cursor.getInt(cursor.getColumnIndex("id")));
                data.setContent(cursor.getString(cursor.getColumnIndex("content")));
                data.setImgUrl(cursor.getString(cursor.getColumnIndex("img_url")));
                list.add(data);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 从数据库新闻表中读取新闻数据
     */
    public List<NewsData> getNews() {
        List<NewsData> list = new ArrayList<NewsData>();
        Cursor cursor = db
//                .rawQuery("select * from news_data", null);
        .query("news_data", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                NewsData data = new NewsData();
                data.setId(cursor.getInt(cursor.getColumnIndex("id")));
                data.setImg_url(cursor.getString(cursor.getColumnIndex("img_url")));
                data.setName(cursor.getString(cursor.getColumnIndex("name")));
                data.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                data.setDate(cursor.getString(cursor.getColumnIndex("date")));
                data.setNewsfrom(cursor.getString(cursor.getColumnIndex("newsfrom")));
                data.setContent(cursor.getString(cursor.getColumnIndex("content")));
                data.setContent_detail(cursor.getString(cursor.getColumnIndex("content_detail")));
                data.setContent_url(cursor.getString(cursor.getColumnIndex("content_url")));
                data.setCollectnew(cursor.getString(cursor.getColumnIndex("collectnew")));
                list.add(data);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 从时间轴表中读取信息。
     */
    public List<TimeLineData> getDatas() {
        List<TimeLineData> list = new ArrayList<TimeLineData>();
        Cursor cursor = db
                .rawQuery("select * from time_line_data order by timedata desc", null);
        if (cursor.moveToFirst()) {
            do {
                TimeLineData data = new TimeLineData();
                data.setContent(cursor.getString(cursor
                        .getColumnIndex("content")));
                data.setData(cursor.getString(cursor

                        .getColumnIndex("timedata")));
                list.add(data);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 从登录表读取User信息。
     */
    public List<LoginInfo> loadUser() {
        List<LoginInfo> list = new ArrayList<LoginInfo>();
        Cursor cursor = db
                .query("CC_Demo009_Info", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                LoginInfo user = new LoginInfo();
                user.setId(cursor.getInt(cursor.getColumnIndex("uid")));
                user.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                user.setPassword(cursor.getString(cursor

                        .getColumnIndex("password")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }




    /**
     * 确认登录数据
     * @param pwd
     * @param name
     * @return
     */
    public int Quer(String pwd,String name) {
        HashMap<String,String> hashmap=new HashMap<String,String>();
        Cursor cursor =db.rawQuery("select * from CC_Demo009_Info where username=?", new String[]{name});

        // hashmap.put("name",db.rawQuery("select * from CC_Demo009_Info where name=?",new String[]{name}).toString());
        if (cursor.getCount()>0)
        {
            Cursor pwdcursor =db.rawQuery("select * from CC_Demo009_Info where password=? and username=?",new String[]{pwd,name});
            if (pwdcursor.getCount()>0)
            {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 根据当前新闻ID获取对应的新闻
     * @param newId
     */
    public List<NewsData> selectByIdNew(String newId){
        List<NewsData> list = new ArrayList<NewsData>();
        String id = String.valueOf(newId);
        Cursor cursor = db.rawQuery("select * from news_data where id=?", new String[]{id});

        if(cursor.moveToFirst()){

            NewsData newsData = new NewsData();
            newsData.setId(cursor.getInt(cursor.getColumnIndex("id")));
            newsData.setImg_url(cursor.getString(cursor.getColumnIndex("img_url")));
            newsData.setName(cursor.getString(cursor.getColumnIndex("name")));
            newsData.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            newsData.setDate(cursor.getString(cursor.getColumnIndex("date")));
            newsData.setNewsfrom(cursor.getString(cursor.getColumnIndex("newsfrom")));
            newsData.setContent(cursor.getString(cursor.getColumnIndex("content")));
            newsData.setContent_detail(cursor.getString(cursor.getColumnIndex("content_detail")));
            newsData.setContent_url(cursor.getString(cursor.getColumnIndex("content_url")));
            newsData.setCollectnew(cursor.getString(cursor.getColumnIndex("collectnew")));

            list.add(newsData);

        }
        return list;

    }

    /**
     * 修改收藏属性值
     * @param collectnew
     */
    public void updateNew(String collectnew, String newId) {
        ContentValues values = new ContentValues();
        values.put("collectnew", collectnew);
        long ret = -1;
        do {
            ret = db.update("news_data", values, "id=?",
                    new String[] { newId });
        } while (ret < 0);
    }

    /**
     * 根据当前id所对应的的message
     * @param mid
     * @return
     */
    public int deleteById(int mid) {
        if (StringUtil.isEmpty(String.valueOf(mid))) {
            return -1;
        }
//            Cursor cursor = db.de("delete from message_data where id=?", new String[]{String.valueOf(mid)});
        int num = db.delete("message_data", "id=?", new String[]{String.valueOf(mid)});
        System.out.println(num + "----123456");
        return num;
    }
}
