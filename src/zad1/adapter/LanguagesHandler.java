package zad1.adapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface LanguagesHandler {

    String path = System.getProperty("user.home") + "\\Downloads\\TPO2_GB_S19982\\TPO2_GB_S19982\\Data\\languagesHandled.txt";
    File file = new File(path);

    default void addNewLanguageToList (String language) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write("\n"+language);
        fileWriter.flush();
        fileWriter.close();
    }

}
