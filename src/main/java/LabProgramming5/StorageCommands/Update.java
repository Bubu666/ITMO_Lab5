package LabProgramming5.StorageCommands;

import LabProgramming5.Human.HumanBeing;
import LabProgramming5.Storage.Storage;

/**
 * Команда "update"
 */
public class Update extends AbstractCommand implements Command {

    /**
     * Введенный пользователем объект
     */
    private HumanBeing humanBeing;

    /**
     * id объекта
     */
    private int id;

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  update id : обновить значение элемента коллекции, id которого равен заданному\n  update id name_of_human\n  update id random : (*)\n";

    /**
     * Принимает ссылку на хранилище, id объекта и объект {@code HumanBeing}
     * @param storage Хранилище
     * @param id Id
     * @param newHumanBeing Новый объект
     */
    public Update(Storage storage, int id, HumanBeing newHumanBeing) {
        super(storage, "update");
        this.id = id;
        this.humanBeing = newHumanBeing;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.update(id, humanBeing);
    }
}
