package com.fileutil.MoveFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

@Component
@EnableScheduling
public class MoveFiles {
    @Autowired
    Environment environment;


    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void moveFiles() throws IOException {
        String source = environment.getProperty("sourcePath");
        String dest = environment.getProperty("destinationPath");
        System.out.println("source: " + source);
        System.out.println("destination: " + dest);
//        Path sourcePath = Paths.get("F:\\MSMQ\\MoveFile\\MoveFile\\dest");
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(dest);
//        Path destinationPath = Paths.get("F:\\MSMQ\\MoveFile\\MoveFile\\copyme");
        Files.list(sourcePath).forEach(path -> {
            System.out.println(path.getFileName());
            try {
                Files.move(path, Paths.get(destinationPath.toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(path + " moved successfully to " + Paths.get(destinationPath.toString(), path.getFileName().toString()));

            } catch (NoSuchFileException e) {
                System.out.println(e.getMessage() + " moved successfully.");
            } catch (FileAlreadyExistsException e) {
                System.out.println(e.getMessage() + " overridden successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
