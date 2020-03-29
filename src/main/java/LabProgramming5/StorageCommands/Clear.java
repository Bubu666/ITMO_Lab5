package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "clear"
 */
public class Clear extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  clear : очистить коллекцию\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
    public Clear(Storage storage) {
        super(storage, "clear");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.clear();
    }
}
