package com.example.TP_Pirmas.usecases.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class UserCreatorDecorator implements IUserCreator{

    @Inject
    @Delegate
    @Any
    IUserCreator userCreator;

    @Override
    public Boolean createUser() {
        try {
            userCreator.createUser();
            Thread.sleep(7000);
            System.out.println("Decorator DONE");
        } catch (InterruptedException e) {

        }
        return true;
    }
}
