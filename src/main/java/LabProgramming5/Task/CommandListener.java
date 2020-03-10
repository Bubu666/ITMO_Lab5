package LabProgramming5.Task;

import LabProgramming5.Human.Car;
import LabProgramming5.Human.Coordinates;
import LabProgramming5.Human.HumanBeing;
import LabProgramming5.Human.Mood;
import LabProgramming5.Storage.Storage;
import LabProgramming5.StorageCommands.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Класс {@code CommandListener} предназначен для считывания и
 * обработки пользователььских действий над классом {@code Storage}
 */

public class CommandListener {

    /**
     * Переменная для считывания пользовательских команд
     */
    private Scanner input;

    /**
     * Переменная хранилища, над которым будут совершаться
     * пользовательские действия
     */
    private Storage storage;

    /**
     * Вывод информации в консоль
     */
    private PrintStream output = System.out;

    /**
     * Счетчик неправильно введенных команд
     */
    private int wrongCounter = 0;

    /**
     * Переменная, содержащая текст, который выводится в консоль для
     * оповещения пользователя о том, что он ввел несуществующую команду.
     */
    private String wrongArguments = "  Не правильные аргументы! Попробуйте заново\n  Для получения справки о доступных командах используйте команду \"help\"";

    /**
     * Переменная, содержащая текст, который выводится в консоль для
     * оповещения пользователя о том, что он ввел некорректные аргументы команды
     */
    private String wrongFormat = "  Неверный формат, попробуйте еще раз\n> ";

    /**
     * Переменная для оповещения программы о том,
     * что пользователь отказался вводить некоторые команды.
     */
    private boolean wasBroken;

    /**
     * Переменная хранит значение true, если обработка команд происходит
     * из стандартного потока System.in, иначе значение false
     */
    private boolean isConsole = false;


    /**
     * @param storage Ссылка на хранилище
     */
    public CommandListener(Storage storage) {
        this.storage = storage;
    }


    /**
     * Метод для слушания пользовательских команд (или же команд, описанных в скрипте).
     * Метод разбивает введенную пользователем строку, содержащую
     * команду и ее аргументы, на массив слов и отправляет ссылку на этот
     * массив методу {@code handle} для обработки команды.
     *
     * @param stream - поток, из которого произвадится чтение команд
     */
    public void listen(InputStream stream) {
        input = new Scanner(new InputStreamReader(stream));

        if (stream.equals(System.in)) {
            isConsole = true;
            output.print("> ");
        }

        while (input.hasNextLine()) {
            String[] msg = input.nextLine().trim().split(" ");


            handle(msg);

            if (isConsole)
                output.print("> ");
        }
    }


    /**
     * Метод для обработки пользовательского ввода, формарования объектов
     * команд {@code Command} и отправки этих команд методу {@code apply} класса {@code Storage}.
     * <p>
     * Если пользовательская команда состоит всего из одного слова и не имеет аргументов,
     * то формирование команды происходит с помощью средств {@code java.lang.reflect}
     * и метода {@code castToCommandClass}
     *
     * @param command - массив фрагментов команды
     */
    public void handle(String[] command) {

        switch (command[0]) {

            case "":
                return;

            case "add": {
                if (command.length > 1) {
                    StringBuilder name;
                    HumanBeing newHumanBeing;

                    if (command[1].compareToIgnoreCase("RANDOM") == 0) {

                        if (command.length == 3 && command[2].matches("\\d+")) {
                            int num = Integer.parseInt(command[2]);

                            for (int i = 0; i < num; ++i) {
                                storage.apply(new Add(storage, HumanBeing.getHuman()));
                            }
                            return;
                        }

                        newHumanBeing = HumanBeing.getHuman();

                    } else {
                        name = new StringBuilder(command[1]);

                        for (int i = 2; i < command.length; ++i) {
                            name.append(command[i]);
                        }

                        newHumanBeing = offerToEnterHuman(name.toString());

                        if (newHumanBeing == null)
                            break;
                    }

                    storage.apply(new Add(storage, newHumanBeing));

                } else {
                    output.println(wrongArguments);
                }

                return;
            }

            case "update": {
                if (command.length > 2) {
                    try {
                        int id = Integer.parseInt(command[1]);
                        HumanBeing newHumanBeing;

                        if (command[2].compareToIgnoreCase("RANDOM") == 0) {
                            newHumanBeing = HumanBeing.getHuman();
                        } else {
                            StringBuilder name = new StringBuilder(command[2]);

                            for (int i = 3; i < command.length; ++i) {
                                name.append(command[i]);
                            }

                            newHumanBeing = offerToEnterHuman(name.toString());

                            if (newHumanBeing == null)
                                return;
                        }

                        storage.apply(new Update(storage, id, newHumanBeing));
                    } catch (NumberFormatException e) {
                        output.println(wrongArguments);
                    }

                } else {
                    output.println(wrongArguments);
                }
                return;
            }

            case "remove_by_id": {
                if (command.length == 2) {
                    try {
                        int id = Integer.parseInt(command[1]);

                        storage.apply(new RemoveById(storage, id));
                    } catch (NumberFormatException e) {
                        output.println(wrongArguments);
                    }

                } else {
                    output.println(wrongArguments);
                }
                return;
            }

            case "execute_script": {
                if (command.length == 2) {
                    storage.apply(new ExecuteScript(storage, command[1]));
                } else {
                    output.println(wrongArguments);
                }
                return;
            }

            case "enter_new_init_file": {
                if (command.length == 2) {
                    storage.apply(new EnterNewInitFile(storage, command[1]));
                } else {
                    output.println(wrongArguments);
                }
                return;
            }

            case "remove_greater": {
                if (command.length > 1) {
                    HumanBeing humanBeing;

                    if (command[1].compareToIgnoreCase("RANDOM") == 0) {
                        humanBeing = HumanBeing.getHuman();
                    } else {
                        StringBuilder name = new StringBuilder(command[1]);

                        for (int i = 2; i < command.length; ++i) {
                            name.append(command[i]);
                        }
                        humanBeing = offerToEnterHuman(name.toString());
                    }

                    if (humanBeing == null)
                        return;

                    storage.apply(new RemoveGreater(storage, humanBeing));

                } else {
                    output.println(wrongArguments);
                }
                return;
            }

            case "count_by_soundtrack_name": {
                if (command.length > 1) {
                    String soundtrack;

                    if (command[1].compareToIgnoreCase("RANDOM") == 0) {

                        soundtrack = HumanBeing.getSoundtrack();

                    } else {
                        StringBuilder song = new StringBuilder(command[1]);

                        for (int i = 2; i < command.length; ++i) {
                            song.append(command[i]);
                        }

                        soundtrack = song.toString();
                    }

                    storage.apply(new CountBySoundtrackName(storage, soundtrack));

                } else {
                    output.println(wrongArguments);
                }
                return;
            }

            default: {
                try {
                    String commandClassName = command.length == 1 ? castToCommandClass(command[0]) : commandBuilder(
                            command);

                    // Создание объекта класса команды по ее названию
                    Command cmd = (Command) Class.forName("LabProgramming5.StorageCommands." + commandClassName)
                            .getConstructor(Storage.class).newInstance(storage);

                    storage.apply(cmd);

                    wrongCounter = 0;
                } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoClassDefFoundError e) {

                    if (++wrongCounter != 11)
                        output.println("  Не верная команда. Для получения справки используйте \"help\".");
                    else
                        output.println("  У тебя все в порядке?");
                }
            }

        }
    }


    /**
     * Метод перерабатывает название команды, введенной пользователем,
     * в название класса для создания соответствующей команды
     *
     * @param str Команда, введенная пользователем
     * @return - Название класса команды
     */
    private String castToCommandClass(String str) {
        if (str.equals(""))
            return str;

        char[] chars = str.toLowerCase().toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);

        for (int i = 1; i < chars.length; ++i) {
            if (chars[i] == '_') {
                chars[i + 1] = Character.toUpperCase(chars[i + 1]);
            }
        }

        return new String(chars).replaceAll("_", "");
    }

    private String commandBuilder(String[] parts) {
        StringBuilder cmd = new StringBuilder();
        for (String p : parts) {
            cmd.append(castToCommandClass(p).replaceAll(" ", ""));
        }

        return cmd.toString();
    }

    /**
     * Метод для построчного ввода пользователем характеристких объекта
     * класса {@code HumanBeing}
     * <p>
     * Возвращает null, если пользователь отказался от ввода характеристик объекта
     *
     * @param name Введенное пользователем значение поля {@code name}
     *             класса {@code HumanBeing}
     * @return Полученный по результатам построчного ввода объект
     * класса {@code HumanBeing} или null в случае принудительного выхода пользователем
     * из режима ввода характеристик объекта
     */
    private HumanBeing offerToEnterHuman(String name) {
        if (isConsole)
            output.println("  Введите характеристики объекта \"" + name + "\" (команда \"break\" для отмены ввода)");
        wasBroken = false;

        Coordinates coords = enterCoordinates();
        if (wasBroken)
            return null;

        Boolean realHero = enterBoolean("Real hero");
        if (wasBroken)
            return null;

        Boolean hasToothPick = enterBoolean("Has toothpick");
        if (wasBroken)
            return null;

        Double impactSpeed = enterDouble("Impact speed");
        if (wasBroken)
            return null;

        String soundtrackName = enterString("Soundtrack name");
        if (wasBroken)
            return null;

        Integer minutesOfWaiting = enterInteger("Minutes of waiting");
        if (wasBroken)
            return null;

        Mood mood = enterMood();
        if (wasBroken)
            return null;

        Boolean hasCoolCar = enterBoolean("Has cool car");
        if (wasBroken)
            return null;

        Car car = new Car(hasCoolCar);

        return new HumanBeing(name,
                coords,
                realHero,
                hasToothPick,
                impactSpeed,
                soundtrackName,
                minutesOfWaiting,
                mood,
                car
        );
    }


    /**
     * Метод для ввода пользователем данных типа String
     * <p>
     * Если пользователь ввел команду "break",
     * то происходит принудительный выход из режима ввода характеристик объекта
     * путем изменения поля {@code wasBroken}
     *
     * @param field Название поля, для которого вводится значение
     * @return Введенное пользователем значение
     */
    private String enterString(String field) {
        if (isConsole)
            output.print("  " + field + "\n> ");

        while (true) {
            String str = input.nextLine();

            if (str.equals(""))
                continue;

            if (str.equals("break"))
                wasBroken = true;

            return str;
        }
    }


    /**
     * Метод для ввода пользователем данных типа Integer
     * <p>
     * Если пользователь ввел команду "break",
     * то происходит принудительный выход из режима ввода характеристик объекта
     * путем изменения поля {@code wasBroken}
     *
     * @param field Название поля, для которого вводится значение
     * @return Введенное пользователем значение
     */
    private Integer enterInteger(String field) {
        if (isConsole)
            output.print("  " + field + " (int)\n> ");
        while (true) {
            String input = this.input.nextLine().replaceAll("\\s", "");

            try {
                return input.equals("break") ? null : Integer.parseInt(input);
            } catch (NumberFormatException e) {
                output.print(wrongFormat);
            }
        }
    }


    /**
     * Метод для ввода пользователем данных типа Double
     * <p>
     * Если пользователь ввел команду "break",
     * то происходит принудительный выход из режима ввода характеристик объекта
     * путем изменения поля {@code wasBroken}
     *
     * @param field Название поля, для которого вводится значение
     * @return Введенное пользователем значение
     */
    private Double enterDouble(String field) {
        if (isConsole)
            output.print("  " + field + " (double)\n> ");
        while (true) {
            String input = this.input.nextLine().replaceAll("\\s", "");

            switch (input) {
                case "":
                    return 0.0D;

                case "break":
                    wasBroken = true;
                    return 0.0D;

                default:
                    try {
                        return Double.parseDouble(input);
                    } catch (NumberFormatException e) {
                        output.print(wrongFormat);
                    }
            }
        }
    }


    /**
     * Метод для ввода пользователем данных для поля {@code coordinates}
     * объекта класса {@code HumanBeing}
     * <p>
     * Если пользователь ввел команду "break",
     * то происходит принудительный выход из режима ввода характеристик объекта
     * путем изменения поля {@code wasBroken}
     *
     * @return Инициализированный объект класса Coordinates
     */
    private Coordinates enterCoordinates() {
        if (isConsole)
            output.print("  Coordinates в формате \"(x, y)\":\n> ");
        while (true) {
            String input = this.input.nextLine().replaceAll("\\s", "");
            String[] coords = Pattern.compile("[(,)]").split(input);

            if (input.equals("break")) {
                wasBroken = true;
                return null;
            }

            try {
                return new Coordinates(Integer.parseInt(coords[1]), Double.parseDouble(coords[2]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                output.print(wrongFormat);
            }
        }
    }


    /**
     * Метод для ввода пользователем данных для поля {@code mood}
     * объекта класса {@code HumanBeing}
     * <p>
     * Если пользователь ввел команду "break",
     * то происходит принудительный выход из режима ввода характеристик объекта
     * путем изменения поля {@code wasBroken}
     *
     * @return Инициализированный объект класса Mood
     */
    private Mood enterMood() {
        if (isConsole)
            output.print("  Mood \"SADNESS/GLOOM/CALM/RAGE\":\n> ");
        while (true) {
            String input = this.input.nextLine().replaceAll(" ", "").toUpperCase();

            switch (input) {
                case "SADNESS":
                    return Mood.SADNESS;

                case "GLOOM":
                    return Mood.GLOOM;

                case "CALM":
                    return Mood.CALM;

                case "RAGE":
                    return Mood.RAGE;

                case "BREAK":
                    return null;
            }

            output.print(wrongFormat);
        }
    }


    /**
     * Метод для ввода пользователем данных типа Boolean
     * <p>
     * Если пользователь ввел команду "break",
     * то происходит принудительный выход из режима ввода характеристик объекта
     * путем изменения поля {@code wasBroken}
     *
     * @param field Название поля, для которого вводится значение
     * @return Введенное значение
     */
    private Boolean enterBoolean(String field) {
        if (isConsole)
            output.print("  " + field + " \"true/false\"\n> ");
        while (true) {
            String input = this.input.nextLine().replaceAll("\\s", "");

            switch (input) {
                case "true":
                    return true;

                case "false":
                case "":
                    return false;

                case "break":
                    wasBroken = true;
                    return false;
            }

            output.print(wrongFormat);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CommandListener))
            return false;
        CommandListener that = (CommandListener) o;
        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }
}
