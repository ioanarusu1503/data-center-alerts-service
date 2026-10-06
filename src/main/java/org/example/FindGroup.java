package org.example;
//am folosit command
public class FindGroup implements Command {
    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();

            if (params.length < 2 || params[1] == null || params[1].trim().isEmpty()) {
                throw new MissingIpAddressException();
            }

            String ip = params[1].trim();

            if (db.findGroup(ip).isPresent()) {
                return "FIND GROUP: " + ip;
            } else {
                return "FIND GROUP: Group not found: ipAddress = " + ip;
            }

        } catch (MissingIpAddressException e) {
            return "FIND GROUP: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "FIND GROUP: Error processing line " + lineNo;
        }
    }
}