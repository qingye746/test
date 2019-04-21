package cn.itcase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: qingye
 * @Date: 2019/2/23 0023 15:15
 * @Version 1.0
 */
public class Person {
    private String name;
    private int age;
//    @JsonIgnore   忽略下行属性的json转换
 //   @JsonIgnore
    private String gender;
//    @JsonFormat 给日期类定义格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Person() {
    }

    public Person(String name, int age, String gender, Date birthday) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, gender, birthday);
    }
}
