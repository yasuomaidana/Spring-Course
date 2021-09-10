package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
        // check to see how it works https://mkyong.com/java/java-password-hashing-with-argon2/
        //https://www.twelve21.io/how-to-use-argon2-for-password-hashing-in-java/
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String secured_password=argon2.hash(1,1024,1,user.getPassword());
        user.setPassword(secured_password);
        entityManager.merge(user);
    }

    @Override
    public boolean verifyEmailPassword(User user) {
        String query = "FROM User WHERE email = :email";
        User response = null;
        try{
            response = (User) entityManager.createQuery(query)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
        }catch (NoResultException e){
            return false;
        }
        // check to see how it works https://mkyong.com/java/java-password-hashing-with-argon2/
        //https://www.twelve21.io/how-to-use-argon2-for-password-hashing-in-java/
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(response.getPassword(),user.getPassword());
    }

    @Override
    public User getUserById(Long ID) {
        return entityManager.find(User.class,ID);
    }
}
