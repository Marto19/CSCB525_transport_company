package org.example.SerializationDesrialization;

import org.example.entity.TripDetails;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TripDetailsSerializer {
    public void serialize(TripDetails tripDetails, String filename) {
        try {
            System.out.println("YEA IMMA WRITE THE FILE");
            FileOutputStream fileOut = new FileOutputStream(filename);
            System.out.println("1.");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            System.out.println("2.");
            out.writeObject(tripDetails);
            out.close();
            fileOut.close();
            System.out.println("YEA, IM DONE");
        } catch (FileNotFoundException f) {
            System.out.println("File not found: " + f.getMessage());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

