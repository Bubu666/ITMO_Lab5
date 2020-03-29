package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "enter_new_init_file"
 */
public class EnterNewInitFile extends AbstractCommand implements Command {

    /**
     * Имя файла
     */
    private String fileName;

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  enter_new_init_file file_name : инициализировать коллекцию данными из указанного файла. \n";

    /**
     * Принимает ссылку на хранилище и имя файла
     * @param storage Хранилище
     * @param newFileName Имя файла
     */
    public EnterNewInitFile(Storage storage, String newFileName) {
        super(storage, "enter_new_init_file");
        fileName = newFileName;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.enter_new_init_file(fileName);
    }
}
