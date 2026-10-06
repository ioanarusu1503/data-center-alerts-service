package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
//am folosit singleton
public class Database {
    private static Database instance = null;

    private Set<Server> servers;
    private Set<ResourceGroup> resourceGroups;
    private Set<Alert> alerts;

    private Map<String, Server> serversByIp;
    private Map<String, ResourceGroup> groupsByIp;
    private DependencyGraph dependencyGraph;
    private AlertQueue alertQueue;
    private Set<String> atRisk;


    private Database() {
        init();
    }

    private void init() {
        this.servers = new HashSet<>();
        this.resourceGroups = new HashSet<>();
        this.alerts = new HashSet<>();
        this.serversByIp = new HashMap<>();
        this.groupsByIp = new HashMap<>();
        this.dependencyGraph = new DependencyGraph();
        this.alertQueue = new AlertQueue();
        this.atRisk = new HashSet<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void reset() {
        init();
    }

    public void addServer(Server server) {
        this.servers.add(server);
        this.serversByIp.putIfAbsent(server.getIpAddress(), server);
    }

    public void addServers(Collection<Server> servers) {
        for (Server server : servers) {
            addServer(server);
        }
    }

    public Optional<Server> findServer(String ipAddress) {
        return Optional.ofNullable(serversByIp.get(ipAddress));
    }

    public void addResourceGroup(ResourceGroup group) {
        this.resourceGroups.add(group);
        this.groupsByIp.putIfAbsent(group.getIpAddress(), group);
    }

    public void addResourceGroups(Collection<ResourceGroup> groups) {
        for (ResourceGroup group : groups) {
            addResourceGroup(group);
        }
    }

    public Optional<ResourceGroup> findGroup(String ipAddress) {
        return Optional.ofNullable(groupsByIp.get(ipAddress));
    }

    public boolean removeResourceGroup(String ipAddress) {
        groupsByIp.remove(ipAddress);
        return resourceGroups.removeIf(g -> g.getIpAddress().equals(ipAddress));
    }

    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    public void enqueueAlert(Alert alert) {
        this.alertQueue.add(alert);
    }

    public Alert pollAlert() {
        return this.alertQueue.poll();
    }

    public DependencyGraph getDependencyGraph() {
        return dependencyGraph;
    }

    public boolean markAtRisk(String ipAddress) {
        return atRisk.add(ipAddress);
    }

    public Set<String> getAtRisk() {
        return atRisk;
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