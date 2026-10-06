package org.example;
//am dolosit command
public class AddEvent implements Command {
    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();

            if (params.length < 5) {
                return null;
            }

            String typeStr = params[1].trim();
            String severityStr = params[2].trim();
            String ipAddress = params[3].trim();
            String message = params[4].trim();

            AlertType type = AlertType.valueOf(typeStr.toUpperCase());
            Severity severity = Severity.valueOf(severityStr.toUpperCase());

            Alert alert = new Alert(type, severity, message, ipAddress);
            db.addAlert(alert);

            db.getServers().stream()
                    .filter(s -> s.getIpAddress().equals(ipAddress))
                    .findFirst()
                    .ifPresent(server -> {
                        db.findGroup(ipAddress).ifPresent(group -> {
                            server.setMonitorGroup(group);
                            server.processAlert(alert);
                        });
                    });

            return "ADD EVENT: " + ipAddress + ": type = " + typeStr + " && severity = " + severityStr + " && message = " + message;

        } catch (Exception e) {
            return null;
        }
    }
}