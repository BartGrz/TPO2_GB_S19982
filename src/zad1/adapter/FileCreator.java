package zad1.adapter;

import java.io.File;
import java.io.IOException;

public interface FileCreator extends LanguagesHandler {
    String dictionary_path = System.getProperty("user.home") + "\\Downloads\\TPO2_GB_S19982\\TPO2_GB_S19982\\src";

    /**
     * creates new File if file hasn't been already created
     *
     * @param file_name
     * @return new File
     * @throws IOException
     */
    default File createFile(String file_name) throws IOException {
        String[] language = file_name.split("_");
        String [] language_short = language[1].split(".");
        addNewLanguageToList(language_short[0]);
        File file = new File(dictionary_path, file_name);
        file.createNewFile();
        return file;
    }
}
