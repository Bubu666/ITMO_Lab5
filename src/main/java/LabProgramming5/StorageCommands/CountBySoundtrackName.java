package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class CountBySoundtrackName extends AbstractCommand implements Command {
    private String soundtrackName;

    public final static String helpInfo = "  count_by_soundtrack_name soundtrack_name : вывести количество элементов, значение поля soundtrackName которых равно заданному\n  count_by_soundtrack_name random : (*)\n";


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
