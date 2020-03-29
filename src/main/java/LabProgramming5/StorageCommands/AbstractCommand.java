package LabProgramming5.StorageCommands;

import LabProgramming5.Storage.Storage;

import java.util.Objects;

/**
 * Абстрактный суперкласс для всех команд
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

    /**
     * Принимает ссылку на хранилище и название команды
     * @param storage Хранилище
     * @param name Название команды
     */
    protected AbstractCommand(Storage storage, String name) {
        if (storage == null) throw new NullPointerException("Storage can't be null");
        this.storage = storage;
        this.name = name;
    }

    /**
     * Возвращает ссылку на хранилище
     * @return Ссылка на хранилище
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Возвращает название команды
     * @return Название команды
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCommand)) return false;
        AbstractCommand that = (AbstractCommand) o;
        return Objects.equals(storage, that.storage) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage, name);
    }
}
