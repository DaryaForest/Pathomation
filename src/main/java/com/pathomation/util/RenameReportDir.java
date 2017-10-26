package com.pathomation.util;

import java.io.*;

/**
 * Renames report directory;
 * Adds to old report directory browser version;
 * Finds old location of report directory, renames it to new;
 */

public class RenameReportDir {
    static String newFolderNameFile = "uploads/newFolderName.txt";
    static String oldFolderNameFile = "uploads/oldFolderName.txt";

    public static void main(String args[]) throws IOException, InterruptedException {
        renameReportDir();
    }

    public static String read(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        exists(fileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }


    public static void renameReportDir() throws InterruptedException, IOException {
        String oldFolderName = read(RenameReportDir.oldFolderNameFile).trim();
        String newFolderName = read(newFolderNameFile).trim();
        renameDirectory(oldFolderName, newFolderName);
    }

    public static void renameDirectory(String fromDir, String toDir) throws IOException {

        File from = new File(fromDir);
        if (from.exists()) {

            File to = new File(toDir);
            if (to.exists()) {
                delete(to);
            }
            if (from.renameTo(to)) {
                System.out.println("Successfully renamed! Current report directory is " + to);
            } else {
                System.out.println("Error renamed");
            }
        } else {
            System.out.println("File " + fromDir + " doesn't exist");
        }
    }

    public static void delete(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles())
                delete(f);
            file.delete();
        } else {
            file.delete();
        }
    }


}