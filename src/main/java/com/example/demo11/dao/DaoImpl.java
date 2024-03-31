package com.example.demo11.dao;

import com.example.demo11.Model.User;
import jakarta.persistence.*;

import java.util.List;

public class DaoImpl implements dao {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("user");
    public List<User> users;
    @Override
    public void saveUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (user.getId()== 0) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
   public boolean checkLogin(String username, String password) {
        String jpql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            User user = em.createQuery(jpql, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException nre) {
            nre.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean isEmailExists(String email) {
        String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

    /*public boolean checkLogin(String username, String password) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query queryObj = em
                    .createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");
            queryObj.setParameter("username", username);
            queryObj.setParameter("password", password);
            User userEntity = (User) queryObj.getSingleResult();
            em.getTransaction().commit();
            return userEntity != null ;

        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }*/
@Override
    public void deleteUser(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @Override
    public void updateUser(int id, User updatedUser) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                user.setUsername(updatedUser.getUsername());
                user.setFirstname(updatedUser.getFirstname());
                user.setLastname(updatedUser.getLastname());
                user.setEmail(updatedUser.getEmail());
                user.setPhonenumber(updatedUser.getPhonenumber());
                user.setDepartement(updatedUser.getDepartement());
                user.setSalaire(updatedUser.getSalaire());
                em.merge(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public  List<User> Lister(){
        String jpql = "SELECT u FROM User u";
        EntityManager em = emf.createEntityManager();
        this.users = em.createQuery(jpql, User.class).getResultList();
        System.out.println("lister est bien");
return users;
    }
}
