package com.example.TP_Pirmas.persistence;

import com.example.TP_Pirmas.entities.Role;
import com.example.TP_Pirmas.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class RolesDAO {

    @Inject
    private EntityManager em;

    public void persist(Role role){
        this.em.persist(role);
    }

    public User findOne(Integer id){
        return em.find(User.class, id);
    }

    public User update(User user){
        return em.merge(user);
    }
}
