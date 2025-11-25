package com.student;

public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String studentId; // 学号
    private String major; // 专业

    // getter/setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}