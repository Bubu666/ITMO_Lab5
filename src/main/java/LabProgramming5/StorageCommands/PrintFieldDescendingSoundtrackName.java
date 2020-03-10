package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class PrintFieldDescendingSoundtrackName extends AbstractCommand implements Command {

    public final static String helpInfo = "  print_field_descending_soundtrack_name : вывести значения поля soundtrackName в порядке убывания\n";

    public PrintFieldDescendingSoundtrackName(Storage storage) {
        super(storage, "print_field_descending_soundtrack_name");
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.print_field_descending_soundtrack_name();
    }
}
