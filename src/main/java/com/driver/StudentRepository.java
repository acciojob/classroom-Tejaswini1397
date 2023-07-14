package com.driver;

import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.HashMap;

@Repository
public class StudentRepository {
    Map<String,Student>studentMap=new HashMap<>();
    Map<String,Teacher>teacherMap=new HashMap<>();
    Map<String,List<String>>studentTeacherMap=new HashMap<>();


    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {

        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentMap.containsKey(student)&& teacherMap.containsKey(teacher)){
            List<String> list = studentTeacherMap.get(teacher);
            list.add(student);
            studentTeacherMap.put(teacher, list);
        } else {
            List<String> al = new ArrayList<>();
            al.add(student);
            studentTeacherMap.put(teacher, al);
        }
    }

    public Student getStudentByName(String name) {
        if(!studentMap.containsKey(name)){
            return null;
        }
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        if(!teacherMap.containsKey(name)){
            return null;
        }

        return teacherMap.get(name);
    }

    public List<String> getStudentByTeacherName(String teacher) {
       if(studentTeacherMap.containsKey(teacher)){
           return studentTeacherMap.get(teacher);
       }
        return new ArrayList<>();
    }

    public void deleteTeacherByName(String teacher) {
        teacherMap.remove(teacher);
       studentTeacherMap.values().removeAll(Collections.singleton(teacher));

    }

    public void deleteAllTeachers() {
        teacherMap.clear();
        studentTeacherMap.clear();
    }

    public List<String> getAllStudent() {
        return new ArrayList<>(studentMap.keySet());
    }
}
