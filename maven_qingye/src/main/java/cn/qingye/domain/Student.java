package cn.qingye.domain;

/**
 * @Author: qingye
 * @Date: 2019/2/26 0026 19:15
 * @Version 1.0
 */
public class Student {
    private int sid;
    private String gender;
    private int class_id;
    private String sname;

    public Student() {
    }

    public Student(int sid, String gender, int class_id, String sname) {
        this.sid = sid;
        this.gender = gender;
        this.class_id = class_id;
        this.sname = sname;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", gender='" + gender + '\'' +
                ", class_id=" + class_id +
                ", sname='" + sname + '\'' +
                '}';
    }

}
