package test;

import domain.Student;
import service.Impl.StudentServiceImpl;
import service.StudentService;
import util.ServiceFactory;

import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        StudentService ss=new StudentServiceImpl();
        StudentService service=(StudentService) ServiceFactory.getService(ss);
        //传入需要代理的服务，创建出代理对象

        //1.根据id查单条
        /*Student stu2=service.getById("A001");
        System.out.println(stu2);*/

        //2.遍历数据库表中的数据
        /*List<Student> sList=service.getAll();
        for (Student s:sList){
            System.out.println(s);
        }*/

        //3.对数据库表进行添加的操作
        Student stu=new Student("A004","yy",15,"BeiJing");
        service.save(stu);

        List<Student> sList1=service.getAll();
        for (Student s:sList1){
            System.out.println(s);
        }
    }
}
