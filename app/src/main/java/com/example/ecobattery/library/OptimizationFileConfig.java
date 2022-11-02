package com.example.ecobattery.library;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OptimizationFileConfig {

    private static File file = new File("optimized_packages.txt");
    private List<String> optimizedPackages;

    public OptimizationFileConfig() {
        initializeFile();
    }

    private void initializeFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean doesPackageExist() {
        // TODO Check if package exists
        return false;
    }

    public static boolean addPackage(String packageToAdd) {
        try {
            if (!doesPackageExist()) {
                FileWriter myWriter = new FileWriter(file.getName());
                myWriter.write(packageToAdd + System.lineSeparator());

                myWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
