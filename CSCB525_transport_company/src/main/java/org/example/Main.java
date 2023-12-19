package org.example;

import org.example.configuration.SessionFactoryUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();
    }
}

//TODO: add client table, think about it, connect it with the payments