package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "show"
 */
public class Show extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  show : вывести все элементы коллекции в строковом представлении\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
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
