package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "exit"
 */
public class Exit extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  exit : завершить программу\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
    public Exit(Storage storage) {
        super(storage, "exit");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.exit();
    }
}
