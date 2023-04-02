package ru.bor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bor.model.Student;

import java.util.List;

public class StudentDao {
    SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int getRandomMark(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void addStudent(Student student) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
        }
    }
    public void removeStudent(Student student){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.remove(student);
            session.getTransaction().commit();
        }
    }
    public void update(Student s_new, Student s_old) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            s_old.setName(s_new.getName());
            s_old.setMark(s_new.getMark());
            session.getTransaction().commit();
            System.out.println("update is ok");
        }
    }
    public List<Student> findAllStudents(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> studentsList = session.createQuery("from Student").list();
            session.getTransaction().commit();
            return studentsList;
        }
    }
    public Student getStudentById(Long id){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }
    public List<Student> findByName(String name) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> studentsList = session.
                    createQuery("SELECT a FROM Student a WHERE a.name like ?1").
                                        setParameter(1, "%"+name+"%").list();
            session.getTransaction().commit();
            return studentsList;
        }
    }
    public boolean removeAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Student").executeUpdate();
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            System.out.println("delete from Student -> failed");
            return false;
        }
    }
}
