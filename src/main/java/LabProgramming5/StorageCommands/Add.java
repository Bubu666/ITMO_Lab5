package LabProgramming5.StorageCommands;

import LabProgramming5.Human.HumanBeing;
import LabProgramming5.Storage.Storage;

/**
 * Команда "add"
 */
public class Add extends AbstractCommand implements Command {

    /**
     * Введенный пользователем объект
     */
    private HumanBeing humanBeing;

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  add : добавить новый элемент в коллекцию\n  add name_of_human\n  add random : (*)\n  add random num_of_elements : (*)\n";

    /**
     * Принимает ссылку на хранилище и новый объект {@code HumanBeing}
     * @param storage Хранилище
     * @param newHuman Новый объект
     */
    public Add(Storage storage, HumanBeing newHuman) {
        super(storage, "add");
        humanBeing = newHuman;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.add(humanBeing);
    }
}