package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "max_by_mood"
 */
public class MaxByMood extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  max_by_mood : вывести любой объект из коллекции, значение поля mood которого является максимальным\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
    public MaxByMood(Storage storage) {
        super(storage, "max_by_mood");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.max_by_mood();
    }
}
