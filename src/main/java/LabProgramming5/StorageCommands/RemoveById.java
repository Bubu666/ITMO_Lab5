package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class RemoveById extends AbstractCommand implements Command {
    private int id;

    public final static String helpInfo = "  remove_by_id id : удалить элемент из коллекции по его id\n";

    public RemoveById(Storage storage, int id) {
        super(storage, "remove_by_id");
        this.id = id;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.remove_by_id(id);
    }
}
