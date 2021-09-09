package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        //Hibernate makes references to CLASSES not to table or columns
        String query ="FROM User";//Makes reference to our MODEL CLASS
        return entityManager.createQuery(query).getResultList();//Hibernates queries are not SQL queries
    }

    @Override
    public void delete(Long ID) {
        User user = entityManager.find(User.class,ID);
        entityManager.remove(user);
    }

    @Override
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override
    public boolean verifyEmailPassword(User user) {
        String query = "FROM User WHERE email = :email AND password = :password";
        Object response = null;
        try{
            response = entityManager.createQuery(query)
                    .setParameter("email", user.getEmail())
                    .setParameter("password", user.getPassword())
                    .getSingleResult();
        }catch (NoResultException e){
        }
        return response!=null;
    }
}
