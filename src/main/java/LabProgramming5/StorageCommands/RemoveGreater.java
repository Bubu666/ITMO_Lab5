package LabProgramming5.StorageCommands;

import LabProgramming5.Human.HumanBeing;
import LabProgramming5.Storage.Storage;

/**
 * Команда "remove_greater"
 */
public class RemoveGreater extends AbstractCommand implements Command {

    /**
     * Введенный пользователем объект
     */
    private HumanBeing humanBeing;

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  remove_greater : удалить из коллекции все элементы, превышающие заданный\n  remove_greater name_of_human\n  remove_greater random : (*)\n";

    /**
     * Принимает ссылку на хранилище и объект {@code HumanBeing}
     * @param storage Хранилище
     * @param humanBeing Объект
     */
    public RemoveGreater(Storage storage, HumanBeing humanBeing) {
        super(storage, "remove_greater");
        this.humanBeing = humanBeing;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.remove_greater(humanBeing);
    }
}
