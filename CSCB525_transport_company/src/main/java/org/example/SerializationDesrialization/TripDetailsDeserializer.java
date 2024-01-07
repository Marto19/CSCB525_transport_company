package org.example.SerializationDesrialization;

import org.example.entity.TripDetails;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TripDetailsDeserializer {
    public TripDetails deserialize(String filename) {
        TripDetails tripDetails = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tripDetails = (TripDetails) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("TripDetails class not found");
            c.printStackTrace();
        }
        return tripDetails;
    }

//    private String directoryPath;
//
//    public TripDetailsDeserializer(String directoryPath) {
//        this.directoryPath = directoryPath;
//    }
//
//    private File[] getFilesInDirectory() {
//        File folder = new File(directoryPath);
//        return folder.listFiles();
//    }
//
//    private void listAllFiles(File[] listOfFiles) {
//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                System.out.println((i + 1) + ": " + listOfFiles[i].getName());
//            }
//        }
//    }
//
//    private int getUserChoice() {
//        System.out.println("Please input the number of the file you want to deserialize:");
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextInt();
//    }
//
//    private TripDetails deserializeFile(String filename) {
//        TripDetails tripDetails = null;
//        try {
//            FileInputStream fileIn = new FileInputStream(filename);
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            tripDetails = (TripDetails) in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//        } catch (ClassNotFoundException c) {
//            System.out.println("TripDetails class not found");
//            c.printStackTrace();
//        }
//        return tripDetails;
//    }
//
//    public TripDetails deserialize() {
//        File[] listOfFiles = getFilesInDirectory();
//        listAllFiles(listOfFiles);
//        int fileNumber = getUserChoice();
//        String filename = listOfFiles[fileNumber - 1].getPath();
//        return deserializeFile(filename);
//    }
}
