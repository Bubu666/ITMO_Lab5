package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Команда "execute_script"
 */
public class ExecuteScript extends AbstractCommand implements Command {

    /**
     * Имя файла
     */
    private String fileName;

    /**
     * Информация о использовании команды
     */
    public final static String helpInfo = "  execute_script file_name : считать и исполнить скрипт из указанного файла. \n";

    /**
     * Принимает ссылку на хранилище и имя файла
     * @param storage Хранилище
     * @param fileName Имя файла
     */
    public ExecuteScript(Storage storage, String fileName) {
        super(storage, "execute_script");
        this.fileName = fileName;
    }

    /**
     * Реализация команды
     */
    @Override
    public void execute() {
        storage.execute_script(fileName);
    }
}
