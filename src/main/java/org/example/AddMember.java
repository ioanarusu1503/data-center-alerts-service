package org.example;
//am folosit command
import java.util.Optional;

public class AddMember implements Command {
    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();

            if (params.length < 2 || params[1] == null || params[1].trim().isEmpty()) {
                throw new MissingIpAddressException();
            }

            String ip = params[1].trim();

            String name = params[2];
            String role = params[3];
            String email = params[4];
            String department = params[5];
            Integer clearanceLevel = null;
            if (params.length > 6 && !params[6].isEmpty()) {
                clearanceLevel = Integer.parseInt(params[6]);
            }

            User member = UserFactory.createUser(name, role, email, department, clearanceLevel);
            Optional<ResourceGroup> groupOpt = db.findGroup(ip);
            if (groupOpt.isPresent()) {
                groupOpt.get().addMember(member);
                return "ADD MEMBER: " + ip + ": name = " + name + " && role = " + role;
            } else {
                return "ADD MEMBER: Group not found: ipAddress = " + ip;
            }

        } catch (MissingIpAddressException e) {
            return "ADD MEMBER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "ADD MEMBER: Error processing line " + lineNo;
        }
    }
}