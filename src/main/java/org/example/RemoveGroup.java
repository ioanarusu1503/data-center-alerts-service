package org.example;
//am folosit command
public class RemoveGroup implements Command {
    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();
            if (params.length < 2 || params[1] == null || params[1].trim().isEmpty()) {
                throw new MissingIpAddressException();
            }

            String ip = params[1].trim();
            if (db.removeResourceGroup(ip)) {
                return "REMOVE GROUP: " + ip;
            } else {
                return "REMOVE GROUP: Group not found: ipAddress = " + ip;
            }

        } catch (MissingIpAddressException e) {
            return "REMOVE GROUP: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "REMOVE GROUP: Error processing line " + lineNo;
        }
    }
}