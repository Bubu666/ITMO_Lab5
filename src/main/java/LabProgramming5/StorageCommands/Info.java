package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "info"
 */
public class Info extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  info : вывести информацию о коллекции\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
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
