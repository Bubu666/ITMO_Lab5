package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "save"
 */
public class Save extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  save : сохранить коллекцию в файл\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
    public Save(Storage storage) {
        super(storage, "save");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.save();
    }
}
