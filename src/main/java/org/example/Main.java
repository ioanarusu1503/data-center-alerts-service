package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        if (args.length < 2) return;

        PathTypes pathType;
        try {
            pathType = PathTypes.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return;
        }

        if (args.length == 2) {
            processFile(pathType, args[1]);
        } else if (args.length == 4) {
            processFile(PathTypes.SERVERS, args[1]);
            processFile(PathTypes.GROUPS, args[2]);
            processFile(PathTypes.LISTENER, args[3]);
        }
    }

    private static void processFile(PathTypes type, String filePath) {
        String outPath = filePath + ".out";
        System.out.println(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath + ".in"))){
             PrintWriter pw = new PrintWriter(new FileWriter(outPath));

            String headerLine = br.readLine();
            if (headerLine == null) return;

            String[] headers = headerLine.split("\\|");

            String line;
            int lineNo = 0;

            while ((line = br.readLine()) != null) {
                lineNo++;
                if (line.trim().isEmpty()) continue;

                String[] params = parseCsvLine(headers, line);
                String result = executeCommand(line, params, lineNo);

                if (result != null && !result.isEmpty()) {
                    pw.println(result);
                }
            }
            pw.close();
            System.out.println("Fisier generat: " + outPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] parseCsvLine(String[] headers, String line) {
        String[] lineParts = line.split("\\|", -1);

        return lineParts;
    }

    private static String executeCommand(String fullLine,String[] params, int lineNo) {

        Command com = null;
        if (fullLine.startsWith("ADD SERVER")) {
            com = new AddServer();
        } else if (fullLine.startsWith("ADD GROUP")) {
            com = new AddGroup();
        } else if (fullLine.startsWith("ADD MEMBER")) {
            com = new AddMember();
        } else if (fullLine.startsWith("FIND GROUP")) {
            com = new FindGroup();
        } else if (fullLine.startsWith("REMOVE GROUP")) {
            com = new RemoveGroup();
        } else if (fullLine.startsWith("FIND MEMBER")) {
            com = new FindMember();
        } else if (fullLine.startsWith("REMOVE MEMBER")) {
            com = new RemoveMember();
        } else if (fullLine.startsWith("ADD EVENT")) {
            com = new AddEvent();
        }

        if (com != null) {
            return com.implement(params, lineNo);
        }
        return null;
    }
}