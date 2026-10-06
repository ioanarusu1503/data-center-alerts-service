package org.example;
//am folosit command
import java.util.Optional;

public class FindMember implements Command {
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
                return "FIND MEMBER: Group not found: ipAddress = " + ip;
            }

            ResourceGroup group = groupOpt.get();
            boolean found = group.getMembers().stream()
                    .anyMatch(m -> m.getName().equals(name) && m.getRole().equals(role));

            if (found) {
                return "FIND MEMBER: " + ip + ": name = " + name + " && role = " + role;
            } else {
                return "FIND MEMBER: Member not found: ipAddress = " + ip + ": name = " + name + " && role = " + role;
            }

        } catch (MissingIpAddressException e) {
            return "FIND MEMBER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (UserException e) {
            return "FIND MEMBER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "FIND MEMBER: Error processing line " + lineNo;
        }
    }
}