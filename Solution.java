package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы 2
*/

public class Solution {
    public static void main(String[] args) throws Exception {
//        System.setIn(new ByteArrayInputStream("C:\\Users\\afilt\\Desktop\\1.txt".getBytes()));//для теста, потом удалить

        if (args.length == 0) return;

        List<String> list = new ArrayList<>();
        String fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()) {
                list.add(reader.readLine() + "\n");
            }

            int argID = Integer.parseInt(args[1]);
            int rowNumber = 0;
            for (int i = 0; i < list.size(); i++) {
                String substring = list.get(i).substring(0, 8).trim();
                int listID = Integer.parseInt(substring);
                if (listID == argID) {
                    rowNumber = i;
                    break;
                } else {
                    rowNumber = -1;
                }
            }

            if (args[0].equals("-d")) {
                list.remove(rowNumber);
            } else if (args[0].equals("-u")) {
                String id = makeParameter(args[1], 8);
                String productName = makeParameter(args[2], 30);
                String price = makeParameter(args[3], 8);
                String quantity = makeParameter(args[4], 4);
                String result = id + productName + price + quantity + "\n";
                list.set(rowNumber, result);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : list)
                writer.write(line);
        }
    }

    private static String makeParameter(String parameter, int length) {
        parameter = parameter.trim();
        if (parameter.length() < length) {
            StringBuilder builder = new StringBuilder(parameter);
            int spaceCount = length - parameter.length();
            for (int i = 0; i < spaceCount; i++) {
                builder.append(" ");
            }
            parameter = builder.toString();
        } else if (parameter.length() > length) {
            parameter = parameter.substring(0, length);
        }
        return parameter;
    }
}
