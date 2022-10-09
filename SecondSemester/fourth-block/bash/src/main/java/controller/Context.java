package controller;

import java.io.File;

public class Context {
    private File currentDirectory;
    public Context(File currentDirectory) {
        this.currentDirectory = currentDirectory;
    }
    public File getCurrentDirectory() {
        return currentDirectory;
    }
}