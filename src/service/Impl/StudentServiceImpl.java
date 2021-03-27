package service.Impl;

import dao.StudentDao;
import domain.Student;
import service.StudentService;
import util.SqlSessionUtil;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao= SqlSessionUtil.getSession().getMapper(StudentDao.class);
    @Override
    public Student getById(String id) {
        Student stu=studentDao.getById(id);
        return stu;
    }

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public List<Student> getAll() {
        List<Student> sList=studentDao.getAll();
        return sList;
    }
}
