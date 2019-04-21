package cn.itcase.jedis.test;

import cn.itcase.Utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: qingye
 * @Date: 2019/2/25 0025 14:50
 * @Version 1.0
 */

public class JedisTest {
    @Test
    public void test1() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("username", "zhangsan");
        String username = jedis.get("username");
        System.out.println(username);
        //设置键值对10秒后消失
        String gogo = jedis.setex("password", 10, "gogo");
        System.out.println(gogo);
        jedis.keys("*");
        jedis.close();
    }

    @Test
    public void test2() {
        Jedis jedis = new Jedis();
        //存储hash
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "female");
        //获取hash
        String gender = jedis.hget("user", "gender");
        System.out.println(gender);
        //获取所有map中的值
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> keyset = user.keySet();
        for (String s : keyset) {
            String value = user.get(s);
            System.out.println(s + ":" + value);
        }
    }

    @Test
    public void test3() {
        Jedis jedis = new Jedis();
        jedis.lpush("mylist", "a", "b", "c");//从左到右村粗
        jedis.lpush("mylist", "a", "b", "c");//从右到左存储
        //list 范围获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);
        //弹出
        String delele = jedis.lpop("mylist");
        System.out.println(delele);
        String delele1 = jedis.rpop("mylist");
        System.out.println(delele1);
        //list 范围获取
        List<String> mylist1 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist1);
        jedis.close();

    }
    @Test
    public void test4() {
        Jedis jedis = new Jedis();
        //存储set
        jedis.sadd("myset","java","pjp","c++");
        //获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        jedis.close();
    }
    @Test
    public void test5() {
        Jedis jedis = new Jedis();
        //存储zset
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",50,"凯");
        jedis.zadd("mysortedset",66,"后裔");
        //获取zset
        Set<Tuple> mysortedset = jedis.zrangeWithScores("mysortedset", 0, -1);
       // Set<String> zrevrang = jedis.zrevrange("mysortedset", 0, -1);
        for (Tuple tuple : mysortedset) {
            System.out.println(tuple.getElement()+"；"+tuple.getScore());
        }

       // System.out.println(zrevrang);
        jedis.close();
    }
    @Test
    public void test6() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String s = jedis.set("hello", "world");
        String hello = jedis.get("hello");
        System.out.println(hello);
        jedis.close();
    }
}

