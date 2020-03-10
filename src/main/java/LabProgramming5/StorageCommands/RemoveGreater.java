package LabProgramming5.StorageCommands;

import LabProgramming5.Human.HumanBeing;
import LabProgramming5.Storage.Storage;

public class RemoveGreater extends AbstractCommand implements Command {
    private HumanBeing humanBeing;

    public final static String helpInfo = "  remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n  remove_greater random : (*)\n";

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
