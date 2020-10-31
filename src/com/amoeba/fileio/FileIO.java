package com.amoeba.fileio;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public interface FileIO {

    void saveGame(List<String> game, String saveName) throws IOException;

    String loadGame(String fileName);

    Properties getProperties();

}
