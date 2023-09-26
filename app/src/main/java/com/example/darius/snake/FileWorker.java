package com.example.darius.snake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class FileWorker {

    public static String read(File file) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        if (!file.exists()) {
            try {
                file.createNewFile();
                write("0", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }


    public static void write(String text, File file) {

        try {
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void update(String newText, File file) throws FileNotFoundException {
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append(newText);
            write("0", file);
            write(sb.toString(), file);
        }
    }
}
