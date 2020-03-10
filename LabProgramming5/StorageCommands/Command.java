package LabProgramming5.StorageCommands;

import java.lang.reflect.InvocationTargetException;

/**
 * Интерфейс для реализации команд
 */
public interface Command {

    /**
     * Выполняет команду
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void execute() throws InvocationTargetException, IllegalAccessException;
}
