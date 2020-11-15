package org.kd.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lib {

    public String convertDate(Date dateToConvert) {
        return new SimpleDateFormat("dd.MM.yyyy")
                .format(dateToConvert);
    }

    public File getLastModified(String directoryFilePath)
    {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isDirectory);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }

}