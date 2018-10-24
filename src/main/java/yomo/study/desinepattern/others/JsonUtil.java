package yomo.study.desinepattern.others;


import com.alibaba.fastjson.JSON;
import com.sun.corba.se.impl.encoding.IDLJavaSerializationInputStream;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.HashMap;


public class JsonUtil {
    public static void main(String[] args) {
        paseJson();
    }
    public static void paseJson() {
        String abc = "{\"reconCode\":\"JD1810240003\",\"ids\":[1054943284503085056, 1054943284352090112]}";
        HashMap hashMap = JSON.parseObject(abc, HashMap.class);
        System.out.println(hashMap);
        Object ids = hashMap.get("ids");
        System.out.println(ids);
        String s = ids.toString();

        ArrayList arrayList = JSON.parseObject(s, ArrayList.class);
        int size = arrayList.size();

    }


}
