package LabProgramming5.Task;

import LabProgramming5.Storage.Storage;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Main-class
 *
 * @author Александр Бурачевский (285691, R3180)
 * @version 1.0
 */
public class Main {

    /**
     * Поток вывода информации
     */
    private static PrintStream output = System.out;

    /**
     * Метод принимает путь к файлу с объектами для инициализации программы.
     *
     * @param args Путь к файлу с объектами для инициализации
     */
    public static void main(String[] args) {
        File objectsFile = enterFile(args.length > 0 ? args[0] : null);
        new CommandListener(new Storage(objectsFile)).listen(System.in);
    }

    /**
     * Принимает название файла (аргумент командной строки) или {@code null}, если аргументы отсутствуют.
     * Возвращает созданный файл.
     * Выполняется проверка файла на существование и доступность. При возникновении каких-либо проблем
     * с файлом, в консоль выводится сообщение с просьбой ввести новое имя файла.
     *
     * @param arg Аргумент командной строки или {@code null}, если аргументы отсутствуют
     * @return Созданный файл
     */
    private static File enterFile(String arg) {
        File file;

        if (arg != null) {
            file = new File(arg);

            if (isReachable(file))
                return file;

            output.print("  Попробуйте ввести название файла заново\n> ");

        } else {
            output.print("  Введите имя файла\n> ");
        }

        Scanner input = new Scanner(System.in);

        while (true) {
            file = new File(input.nextLine());

            if (isReachable(file))
                return file;

            output.print("  Попробуйте ввести название файла заново\n> ");
        }
    }

    /**
     * Проверяет файл на доступность и существование и выводит в консоль информацию о возникших ошибках.
     *
     * @param file Проверяемый файл
     * @return {@code true} в случае полной пригодности файла для использования.
     * {@code false} когда возникли какие-то ошибки.
     */
    public static boolean isReachable(File file) {
        boolean success = true;

        if (!file.exists()) {

            output.println("  Не удалось найти файл \"" + file.getName() + "\"");
            success = false;

        } else {
            if (!file.canRead()) {
                output.println("  Не удалось открыть файл \"" + file.getName() + "\" для чтения");
                success = false;
            }

            if (!file.canWrite()) {
                output.println("  Не удалось открыть файл \"" + file.getName() + "\" для записи");
                success = false;
            }
        }

        return success;
    }
}