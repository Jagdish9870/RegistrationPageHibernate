package com.jack.dao;

import com.jack.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentDao {
    private SessionFactory sessionFactory;
    public StudentDao(SessionFactory sessionFactory){
        super();
        this.sessionFactory=sessionFactory;

    }
    public boolean saveStudent(Student st){
        boolean value=false;
        Session session=sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        int i=(Integer) session.save(st);
        if(i>0){
            value=true;
        }
        tx.commit();
        session.close();
        return value;

    }
}
