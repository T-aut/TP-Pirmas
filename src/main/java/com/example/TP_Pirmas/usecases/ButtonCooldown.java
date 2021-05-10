package com.example.TP_Pirmas.usecases;

import com.example.TP_Pirmas.usecases.decorator.UserCreator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class ButtonCooldown implements Serializable {

    @Inject
    private UserCreator userCreator;

    public void startWork() {
        userCreationTask = CompletableFuture.supplyAsync(() -> userCreator.createUser());
    }

    @PostConstruct
    private void printOnCreation() {
        System.out.println("NEW BEAN CREATED");
    }

    private CompletableFuture<Boolean> userCreationTask = null;

    public boolean isUserCreatorRunning() {
        return userCreationTask != null && !userCreationTask.isDone();
    }

    public String getUserCreationStatus() throws ExecutionException, InterruptedException {
        if (userCreationTask == null) {
            return "Add a new user for this project";
        } else if (isUserCreatorRunning()) {
            return "User creation in progress";
        }
        return "Add a new user for this project" + userCreationTask.get();
    }
}
