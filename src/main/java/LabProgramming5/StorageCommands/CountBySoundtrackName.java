package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "count_by_soundtrack_name"
 */
public class CountBySoundtrackName extends AbstractCommand implements Command {

    /**
     * Название саундтрека
     */
    private String soundtrackName;

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  count_by_soundtrack_name soundtrack_name : вывести количество элементов, значение поля soundtrackName которых равно заданному\n  count_by_soundtrack_name random : (*)\n";

    /**
     * Принимает ссылку на хранилище и название саундтрека
     * @param storage Хранилище
     * @param soundtrackName Название саундтрека
     */
    public CountBySoundtrackName(Storage storage, String soundtrackName) {
        super(storage, "count_by_soundtrack_name");
        this.soundtrackName = soundtrackName;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.count_by_soundtrack_name(soundtrackName);
    }
}
