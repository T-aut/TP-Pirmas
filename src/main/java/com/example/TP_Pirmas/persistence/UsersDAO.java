package com.example.TP_Pirmas.persistence;

import com.example.TP_Pirmas.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class UsersDAO {

    @Inject
    private EntityManager em;

    public void persist(User player){
        this.em.persist(player);
    }

    public User findOne(Integer id){
        return em.find(User.class, id);
    }

    public User update(User player){
        return em.merge(player);
    }
}