import Database.ReadFiles;
import Database.SaveChanges;
import UI.MainDashboard;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ReadFiles.ReadAllFiles();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainDashboard();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                super.run();
                SaveChanges saveChanges = new SaveChanges();
                saveChanges.saveAll();
            }
        });
    }
}