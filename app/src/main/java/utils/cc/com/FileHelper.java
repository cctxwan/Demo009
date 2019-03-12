package utils.cc.com;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.cc.com.TimeLineData;

/**
 * Created by admin on 2017/12/4.
 */

public class FileHelper {

    private Context mContext;

    //空参数构造函数，传入的值为空时，不出错
    public FileHelper() {
    }

    public FileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    /*
    * 定义文件保存的方法，写入到文件中，所以是输出流
    * */
    public void save(String content, String timedata) throws Exception {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "timeline.xml");
            FileOutputStream fos = new FileOutputStream(file);
            // 获得一个序列化工具
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "utf-8");
            // 设置文件头
            serializer.startDocument("utf-8", true);
            serializer.startTag(null, "datas");
            serializer.startTag(null, "data");
            // 写内容
            serializer.startTag(null, "content");
            serializer.text(content);
            serializer.endTag(null, "content");
            // 写日期
            serializer.startTag(null, "timedata");
            serializer.text(timedata);
            serializer.endTag(null, "timedata");
            serializer.endTag(null, "data");
            serializer.endTag(null, "datas");
            serializer.endDocument();
            fos.close();
            Log.i(PublicUtils.AppName, "时光轴日志已进入时空隧道(保存成功)");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(PublicUtils.AppName, "时光轴日志已进入黑洞隧道(保存失败)");
        }
    }


    /*
    * 定义文件读取的方法
    * */
    public void getData() throws IOException {
        List<TimeLineData> list = new ArrayList<TimeLineData>();
        try {
            File path = new File(Environment.getExternalStorageDirectory(),
                    "timeline.xml");
            FileInputStream fis = new FileInputStream(path);

            // 获得pull解析器对象
            XmlPullParser parser = Xml.newPullParser();
            // 指定解析的文件和编码格式
            parser.setInput(fis, "utf-8");

            int eventType = parser.getEventType(); // 获得事件类型

            String content = null;
            String timedata = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName(); // 获得当前节点的名称

                switch (eventType) {
                    case XmlPullParser.START_TAG: // 当前等于开始节点 <data>
                        if ("datas".equals(tagName)) { // <datas>
                        } else if ("content".equals(tagName)) { // <content>
                            content = parser.nextText();
                        }else if ("timedata".equals(tagName)) { // <timedata>
                            timedata = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG: // </persons>
                        if ("data".equals(tagName)) {
                            Log.i(PublicUtils.AppName, "时光轴日志已逃离时空隧道(读取成功)");
                            Log.i(PublicUtils.AppName, "content---" + content);
                            Log.i(PublicUtils.AppName, "timedata---" + timedata);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next(); // 获得下一个事件类型
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
