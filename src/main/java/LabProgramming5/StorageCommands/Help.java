package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "help"
 */
public class Help extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  help : вывести справку по доступным командам\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
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
