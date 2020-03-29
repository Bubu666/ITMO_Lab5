package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "history"
 */
public class History extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  history : вывести последние 5 команд\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
    public History(Storage storage) {
        super(storage, "history");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.history();
    }
}
