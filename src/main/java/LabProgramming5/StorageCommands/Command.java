package LabProgramming5.StorageCommands;

import java.lang.reflect.InvocationTargetException;

/**
 * Интерфейс для реализации команд
 */
public interface Command {

    /**
     * Выполняет команду
     *
     * @throws InvocationTargetException _
     * @throws IllegalAccessException _
     */
    void execute() throws InvocationTargetException, IllegalAccessException;
}
