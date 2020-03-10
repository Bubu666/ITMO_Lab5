package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class ExecuteScript extends AbstractCommand implements Command {
    private String fileName;

    public final static String helpInfo = "  execute_script file_name : считать и исполнить скрипт из указанного файла. \n";

    public ExecuteScript(Storage storage, String fileName) {
        super(storage, "execute_script");
        this.fileName = fileName;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.execute_script(fileName);
    }
}
