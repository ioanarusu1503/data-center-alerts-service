package org.example;
//am folosit command
public class AddServer implements Command{
    public String implement(String[] params, int lineNo) {
        try {
            Database db = Database.getInstance();
            String ip = params[2];
            //System.out.println(ip);
            if (ip == null || ip.isEmpty()) {
                throw new MissingIpAddressException();
            }
            //System.out.println("hei");
            String ownerName = params[9];
            String ownerRole = params[10];
            String ownerEmail = params[11];
            String ownerDepartment = params[12];
            Integer ownerClearenceLevel = null;
            if(!params[13].isEmpty()){
                ownerClearenceLevel = Integer.parseInt(params[13]);
            }

            if (ownerName == null || ownerName.isEmpty() ||
                    ownerRole == null || ownerRole.isEmpty()) {
                throw new UserException();
            }

            String country = params[4];
            if (country == null || country.isEmpty()) {
                throw new LocationException();
            }
            User owner = UserFactory.createUser(ownerName, ownerRole, ownerEmail, ownerDepartment, ownerClearenceLevel);
            Location location = new Location(country);

            location.setCity(params[5]);
            location.setAddress(params[6]);
            if (!params[7].isEmpty()){
                location.setLatitude(Double.parseDouble(params[7]));
            }
            if (!params[8].isEmpty()) location.setLongitude(Double.parseDouble(params[8]));

            //System.out.println("buna");
            String Hostname = params[1];
            Integer CpuCores = null;
            Integer RamGb = null;
            Integer StorageGb = null;

            if (!params[14].isEmpty()) CpuCores = Integer.parseInt(params[14]);
            if (!params[15].isEmpty()) RamGb = Integer.parseInt(params[15]);
            if (!params[16].isEmpty()) StorageGb = Integer.parseInt(params[16]);

            ServerStatus Status = null;
            if (!params[3].isEmpty()) {
                Status = ServerStatus.valueOf(params[3].toUpperCase());
            } else {
                Status = ServerStatus.UP;
            }
            //System.out.println("test test test");
            Server server = new Server.Builder(ip, location, owner)
                    .setHostname(Hostname)
                    .setCpuCores(CpuCores)
                    .setRamGb(RamGb)
                    .setStatus(Status)
                    .setStorageGb(StorageGb)
                    .build();
            db.addServer(server);
            System.out.println("ADD SERVER: " + ip + ": " + Status);
            return "ADD SERVER: " + ip + ": " + Status;

        } catch (MissingIpAddressException e) {
            return "ADD SERVER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (UserException e) {
            return "ADD SERVER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (LocationException e) {
            return "ADD SERVER: " + e.getMessage() + " ## line no: " + lineNo;
        } catch (Exception e) {
            return "ADD SERVER: Error processing line " + lineNo;
        }
}}