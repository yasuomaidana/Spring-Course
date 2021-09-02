package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //Makes reference to the database connection,
// it says that this class could have access to database
@Transactional //Proportionate the capability of make SQL request
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;//Helps to make the connection to DB

    @Override
    @Transactional
    public List<User> getUsers() {
        String query ="FROM User";//Makes reference to our MODEL CLASS
        return entityManager.createQuery(query).getResultList();//Hibernates queries are not SQL queries
    }
}
