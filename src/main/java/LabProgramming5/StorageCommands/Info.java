package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class Info extends AbstractCommand implements Command {

    public final static String helpInfo = "  info : вывести информацию о коллекции\n";

    public Info(Storage storage) {
        super(storage, "info");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.info();
    }
}
