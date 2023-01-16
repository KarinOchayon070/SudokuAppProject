package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.util.TimerTask;

public class BackupAndRestore {
	public static void backup(String fromPathFile, String toPathBackup, long delay, long period) {
		Timer timer = new Timer();
		
		TimerTask backupTask = new TimerTask() {
	        public void run() {
	            try {
	                File source = new File(fromPathFile);
	                File destination = new File(toPathBackup);
	                Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	    
	    timer.scheduleAtFixedRate(backupTask, delay, period);
	}
	
	
	
	public static String restore(String fromFilePath) {
	    try {
	        byte[] data = Files.readAllBytes(Paths.get(fromFilePath));
	        
	        return new String(data);
	    } catch (IOException e) {
	        e.printStackTrace();
	        
	        return null;
	    }
	}

}
