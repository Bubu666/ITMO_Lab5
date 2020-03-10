package LabProgramming5.StorageCommands;

import LabProgramming5.Human.HumanBeing;
import LabProgramming5.Storage.Storage;

public class Update extends AbstractCommand implements Command {
    private HumanBeing humanBeing;
    private int id;

    public final static String helpInfo = "  update id {element} : обновить значение элемента коллекции, id которого равен заданному\n  update id random : (*)\n";

    public Update(Storage storage, int id, HumanBeing humanBeing) {
        super(storage, "update");
        this.id = id;
        this.humanBeing = humanBeing;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.update(id, humanBeing);
    }
}
