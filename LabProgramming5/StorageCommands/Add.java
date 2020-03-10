package LabProgramming5.StorageCommands;

import LabProgramming5.Human.HumanBeing;
import LabProgramming5.Storage.Storage;

public class Add extends AbstractCommand implements Command {
    private HumanBeing humanBeing;

    public final static String helpInfo = "  add {element} : добавить новый элемент в коллекцию\n  add random : (*)\n  add random num_of_elements : (*)\n";


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