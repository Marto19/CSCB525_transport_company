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
}
