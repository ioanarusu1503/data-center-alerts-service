package org.example;
//am folosit builder
public class Server {
    private String ipAddress;
    private Location location;
    private User owner;

    private String hostname;
    private ServerStatus status;
    private Integer cpuCores;
    private Integer ramGb;
    private Integer storageGb;

    private Server(Builder builder) {
        this.ipAddress = builder.ipAddress;
        this.location = builder.location;
        this.owner = builder.owner;
        this.hostname = builder.hostname;
        this.status = builder.status;
        this.cpuCores = builder.cpuCores;
        this.ramGb = builder.ramGb;
        this.storageGb = builder.storageGb;
    }

    public static class Builder {
        private String ipAddress;
        private Location location;
        private User owner;

        private String hostname;
        private ServerStatus status;
        private Integer cpuCores;
        private Integer ramGb;
        private Integer storageGb;

        public Builder(String ipAdress, Location location, User owner){
            this.ipAddress = ipAdress;
            this.owner = owner;
            this.location = location;
        }

        public Builder setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }
        public Builder setCpuCores(Integer cpuCores) {
            this.cpuCores = cpuCores;
            return this;
        }
        public Builder setRamGb(Integer ramGb) {
            this.ramGb = ramGb;
            return this;
        }
        public Builder setStorageGb(Integer storageGb) {
            this.storageGb = storageGb;
            return this;
        }
        public Builder setStatus(ServerStatus status) {
            this.status = status;
            return this;
        }
        public Server build() {
            return new Server(this);
        }
    }
    private ResourceGroup monitorGroup;

    public void setMonitorGroup(ResourceGroup group) {
        this.monitorGroup = group;
    }

    public void processAlert(Alert alert) {
        if (monitorGroup != null) {
            monitorGroup.handleAlert(alert);
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }
}