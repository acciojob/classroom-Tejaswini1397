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
    public List<String> getAllStudent() {
        List<String>list=new ArrayList<>();
        for(String s:studentMap.keySet()){
            list.add(s);
        }
        return list;
    }

    public void deleteTeacherByName(String teacher) {
        teacherMap.remove(teacher);
        List<String>al=studentTeacherMap.remove(teacher);
        for (int i=0;i<al.size();i++){
            String s=al.get(i);
            studentMap.remove(s);
        }

    }

    public void deleteAllTeachers() {
        for(String k: studentTeacherMap.keySet()) {
            teacherMap.remove(k);
            List<String> al = studentTeacherMap.remove(k);
            for(String p: al) {
                if(studentMap.containsKey(p)) {
                    studentMap.remove(p);
                }
            }
        }
    }
}
