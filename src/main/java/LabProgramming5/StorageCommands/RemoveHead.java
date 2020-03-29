package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "remove_head"
 */
public class RemoveHead extends AbstractCommand implements Command {

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  remove_head : вывести первый элемент коллекции и удалить его\n";

    /**
     * Принимает ссылку на хранилище
     * @param storage Хранилище
     */
    public RemoveHead(Storage storage) {
        super(storage, "remove_head");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.remove_head();
    }
}
