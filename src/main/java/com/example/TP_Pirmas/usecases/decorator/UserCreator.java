package com.example.TP_Pirmas.usecases.decorator;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;


@ApplicationScoped
public class UserCreator implements Serializable, IUserCreator{

    // TODO: Decorator here
    public Boolean createUser() {
        try {
            Thread.sleep(3000);
            System.out.println("THREAD SLEEP DONE");
        } catch (InterruptedException e) {

        }
        return true;
    }
}
