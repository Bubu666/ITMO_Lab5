package LabProgramming5.Human;

/**
 * Реализация искрючения, которое выбрасывается в случае
 * неправильного введения пользователем имени объекта
 */
public class InappropriateHumanNameException extends RuntimeException {
    public InappropriateHumanNameException(String message) {
        super(message);
    }
}