package test;

import dao.StudentDao;
import domain.Student;
import util.SqlSessionUtil;
import vo.StudentAndClassroomVo;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test02 {
    public static void main(String[] args) {
        StudentDao studentDao= SqlSessionUtil.getSession().getMapper(StudentDao.class);
        /*
        * 1.测试：parameterType 使用简单数据类型 String
        * */
        /*Student s=studentDao.getById("A001");
        System.out.println(s);*/

        /*
        * 2.测试：parameterType 使用简单数据类型 int
        * 查询出所有年龄为18岁学员的详细信息
        * */
        /*List<Student> sList=studentDao.select2(18);
        for (Student s:sList){
            System.out.println(s);
        }*/

        /*
        * 3.测试：parameterType
        * 需求：查询出姓名为ww,年龄为20的学员信息
        * 这种具体输入每一个条件的查询方式，在mybatis中无法进行使用
        * */
        /*List<Student> sList=studentDao.select3("ww",20);
        for (Student s:sList){
            System.out.println(s);
        }*/

        //4.对测试3进行修改之后的操作
        /*
        * 如果我们要为sql语句传递多个参数，我们应该将这多个参数封装到一个domain
        * 对象中或者是打包到一个map集合中，进行下列测试
        * 查询姓名为ww，年龄为20的学员信息
        * */
       /* Student s=new Student();
        s.setName("ww");
        s.setAge(20);
        List<Student> sList=studentDao.select4(s);
        for (Student ss:sList){
            System.out.println(ss);
        }*/

       /*
       * 5.测试parameterType 使用map集合作为参数
       * 需求：查询出姓名为zs,年龄为18岁的学员信息
       * */
      /*  Map<String,Object> map=new HashMap<String, Object>();
        map.put("name","ww");
        map.put("age",20);
        List<Student> sList=studentDao.select5(map);
        for (Student student:sList){
            System.out.println(student);
        }*/

        /*
        * 上面5条查询总结：
        *     在实际项目中，使用domain引用类型，或者使用map集合类型，都可以为sql
        *     语句同时传递多条参数，一般情况下，使用domain即可满足需求，但是当domain
        *     不符合需求的情况下，我们一定要考虑使用map来传值
        *
        *     如下面的需求：
        *         请查询出姓名为ww,班级为一年一班的学员的详细信息
        *         select
        *         *
        *         from tbl_student s
        *         join tbl_classroom c
        *         on s.classroomId=c.id
        *         where s.name=#{ww} and c.name=#{一年一班}
        *
        *         在实际项目开发中，一定要学会使用sql传值的这几种方式，但是对于在<select>
        *         中的paramterType属性，一般我们都是省略不懈的
        * */

        /*
        * 6.测试：根据id查单条，sql使用${}来接受值
        * */
        /*Student s=studentDao.select6("A001");
        System.out.println(s);*/

        /*
        * 7.测试：like模糊查询 方式1：使用${} 掌握程度：了解
        *
        * */
        /*List<Student> studentList=studentDao.select7("z");
        for (Student student:studentList){
            System.out.println(student);
        }*/

        /*
        * 8.测试：like模糊查询 方式2：使用#{} 掌握程度：了解
        * */
       /* List<Student> studentList=studentDao.select8("%z%");
        for (Student student:studentList){
            System.out.println(student);
        }*/

       /*
       * 9.测试：like模糊查询 方式3：使用#{} 掌握程度：掌握
       * */
      /* List<Student> studentList=studentDao.select9("z");
       for (Student student:studentList){
           System.out.println(student);
       }*/

       //总结：测试8和测试9的区别在于，一个在程序中加入%，一个不在程序中加入%

        /*
        * 10.测试：resultType 返回String类型
        * 需求：查询编号为A001学员的姓名
        * */
       /* String name=studentDao.select10("A001");
        System.out.println(name);*/

       /*
       * 11.测试：resultType 返回String类型集合
       * 需求：查询出所有学生的姓名
       * */
      /* List<String> stringList=studentDao.select11();
       for (String name:stringList){
           System.out.println(name);
       }*/

      /*
      * 12.测试：resultType 返回int类型
      * 需求：查询出表中一共有多少条信息
      *
      * 在mapper文件中，一定要写上resultType，这个非常重要！！！！
      * */
      /*int count=studentDao.select12();
        System.out.println(count);*/

      /*
      * 13.测试：resultType 返回domain引用数据类型Student
      * */

      /*
      * 14.测试：resultType 返回map类型
      * */
      //下面是一些关于sql语句执行过程的讨论
      /*
       返回类型是student的讨论
      * <select id="" reultType="Student">
      *     select* from tbl_student2
      * </select>
      * 当执行了sql语句之后，通过查询得到的结果 id name age
      * 根据返回值类型，会自动为我们创建出来一个该类型的对象，由该对象将查询结果封装起来
      * Student s1=new Student()
      * s.setId("A001");
      * s.setName("zs");
      * s.setAge(18);
      * s.setAddress("BeiJing");
      *
      * 当查询出第二条记录，根据返回值类型，再一次创建出来一个对象，封装第二条记录
      * Student s2=new Student()
      * s.setId("A002");
      * s.setName("ls");
      * s.setAge("19");
      * s.setAddress("TianJing");
      * ...
      * ...
      * 一直往下，多条记录封装成为多个Student对象
      * 系统会自动的为我们创建出来一个List集合来保存这些对象
      * List<Student> sList=new ArrayList<>();
      * sList.add(s1);
      * sList.add(s2);
      * ...
      * ...
      * 以上便是返回一个学生类型的讨论
      * -------------------------------------------------------------------
      *
      * 返回值是map集合的讨论
      * <select id="" resultType="map">
      *    select * from tbl_student2
      * <select>
      * 当执行了sql语句之后，通过查询得到的结果 id name age
      * 根据返回值类型，会自动为我们创造出来一个该类型的对象，由该对象将查询的结果保存起来
      * Map<String,Object> map1=new HashMap<>()
      * map1.put("id","A001");
      * map1.put("name","ww"):
      * map1.put("age",23);
      *
      * 当查询出来了第二条记录，根据返回值类型，再一次创建出来一个对象，保存第二个记录的值
      * Map<String,Object> map2=new HashMap<>();
      * map2.put("id","A002");
      * map2.put("name","ls");
      * map2.put("age",19):
      * ...
      * ...
      *多条记录封装为多个map对象
      * 系统会自动的为我们创建出来一个List集合来保存这些map对象
      * List<Map<String,Object>> mapList=new ArrayList<>()
      * mapList.add(map1);
      * mapList.add(map2);
      * ...
      * ...
      * --------------------------------------------------------
      *
      * 对于sql语句查询的结果，我们使用domain来封装这些结果很方便，为什么还要使用Map集合进行封装？
      *     因为对于查询结果，很多情况，使用domain封装不了，所以我们会想到使用map来保存结果
      *     例如：
      *         需求：根据姓名分组，查询出来每一个姓名对应的数量
      *         叫wyf的有多少人
      *         叫lh的由多少人
      *         ...
      *
      *         select name,count(*) from tbl_student group by name
      *         对于以上的查询结果，使用domain很明显不能惊醒封装，domain由name属性，
      *         但是没有count属性，但是如果使用map一定可以保存查询到的结果
      *
      * */
      /*List<Map<String,Object>> mapList=studentDao.select14();
      for (Map<String,Object> map:mapList){
          Set<String> set=map.keySet();
          for (String key:set){
              System.out.println("key:"+key);
              System.out.println("value:"+map.get(key));
          }
      }*/

      /*
      * 15.测试 resultType 当数据库表字段名称和domain类属性名称不一样时的处理 方式一：起别名
      *
      * */
      /*List<Student> sList=studentDao.select15();
      for (Student s:sList){
          System.out.println(s);
      }*/

      /*
      * 16.测试 resultType 当数据库表字段名称和domain类属性名称不一致时处理 方式二：ResultMap
      *
      * */
      /*List<Student> sList=studentDao.select16();
      for (Student s:sList){
          System.out.println(s);
      }*/

      /*
      * 17.测试 动态sql where标签+if标签
      * */
      /*Student student=new Student();
      student.setName("l");
      student.setAddress("T");
      List<Student> sList=studentDao.select17(student);
      for (Student student1:sList){
          System.out.println(student1);
      }*/

      /*
      * 18.测试 动态sql forcach标签
      * 查询编号为A001 A002 A003的学员信息
      * */
      /*String strArr[]={"A001","A002","A003"};
      List<Student> sList=studentDao.select18(strArr);
      for (Student student:sList){
          System.out.println(student);
      }*/

      /*
      * 19.测试sql片段
      * */
     /* *//*Student student;
      student=studentDao.select19("AOO2");
      System.out.println(student);*//*
      Student student=studentDao.select19("A003");
        System.out.println(student);
        //System.out.println(studentDao.select19("A003"));*/

    /*
    * 20.测试：多表联查 查询出学僧姓名和班级名称
    * */
    /*   List<Map<String,Object>> mapList=studentDao.select20();
    for (Map<String,Object> map:mapList){
        Set<String> set=map.keySet();
        for (String key:set){
            System.out.println("key"+key);
            System.out.println("value:"+map.get(key));
        }
    }*/

     /*
     * 21.测试：多表联查 查询出学生和班级信息，加VO
     *    在实际项目开发中，如果需要为前端展现数据，使用一个domain类型不足以表现出来
     *    这些数据，这时我们可以考虑使用两种技术来实现
     *    分别为：
     *          使用map以及使用vo
     *    例如我们现在需求
     *          查询出学生和班级所有信息
     *          得到的结果 使用学生的domain或者班级的domain都不能够封装这些结果
     *          所以我们可以使用map去保存这些信息
     *          同时我们也可以使用vo类来保存这些信息
     *
     *          vo指的是创建出来一个类，这个类中的属性时完全由我们自己定义，属性会保存所有需要展现的
     *          信息，例如我们现在的这个例子，我们可以使用vo来封装所有与学生和班级相关的信息
     * */
     /*List<StudentAndClassroomVo> voList=studentDao.select21();
     for (StudentAndClassroomVo vo:voList){
         System.out.println(vo);
     }*/

     /*
     * 22.测试：多表联查 查询出带有字母z的学生和班级信息
     *
     * */
     List<StudentAndClassroomVo> voList=studentDao.select22("z");
     for (StudentAndClassroomVo vo:voList){
         System.out.println(vo);
         //C:\Users\yang hui\IdeaProjects\JDBCTest\CRMTest04\src\service\StudentService.java
     }
    }
}
