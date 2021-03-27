package dao;

import domain.Student;
import vo.StudentAndClassroomVo;

import javax.swing.plaf.synth.SynthTableUI;
import java.util.List;
import java.util.Map;

//此处使用动态代理进行创建代理对象，连中间的接口实现对象都省了

//这里在接口中定义的方法，在.xml文件中进行了编写，都会映射到mapper文件中
//但是需要这里的方法名与.xml文件中的id名一致
public interface StudentDao {
    public Student getById(String id);
    public void save(Student stu);
    List<Student> getAll();
    Student select1(String A001);
    List<Student> select2(int i);

    //在数据库查询中，如果需要有多个条件查询，需要放入的不是每一个条件，
    //而是下面的select4方法
    List<Student> select3(String name,int age);
    List<Student> select4(Student stu);
    List<Student> select5(Map<String,Object> map);
    Student select6(String getById);
    List<Student> select7(String moHuChaXun);
    List<Student> select8(String moHuChaXun);
    List<Student> select9(String moHuChaXun);
    String select10(String getById);
    List<String> select11();
    int select12();
    List<Map<String,Object>> select14();
    List<Student> select15();
    List<Student> select16();
    List<Student> select17(Student s);
    List<Student> select18(String[] strArr);
    Student select19(String A001);
    List<Map<String,Object>> select20();

    //后面还有两个StudentAndClassroomVo的查询语句
    List<StudentAndClassroomVo> select21();
    List<StudentAndClassroomVo> select22(String name);

}
