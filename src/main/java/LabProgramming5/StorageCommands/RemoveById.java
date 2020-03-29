package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "remove_by_id"
 */
public class RemoveById extends AbstractCommand implements Command {

    /**
     * id объекта
     */
    private int id;

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  remove_by_id id : удалить элемент из коллекции по его id\n";

    /**
     * Принимает ссылку на хранилище и id объекта
     * @param storage Хранилище
     * @param id Id объекта
     */
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
