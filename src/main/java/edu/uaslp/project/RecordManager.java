package edu.uaslp.project;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class RecordManager {
    private final int numMaxRecords;
    private final String nameFile;

    public RecordManager(int maxRecords, String name) {
        this.numMaxRecords = maxRecords;
        this.nameFile = name;
    }

    public void save(Record record) {
        if (record.getNamePlayer().equals(""))
            throw new RecordsException("Empty name not allowed");

        List<Record> records = getRecords();

        records.add(record);
        records.sort((o1, o2) -> o2.getScorePlayer() - o1.getScorePlayer());


        try (FileWriter fileWriter = new FileWriter(nameFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            int counter = 0;

            for (Record record1 : records) {
                printWriter.append(record1.getNamePlayer()).append(",").print(record1.getScorePlayer() + "\n");
                counter++;
                if (counter == numMaxRecords) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Record> getRecords() {
        List<Record> records = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] items = currentLine.split(",");

                records.add(new Record(items[0], Integer.parseInt(items[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}