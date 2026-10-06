package org.example;

import java.util.ArrayList;
import java.util.List;
//am folosit command
public class ProcessAlerts implements Command {
    private static final Severity PROPAGATION_THRESHOLD = Severity.HIGH;

    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();
            List<String> lines = new ArrayList<>();

            Alert alert;
            while ((alert = db.pollAlert()) != null) {
                lines.add("PROCESS ALERT: " + alert.getIpAddress()
                        + ": severity = " + alert.getSeverity()
                        + " && message = " + alert.getMessage());

                if (alert.getSeverity().compareTo(PROPAGATION_THRESHOLD) >= 0) {
                    for (String ip : db.getDependencyGraph().reachableFrom(alert.getIpAddress())) {
                        if (db.markAtRisk(ip)) {
                            lines.add("AT RISK: " + ip);
                        }
                    }
                }
            }

            if (lines.isEmpty()) {
                return null;
            }
            return String.join("\n", lines);

        } catch (Exception e) {
            return "PROCESS ALERTS: Error processing line " + lineNo;
        }
    }
}
