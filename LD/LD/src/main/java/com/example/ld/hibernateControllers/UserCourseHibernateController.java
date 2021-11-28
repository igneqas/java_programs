package com.example.ld.hibernateControllers;

import com.example.ld.ds.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserCourseHibernateController {

    private EntityManagerFactory emf = null;
    private java.lang.Object Object;

    public UserCourseHibernateController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void createUserCourse(){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            //em.persist();
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
}
