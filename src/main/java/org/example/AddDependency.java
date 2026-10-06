package org.example;
//am folosit command
public class AddDependency implements Command {
    @Override
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();

            if (params.length < 3
                    || params[1] == null || params[1].trim().isEmpty()
                    || params[2] == null || params[2].trim().isEmpty()) {
                throw new MissingIpAddressException();
            }

            String from = params[1].trim();
            String to = params[2].trim();

            if (!db.findServer(from).isPresent()) {
                return "ADD DEPENDENCY: Server not found: ipAddress = " + from + " ## line no: " + lineNo;
            }
            if (!db.findServer(to).isPresent()) {
                return "ADD DEPENDENCY: Server not found: ipAddress = " + to + " ## line no: " + lineNo;
            }
            if (from.equals(to)) {
                return "ADD DEPENDENCY: A server cannot depend on itself: ipAddress = " + from
                        + " ## line no: " + lineNo;
            }
            if (!db.getDependencyGraph().addEdge(from, to)) {
                return "ADD DEPENDENCY: Dependency already exists: " + from + " -> " + to
                        + " ## line no: " + lineNo;
            }

            return "ADD DEPENDENCY: " + from + " -> " + to;

        } catch (MissingIpAddressException e) {
            return "ADD DEPENDENCY: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "ADD DEPENDENCY: Error processing line " + lineNo;
        }
    }
}
