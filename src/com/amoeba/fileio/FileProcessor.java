package com.amoeba.fileio;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class FileProcessor implements FileIO {

    private static final String PROP_PATH = "resources/tictactoe.properties";
    private static final String SAVE_DIR = "saves/";
    private static final String EXTENSION = ".sav";
    private static final String LINE_END = "\n";

    @Override
    public void saveGame(List<String> gameOutput, String fileName) throws IOException {
        File file = new File(createFileName(fileName));
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for (String line : gameOutput) {
            out.write(line);
        }
        out.close();
    }

    @Override
    public String loadGame(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(createFileName(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(LINE_END);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String createFileName(String fileName) {
        return SAVE_DIR + fileName + EXTENSION;
    }

    @Override
    public Properties getProperties() {
        FileInputStream fis = null;
        Properties prop = new Properties();
        try {
            fis = new FileInputStream(PROP_PATH);
            prop.load(fis);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

}
