package cn.itcase.test;

import cn.itcase.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: qingye
 * @Date: 2019/2/23 0023 15:13
 * @Version 1.0
 */
public class JacksonTest {

    @Test
    public void test1() throws Exception {
        Person p = new Person();
        p.setName("清叶");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());
        ObjectMapper mapper = new ObjectMapper();
//        writeValueAsString(p)将P对象转化为json字符串
//        String json = mapper.writeValueAsString(p);
//        System.out.println(json);
/*//        将p对象转化为json字符串存入到d盘a文件中
        mapper.writeValue(new File("d://a.txt"),p);
//          将p对象转化为json字符串关联到writer中
        mapper.writeValue(new FileWriter("e://a.txt"),p);*/

        Person p2 = new Person("风雪", 33, "女", new Date());
        Person p3 = new Person("黎光", 12, "男", new Date());
        Person p4 = new Person("黄玲", 13, "女", new Date());
        ArrayList<Person> ps = new ArrayList<>();
        ps.add(p);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);
        String jsons = mapper.writeValueAsString(ps);
        System.out.println(jsons);
    }

    @Test
    public void test2() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("gender", "男");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        //{"name":"张三","age":23,"gender":"男"}
        System.out.println(json);
    }

    @Test
    public void test3() throws Exception {
        //1.初始化JSON字符串
        String json = "{\"name\":\"清叶\",\"age\":23,\"gender\":\"男\",\"birthday\":\"2019-02-23\"}";
        ObjectMapper mapper = new ObjectMapper();
        //3.mapper.readValue转换为Java对象 Person对象 反射原理
        Person person = mapper.readValue(json,Person.class);
        System.out.println(person);

    }
}


