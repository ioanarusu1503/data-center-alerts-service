package org.example;
//am folosit factory
public class UserFactory {
    public static User createUser(String name, String role, String email, String department, Integer clearenceLevel) {
        switch (role) {
            case "Operator":
                return new Operator(name, role, email, department);
            case "Admin":
                return new Admin(name, role, email, department, clearenceLevel);
            default:
                return new User(name, role, email);
        }
    }
}