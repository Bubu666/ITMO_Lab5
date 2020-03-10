package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class Clear extends AbstractCommand implements Command {
    public Clear(Storage storage) {
        super(storage, "clear");
    }

    public final static String helpInfo = "  clear : очистить коллекцию\n";

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.clear();
    }
}
