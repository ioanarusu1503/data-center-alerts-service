package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Optional;
//am folosit singleton
public class Database {
    private static Database instance = null;

    private Set<Server> servers;
    private Set<ResourceGroup> resourceGroups;
    private Set<Alert> alerts;


    private Database() {
        this.servers = new HashSet<>();
        this.resourceGroups = new HashSet<>();
        this.alerts = new HashSet<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addServer(Server server) {
        this.servers.add(server);
    }

    public void addServers(Collection<Server> servers) {
        this.servers.addAll(servers);
    }

    public void addResourceGroup(ResourceGroup group) {
        this.resourceGroups.add(group);
    }

    public void addResourceGroups(Collection<ResourceGroup> groups) {
        this.resourceGroups.addAll(groups);
    }

    public Optional<ResourceGroup> findGroup(String ipAddress) {
        return resourceGroups.stream()
                .filter(g -> g.getIpAddress().equals(ipAddress))
                .findFirst();
    }

    public boolean removeResourceGroup(String ipAddress) {
        return resourceGroups.removeIf(g -> g.getIpAddress().equals(ipAddress));
    }

    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    public Set<Server> getServers() {
        return servers;
    }

    public Set<ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }

    public Set<Alert> getAlerts() {
        return alerts;
    }
}