package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class Save extends AbstractCommand implements Command {

    public final static String helpInfo = "  save : сохранить коллекцию в файл\n";

    public Save(Storage storage) {
        super(storage, "save");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.save();
    }
}
