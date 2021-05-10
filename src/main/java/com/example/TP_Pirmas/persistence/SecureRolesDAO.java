package com.example.TP_Pirmas.persistence;

import com.example.TP_Pirmas.entities.Role;
import com.example.TP_Pirmas.interceptors.LoggingInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@Specializes
@LoggingInterceptor
public class SecureRolesDAO extends RolesDAO {

    @Inject
    private EntityManager em;

    public void persist(Role role){
        System.out.println("Persisting role securely");
        this.em.persist(role);
    }
}
