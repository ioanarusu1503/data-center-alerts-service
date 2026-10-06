package org.example;
//am folosit command
public class AddGroup implements Command {
    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();
            if (params.length < 2 || params[1] == null || params[1].trim().isEmpty()) {
                throw new MissingIpAddressException();
            }

            String ip = params[1].trim();
            db.addResourceGroup(new ResourceGroup(ip));
            return "ADD GROUP: " + ip;

        } catch (MissingIpAddressException e) {
            return "ADD GROUP: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "ADD GROUP: Error processing line " + lineNo;
        }
    }
}