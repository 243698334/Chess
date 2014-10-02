package com.kevinychen.chess.main.util;

import java.io.File;

public class SaverLoader {

    public static void loadGame(File file) {
        loadPreferences(file);
        loadMoveHistory(file);
    }

    public static void saveGame(String fileName) {
        File file = null;
        savePreferences(file);
        saveMoveHistory(file);
    }

    private static void loadPreferences(File file) {

    }

    private static void loadMoveHistory(File file) {

    }

    private static void savePreferences(File file) {

    }

    private static void saveMoveHistory(File file) {

    }



}
