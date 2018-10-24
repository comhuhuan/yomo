package yomo.study;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.asm.Type;
import com.sun.javafx.collections.MappingChange;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("10.10.12.40", 6379);
        jedis.auth("midea123456");
        while (true) {

            System.out.println(jedis.pttl("token_ebs") / 1000);
            Thread.sleep(300000);
            System.out.println(jedis.get("token_ebs"));
            jedis.del("token_ebs");
            // System.out.println(jedis.get("token_ebs"));
        }

//
//
//        jedis.set("abc", "123");
//
//        jedis.expire("abc", 2);
//        String token2 = jedis.get("abc");
//        System.out.println(token2);
//        Thread.sleep(5000);
//        String token1 = jedis.get("abc");
//        System.out.println(token1);
//
//
//
//        System.out.println(token1);
//        String token3= jedis.get("abc");

//
//        String token = jedis.get("token_ebs");
//        jedis.set("token_ebs", "6d23aaf3-50bd-40bc-88ea-45e7916c33a2");
//        jedis.del("token_ebs");
//
//        System.out.println(token);
//        String token3= jedis.get("abc");
//        if (token != null) {
//            System.out.println(token);
//        }
//        System.out.println(jedis.pttl("abc"));
//        System.out.println(jedis.exists("abc"));
//        jedis.set("abc", "abc");
//        jedis.expire("abc", 360);
//        System.out.println(jedis.randomKey());
//
//        System.out.println(jedis.type("abc"));
//        jedis.append("abc", "huhuan");
//        System.out.println(jedis.get("abc"));
//        jedis.set("abc", "haha");
//        System.out.println(jedis.get("abc"));
//
//        jedis.set("hello2", "hahahaha");
//        // 设置过期时间
//        jedis.setex("hello2", 2, "world2");
//        System.out.println(jedis.get("hello2"));
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }
//        System.out.println(jedis.get("hello2"));
//
//        System.out.println("*********************************");
//        String key = "mylist";
//        jedis.del(key);
//        jedis.rpush(key, "aaaa");
//        jedis.rpush(key, "aaaa");
//        jedis.rpush(key, "bbbb");
//        jedis.rpush(key, "cccc");
//        jedis.rpush(key, "bbbb");
//        jedis.rpush(key, "cccc");
//        System.out.println("lenth: " + jedis.llen(key));
//        System.out.println("index of 1: " + jedis.lindex(key, 1));
//        jedis.lset(key, 1, "aa22");
//        System.out.println("index of 1: " + jedis.lindex(key, 1));
//        jedis.rpush(key, "-2", "-1", "aa");// 先-2，后-1入队列
//        System.out.println("all elements: " + jedis.lrange(key, 0, -1));
//        jedis.lpush(key, "-3", "-4", "a5");// 先-2，后-1入队列
//        System.out.println("all elements: " + jedis.lrange(key, 0, -1));
//        System.out.println(jedis.rpop(key));
//        System.out.println("all elements: " + jedis.lrange(key, 0, -1));
//        System.out.println(jedis.lpop(key));
//        System.out.println("all elements: " + jedis.lrange(key, 0, -1));
//
//        jedis.lrem(key, 1, "cccc");
//        System.out.println("all elements: " + jedis.lrange(key, 0, -1));
//        jedis.incrBy("abcd", 2);
//        System.out.println(jedis.get("abcd"));
//
//        String abc = "[{\n" +
//                "\"title\": \"业务实体\",\n" +
//                "\"dataKey\": \"orgName\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"String\",\n" +
//                "\"NO\": \"0\"\n" +
//                "}, {\n" +
//                "\"title\": \"供应商名称\",\n" +
//                "\"dataKey\": \"vendorName\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"String\",\n" +
//                "\"NO\": \"1\"\n" +
//                "}, {\n" +
//                "\"title\": \"物料编码\",\n" +
//                "\"dataKey\": \"itemCode\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"String\",\n" +
//                "\"NO\": \"2\"\n" +
//                "}, {\n" +
//                "\"title\": \"物料描述\",\n" +
//                "\"dataKey\": \"itemName\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"String\",\n" +
//                "\"NO\": \"3\"\n" +
//                "}, {\n" +
//                "\"title\": \"日期\",\n" +
//                "\"dataKey\": \"reqDate\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"Date\",\n" +
//                "\"NO\": \"4\"\n" +
//                "}, {\n" +
//                "\"title\": \"类型\",\n" +
//                "\"dataKey\": \"qtyType\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"String\",\n" +
//                "\"NO\": \"5\"\n" +
//                "}, {\n" +
//                "\"title\": \"数量\",\n" +
//                "\"dataKey\": \"qty\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"String\",\n" +
//                "\"NO\": \"6\"\n" +
//                "}, {\n" +
//                "\"title\": \"是否发布\",\n" +
//                "\"dataKey\": \"released\",\n" +
//                "\"ch\": \"1\",\n" +
//                "\"type\": \"String\",\n" +
//                "\"NO\": \"7\"\n" +
//                "}]";
//
//        JSONArray objects = JSON.parseArray(abc);
//


    }
}
