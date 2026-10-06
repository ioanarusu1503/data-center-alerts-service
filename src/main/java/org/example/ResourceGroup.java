package org.example;

import java.util.ArrayList;
import java.util.List;


public class ResourceGroup {
    private List<User> members;
    private String ipAddress;

    public ResourceGroup(String ipAddress) {
        this.ipAddress = ipAddress;
        this.members = new ArrayList<>();
    }

    public void addMember(User user) {
        this.members.add(user);
    }

    public List<User> getMembers() {
        return new ArrayList<>(this.members);
    }

    public boolean removeMember(User user) {
        return this.members.removeIf(m -> m.getName().equals(user.getName()) &&
                m.getRole().equals(user.getRole()));
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void handleAlert(Alert alert) {
    }
}