package zad1.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public interface FileHandler extends FileCreator {

    String path = System.getProperty("user.home") + "\\Downloads\\TPO2_GB_S19982\\TPO2_GB_S19982\\Data\\languagesHandled.txt";
    File file = new File(path);

    /**
     * Checks if File exists by scanning txt file with all previously added languages
     *
     * @param file_name
     * @return
     * @throws IOException
     */
    private boolean checkIfExists(String file_name) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            if (scanner.next().equals(file_name)) {
                System.out.println("Exists");
                return true;
            }
        }
        return false;
    }

    /**
     * if File does not exist, create new one
     * @param file_name
     * @throws IOException
     */
    default void validateAndCreateFile(String file_name)   {
        try {
            if (!checkIfExists(file_name)) {
                createFile(file_name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

