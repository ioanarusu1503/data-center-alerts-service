package org.example;
//am folosit command
import java.util.Optional;

public class RemoveMember implements Command {
    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();
            if (params.length < 2 || params[1] == null || params[1].trim().isEmpty()) {
                throw new MissingIpAddressException();
            }
            String ip = params[1].trim();
            if (params.length < 4 || params[2] == null || params[2].trim().isEmpty() ||
                    params[3] == null || params[3].trim().isEmpty()) {
                throw new UserException();
            }
            String name = params[2].trim();
            String role = params[3].trim();

            Optional<ResourceGroup> groupOpt = db.findGroup(ip);
            if (groupOpt.isEmpty()) {
                return "REMOVE MEMBER: Group not found: ipAddress = " + ip;
            }

            ResourceGroup group = groupOpt.get();
            boolean removed = group.removeMember(new User(name, role, ""));

            if (removed) {
                return "REMOVE MEMBER: " + ip + ": name = " + name + " && role = " + role;
            } else {
                return "REMOVE MEMBER: Member not found: ipAddress = " + ip + ": name = " + name + " && role = " + role;
            }

        } catch (MissingIpAddressException e) {
            return "REMOVE MEMBER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (UserException e) {
            return "REMOVE MEMBER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "REMOVE MEMBER: Error processing line " + lineNo;
        }
    }
}