package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class Exit extends AbstractCommand implements Command {

    public final static String helpInfo = "  exit : завершить программу\n";

    public Exit(Storage storage) {
        super(storage, "exit");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.exit();
    }
}
