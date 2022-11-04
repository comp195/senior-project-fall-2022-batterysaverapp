package com.example.ecobattery.library;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class OptimizationFileConfig {

    private File file;
    private List<String> optimizedPackages;
    private Context appContext;

    public OptimizationFileConfig(Context appContext) {
        this.appContext = appContext;
        initializeFile();
    }

    public List<String> getOptimizedPackages() {
        return optimizedPackages;
    }

    private void initializeFile() {
        file = new File(appContext.getFilesDir(), "optimized_packages.txt");
        optimizedPackages = new ArrayList<>();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(appContext.openFileInput("optimized_packages.txt"));
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String line;
                while((line=reader.readLine())!=null) {
                    optimizedPackages.add(line);
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean doesPackageExist(String packageToCheck) {
        for (String packages : optimizedPackages) {
            if (packages.equalsIgnoreCase(packageToCheck)) {
                return true;
            }
        }
        return false;
    }

    public void processPackage(String packageToProcess) {
        try {
            if (doesPackageExist(packageToProcess)) {
                InputStreamReader inputStreamReader = new InputStreamReader(appContext.openFileInput("optimized_packages.txt"));
                BufferedReader reader = new BufferedReader(inputStreamReader);

                StringBuilder stringToWrite = new StringBuilder();

                String line;
                while((line=reader.readLine())!=null) {
                    if (!line.equalsIgnoreCase(packageToProcess)) {
                        stringToWrite.append(line).append(System.lineSeparator());
                    }
                }
                reader.close();

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(appContext.openFileOutput("optimized_packages.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write(String.valueOf(stringToWrite));
                outputStreamWriter.close();

                optimizedPackages.remove(packageToProcess);
            } else {
                // ADD PACKAGE
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(appContext.openFileOutput("optimized_packages.txt", Context.MODE_APPEND));
                outputStreamWriter.write(packageToProcess + System.lineSeparator());
                outputStreamWriter.close();

                optimizedPackages.add(packageToProcess);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
