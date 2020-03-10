package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class RemoveHead extends AbstractCommand implements Command {

    public final static String helpInfo = "  remove_head : вывести первый элемент коллекции и удалить его\n";

    public RemoveHead(Storage storage) {
        super(storage, "remove_head");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.remove_head();
    }
}
