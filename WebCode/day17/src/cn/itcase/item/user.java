package cn.itcase.item;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: qingye
 * @Date: 2019/2/15 0015 11:44
 * @Version 1.0
 */
public class user {
   private String name;
   private int age;
   private Date birthday;

   public String getday(){
       if(birthday!=null){
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
           return  sdf.format(birthday);
       }else {
           return "";
       }

   }

    public user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public user(String name, int age, Date birthday) {

        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
}
