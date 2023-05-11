package org.propsht.utils;

import java.io.File;
import java.util.Date;


public class MyFileUtils {

    static public String folderPath = "files/";
    static public String fileNameDownload = "easyinfo.txt";



    public static void main(String[] args) {
        File file = generateTestFile();
        System.out.println();
    }


    public static File generateTestFile() {


        String baseName = "tempfile";
        String extension = "txt";


        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // Create the folder if it doesn't exist
        }


        String fileName = baseName
                + new Date().getTime()
                + "." + extension;
        File fileTemp = new File(folder, fileName);

        //fileTemp.deleteOnExit();
        return fileTemp;

    }
    // wait until file download
    public static File waitTillFileDownloaded(String fileName) throws Exception {
        File file = new File(folderPath, fileName);
        int count = 0;
        while (count <5){
            if (file.exists()){
                count = 0;
                break;
            }
            pause(1000);
            count++;
        }
        if (count > 0) {
            throw new Exception("File is not exist!");
        }

        while (count < 60){
            long fileSizeBefore = file.length();
            pause(1000);
            long fileSizeAfter = file.length();
            if (fileSizeBefore == fileSizeAfter){
                return  file;
            }
            count++;
        }
        throw new Exception("Timeout File  failed");
    }

    // pause
    public static void pause(long msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}





