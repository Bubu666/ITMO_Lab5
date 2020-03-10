package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class Help extends AbstractCommand implements Command {

    public final static String helpInfo = "  help : вывести справку по доступным командам\n";

    public Help(Storage storage) {
        super(storage, "help");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.help();
    }
}
