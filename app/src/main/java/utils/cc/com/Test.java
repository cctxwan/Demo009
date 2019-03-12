package utils.cc.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/4.
 */

public class Test {

    public static void main(String [] args){
        String str = "/storage/emulated/0/DCIM/camera/IMG_20171215_173035.jpg,/storage/emulated/0/DUGIFMaker/recorded/20171212_161826.gif,/storage/emulated/0/DUGIFMaker/edited/gifeditor_20171212_161317.gif,";
        String[]  strs = str.split(",");
        for(int i = 0,len = strs.length; i < len; i++){
            System.out.println(strs[i].toString());
        }
    }

//        String[][] str = { { "小一", "小二" }, { "二一", "二二" },{ "三一", "三二" }, { "四一", "四儿" } };
//
//        ArrayList<ArrayList<String>>  list = new ArrayList<ArrayList<String>>();//先定义一个集合对象
//
//        for(int i=0; i<str.length; i++){//遍历二维数组，对集合进行填充
//            ArrayList<String> listSub=new ArrayList<String>();//初始化一个ArrayList集合
//            for(int j=0; j<str[i].length; j++){
//                listSub.add(str[i][j]);//数组的列放到集合中
//
//            }
//            list.add(listSub);//二维数组放到集合中
//
//        }
//`
//        System.out.println(list);//打印数组结果
//        ArrayList<String> list1, list2, list3;
//        list1 = new ArrayList<String>();
//        list1.add("11");
//        list1.add("12");
//        list1.add("13");
//
//        list2 = new ArrayList<String>();
//        list2.add("21");
//        list2.add("22");
//
//        list3 = new ArrayList<String>();
//        list3.add("31");
//        list3.add("32");
//        list3.add("33");
//        list3.add("34");
//
//        ArrayList<ArrayList<String>> listTwo = new ArrayList<ArrayList<String>>();
//        listTwo.add(list1);
//        listTwo.add(list2);
//        listTwo.add(list3);
//
//        // 转成一维数组
//        String strone1[] = list1.toArray(new String[list1.size()]);
//        String strone2[] = list2.toArray(new String[list2.size()]);
//        String strone3[] = list3.toArray(new String[list3.size()]);
//
//        String strTwo[][] = new String[64][64];
//        // 转成二维数组
//        strTwo[0] = strone1;
//        strTwo[1] = strone2;
//        strTwo[2] = strone3;
////        printTwo(strTwo);
//        System.out.println(strTwo);
//    }
//
//    private static void printTwo(String arr[][]) {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] + ",");
//            }
//            System.out.println();
//        }

        // 准备数据//////////////////////////////////////////////
//        Element e1 = new Element();
//        e1.setId("e1");
//        Element e2 = new Element();
//        e2.setId("e2");
//        List<Element> list1 = new ArrayList<Element>();
//        list1.add(e1);
//        list1.add(e2);
//        Element e3 = new Element();
//        e3.setId("e3");
//        Element e4 = new Element();
//        e4.setId("e4");
//        List<Element> list2 = new ArrayList<Element>();
//        list2.add(e3);
//        list2.add(e4);
//        e1.setElements(list2);
//        Element e5 = new Element();
//        e5.setId("e5");
//        Element e6 = new Element();
//        e6.setId("e6");
//        List<Element> list3 = new ArrayList<Element>();
//        list3.add(e5);
//        list3.add(e6);
//        e3.setElements(list3);
//        // ///////////////////////////////////////////////////////////////
//        // 开始测试
//        StringBuilder buff = new StringBuilder();
//        listElements(list1, buff);
//        System.out.println(buff.toString());
//    }
//
//    /**
//     * 使用递归将数据拼成一个字符串
//     * @param list
//     * @param buff
//     */
//    public static void listElements(List<Element> list, StringBuilder buff) {
//        // 递归返回条件
//        if (list == null || list.size() == 0) {
//            return;
//        }
//        // 存储下一层所有元素
//        List<Element> list2 = new ArrayList<Element>();
//        for (Element e : list) {
//            // 记录当前层所有元素
//            buff.append(e.getId()).append(",");
//            if (e.getElements() != null) {
//                // 把每个元素的下一层放到一个list中
//                list2.addAll(e.getElements());
//            }
//        }
//        // 递归调用
//        listElements(list2, buff);
//    }
}
//class Element {
//    private String id;
//    private List<Element> elements;
//    public String getId() {
//        return id;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }
//    public List<Element> getElements() {
//        return elements;
//    }
//    public void setElements(List<Element> elements) {
//        this.elements = elements;
//    }
//
//    }