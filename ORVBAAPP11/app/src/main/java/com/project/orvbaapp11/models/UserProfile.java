package com.project.orvbaapp11.models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserProfile {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private boolean approved;
    private String location;
    private String services;


    public UserProfile(String name, String email, String phoneNumber, String password, String role, boolean approved) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.approved = approved;
    }

    public UserProfile(String name, String email, String phoneNumber, String password, boolean approved, String location, String services) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.approved = approved;
        this.location = location;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }


    public void approveMechanic(FirebaseAuth firebaseAuth) {
        if (this.role.equals("mechanic") && !this.approved) {
            // Use Firebase authentication to approve the mechanic
            firebaseAuth.getCurrentUser().updateEmail(this.email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Update the approved field to true
                                approved = true;
                            } else {
                                // Handle the error
                            }
                        }
                    });
        }
    }


}
