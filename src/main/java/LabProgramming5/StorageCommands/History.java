package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class History extends AbstractCommand implements Command {

    public final static String helpInfo = "  history : вывести последние 5 команд\n";


    public History(Storage storage) {
        super(storage, "history");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.history();
    }
}
