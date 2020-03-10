package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

/**
 * Абстрактный super class для всех команд
 */
public abstract class AbstractCommand implements Command {

    /**
     * Ссылка на хранилище, которым управляет команда
     */
    protected Storage storage;

    /**
     * Имя команды в пользовательском представлении
     */
    protected final String name;

    protected AbstractCommand(Storage storage, String name) {
        if (storage == null) throw new NullPointerException("Storage can't be null");
        this.storage = storage;
        this.name = name;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
