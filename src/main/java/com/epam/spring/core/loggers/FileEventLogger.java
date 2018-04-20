package com.epam.spring.core.loggers;

import com.epam.spring.core.events.Event;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements IEventLogger {

    private String fileName;
    private File logFile;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    protected void init() throws IOException {
        logFile = new File(fileName);
        if(!logFile.canWrite()){
         throw new IOException("log file is unable to write data");
        }
    }


    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(logFile, "\n" + event.toString(), Charsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
