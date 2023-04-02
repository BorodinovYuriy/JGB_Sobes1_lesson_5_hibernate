package ru.bor;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bor.dao.StudentDao;
import ru.bor.model.Student;
import ru.bor.utils.HiberUtil;
import java.util.List;

public class App {



    public static void main(String[] args) {

        Configuration conf = new Configuration().
                addAnnotatedClass(Student.class);

        SessionFactory sessionFactory = HiberUtil.getSessionFactory(conf);

        StudentDao studentDao = new StudentDao(sessionFactory);

        try {
            studentDao.addStudent(new Student("test1", 1));
            System.out.println(studentDao.findAllStudents().toString());

            List<Student> studRemoveList = studentDao.findAllStudents();
            studRemoveList.forEach(studentDao::removeStudent);

            for (int i = 0; i < 1000; i++) {
                studentDao.addStudent(new Student("S_" + i, studentDao.getRandomMark(2,6) ));
            }

            List<Student> studentsList = studentDao.findAllStudents();
            studentsList.forEach(s -> System.out.println(s.toString()));

            System.out.println("================================");

            List<Student> studentsFinder = studentDao.findByName("S_100");
            System.out.println("_____finder test!!!!");
            System.out.println(studentsFinder.toString());

            Student student = studentsFinder.get(0);
            Student s = new Student("TEST_update",5);
            studentDao.update(s,student);

            List<Student> studentsFinder2 = studentDao.findByName("TEST_update");
            System.out.println("_____finder test!!!!");
            System.out.println(studentsFinder.toString());

        }finally {
            System.out.println("removeAll() is " + studentDao.removeAll());
            HiberUtil.shutdown();
        }
    }

}













