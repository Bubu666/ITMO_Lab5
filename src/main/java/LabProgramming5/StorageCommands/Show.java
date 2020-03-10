package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class Show extends AbstractCommand implements Command {

    public final static String helpInfo = "  show : вывести все элементы коллекции в строковом представлении\n";

    public Show(Storage storage) {
        super(storage, "show");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.show();
    }
}
