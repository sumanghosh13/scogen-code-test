package com.java.scogen.process;

import java.io.*;

public class test {

    public static void main(String[] args) throws IOException {

        File file = new File("F:\\testFiles\\jisuTest.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }
}
