package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

public class EnterNewInitFile extends AbstractCommand implements Command {
    private String fileName;

    public final static String helpInfo = "  enter_new_init_file file_name : инициализировать коллекцию данными из указанного файла. \n";


    public EnterNewInitFile(Storage storage, String newFileName) {
        super(storage, "enter_new_init_file");
        fileName = newFileName;
    }

    @Override
    public void execute() {
        storage.enter_new_init_file(fileName);
    }
}
