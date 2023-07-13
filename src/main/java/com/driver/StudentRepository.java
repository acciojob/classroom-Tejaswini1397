package com.driver;

import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.HashMap;

@Repository
public class StudentRepository {
    Map<String,Student>studentMap=new HashMap<>();
    Map<String,Teacher>teacherMap=new HashMap<>();
    Map<String,String>studentTeacherMap=new HashMap<>();


    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {

        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
            studentTeacherMap.put(student,teacher);
    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {

        return teacherMap.get(name);
    }

    public List<String> getStudentByTeacherName(String teacher) {
        List<String>list=new ArrayList<>();
        for (String student:studentTeacherMap.keySet()){
            if(studentTeacherMap.get(student).equals(teacher)){
                list.add(studentTeacherMap.get(student));
            }
        }
        return list;
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
