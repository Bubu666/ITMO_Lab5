package LabProgramming5.Storage;

import LabProgramming5.Human.*;
import LabProgramming5.StorageCommands.*;
import LabProgramming5.Task.CommandListener;
import LabProgramming5.Task.Main;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Класс {@code Storage} хранит коллекцию объектов класса {@code HumanBeing},
 * а также управляет этой коллекцией.
 */
public class Storage implements StorageManagement {

    /**
     * Список объектов класса {@code HumanBeing}
     */
    private LinkedList<HumanBeing> humanBeings;

    /**
     * Список вводимых команд {@code Command}
     */
    private LinkedList<Command> commandList;

    /**
     * Файл, из которого считываются объекты класса {@code HumanBeing}
     * при инициализации {@code humanBeings}
     */
    private File objectsFile;

    /**
     * Дата инициализации коллекции
     */
    private Date dateOfInitialization;

    /**
     * Переменная для вывода информации в консоль
     */
    private PrintStream console = System.out;

    private static final String helpInfo;

    static {
        StringBuilder info = new StringBuilder();
        for (Method m : Storage.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(HelpInfo.class)) {
                info.append(m.getAnnotation(HelpInfo.class).info());
            }
        }
        helpInfo = info.toString();
    }

    {
        humanBeings = new LinkedList<>();
        commandList = new LinkedList<>();
        dateOfInitialization = new Date();
    }

    /**
     * @param objectsFile Файл, из которого считываются объекты для инициализации
     */
    public Storage(File objectsFile) {
        this.objectsFile = objectsFile;
        if (!initCollection()) {
            console.println("  Данные в файле " + objectsFile + " записаны в неверном формате или повреждены, объекты могут быть инициализированы не в полном объеме.");
            console.println(
                    "  Для ввода нового файла используйте \"enter_new_init_file\". Для получения справки используйте \"help\".");
        }
    }

    public File getObjectsFile() {
        return objectsFile;
    }

    /**
     * Метод для выполнения команд для действий над коллекцией {@code humanBeings}
     *
     * @param command Принимаемая команда
     */
    public void apply(Command command) {
        try {
            command.execute();
            commandList.add(command);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * Инициализация коллекции {@code humanBeings}
     * из файла {@code objectsFile}
     */
    private boolean initCollection() {
        humanBeings.clear();
        boolean success = true;
        HashMap<String, Field> nameToField = new HashMap<>();

        for (Field f : HumanBeing.class.getDeclaredFields()) {
            if (f.isAnnotationPresent(Stored.class)) {
                nameToField.put(f.getName(), f);
            }
        }

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(objectsFile)));

            if (!input.ready()) {
                return true;
            }

            String[] fields = input.readLine().trim().split(";");

            if (fields.length != nameToField.size()) {
                success = false;
            }

            for (String f : fields) {
                if (nameToField.get(f) == null) {
                    success = false;
                }
            }

            ArrayList<String[]> props = (ArrayList<String[]>) input.lines().map(l -> l.trim().split(";"))
                    .collect(Collectors.toList());

            for (int i = 0; i < props.size(); ++i) {

                if (props.get(i).length != fields.length) {
                    success = false;
                    continue;
                }


                Coordinates coordinates;
                String[] inputCoords = props.get(i)[1].split(" ");
                if (inputCoords.length != 2) {
                    success = false;
                    continue;
                }

                try {
                    coordinates = new Coordinates(Integer.parseInt(inputCoords[0]), Double.parseDouble(inputCoords[1]));
                    humanBeings.add(new HumanBeing(props.get(i)[0],
                            coordinates,
                            Boolean.parseBoolean(props.get(i)[2]),
                            Boolean.parseBoolean(props.get(i)[3]),
                            Double.parseDouble(props.get(i)[4]),
                            props.get(i)[5],
                            Integer.parseInt(props.get(i)[6]),
                            Mood.of(props.get(i)[7]),
                            new Car(Boolean.parseBoolean(props.get(i)[8])),
                            Instant.parse(props.get(i)[9])
                    ));
                } catch (NumberFormatException | DateTimeParseException e) {
                    success = false;
                    continue;
                }
            }

        } catch (IOException e) {
            return false;
        }
        return success;
    }


    /**
     * Вывод в консоль информации о доступных командах
     */
    @Override
    @HelpInfo(info = Help.helpInfo)
    public void help() {

        console.println("  Допустимые команды:\n" + helpInfo + "  * Некоторые команды поддерживают ввод случайно сгенерированного объекта (random)");
    }


    /**
     * Вывод в консоль информации о коллекции {@code humanBeings}
     */
    @Override
    @HelpInfo(info = Info.helpInfo)
    public void info() {
        console.println("  Тип коллекции: " + humanBeings.getClass().getName() + "\n  Дата инициализации: " + dateOfInitialization + "\n  Количество элементов: " + humanBeings
                .size() + "\n  Файл: " + objectsFile.getName());
    }


    /**
     * Вывод в консоль объектов коллекции {@code humanBeings}
     */
    @Override
    @HelpInfo(info = Show.helpInfo)
    public void show() {

        if (humanBeings.isEmpty()) {
            console.println("  Коллекция пуста");
        } else {
            humanBeings.forEach(console::println);
        }
    }


    /**
     * Добавление объекта в коллекцию {@code humanBeings}
     *
     * @param humanBeing Добавляемый объект
     */
    @Override
    @HelpInfo(info = Add.helpInfo)
    public void add(HumanBeing humanBeing) {

        if (humanBeings.add(humanBeing))
            console.println("  " + humanBeing.getName() + " успешно добавлен в коллекцию!");
        else
            console.println("  " + humanBeing.getName() + " не добавлен в коллекцию.");
    }


    /**
     * Обновление объекта коллекции {@code humanBeings} по заданному id
     *
     * @param id         Id объекта
     * @param humanBeing Объект
     */
    @Override
    @HelpInfo(info = Update.helpInfo)
    public void update(int id, HumanBeing humanBeing) {

        boolean isHere = false;
        int i = 0;

        for (HumanBeing hb : humanBeings) {

            if (hb.getId() == id) {
                isHere = true;
                break;
            }
            ++i;
        }

        if (isHere) {
            HumanBeing prev = humanBeings.remove(i);
            humanBeings.add(i, humanBeing);

            console.println("  Объект " + humanBeing.getName() + " добавлен в коллекцию!\n  Объект " + prev
                    .getName() + " удален из коллекции.");
        } else {
            console.println("  В коллекции нет элемента с данным id");
        }
    }


    /**
     * Удаление объекта коллекции {@code humanBeings} по заданному id
     *
     * @param id Id удаляемого объекта
     */
    @Override
    @HelpInfo(info = RemoveById.helpInfo)
    public void remove_by_id(int id) {

        boolean success = false;

        int i = 0;
        for (HumanBeing hb : humanBeings) {

            if (hb.getId() == id) {
                success = true;
                break;
            }
            ++i;
        }

        if (success) {
            humanBeings.remove(i);
            console.println("  Элемент удален");
        } else {
            console.println("  Элемент не удален");
        }
    }


    /**
     * очистка коллеции {@code humanBeings}
     */
    @Override
    @HelpInfo(info = Clear.helpInfo)
    public void clear() {

        if (humanBeings.size() > 0) {
            humanBeings.clear();
            console.println("  Все элементы удалены");
        } else {
            console.println("  Коллекция пуста");
        }
    }


    /**
     * сохранение коллекции {@code humanBeings} в файл
     */
    @Override
    @HelpInfo(info = Save.helpInfo)
    public void save() {

        try {
            FileWriter fw = new FileWriter(objectsFile);

            if (!Main.isReachable(objectsFile)) {
                console.println("  Возможно, файл поврежден или удален.");
            }

            fw.write(HumanBeing.fieldHeadersCSVString() + "\n");

            for (HumanBeing hb : humanBeings) {
                fw.write(hb.toCSVFormatString() + "\n");
            }

            fw.close();

            console.println("  Элементы коллекции сохранены в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Выполнение скрипта из файла по заданному имени
     *
     * @param fileName Имя файла, в котором находится скрипт
     */
    @Override
    @HelpInfo(info = ExecuteScript.helpInfo)
    public void execute_script(String fileName) {

        File script = new File(fileName);

        if (!script.exists()) {
            console.println("  Файл " + script + " не существует!");
            return;
        }

        if (!script.canRead()) {
            console.println("  Файл " + script + " не доступен для чтения");
            return;
        }

        try {
            new CommandListener(this).listen(new FileInputStream(script));
        } catch (FileNotFoundException e) {
            console.println("  Файл " + script + " не существует!");
        }
    }


    /**
     * Принудительное завершение программы
     */
    @Override
    @HelpInfo(info = Exit.helpInfo)
    public void exit() {
        System.exit(0);
    }


    /**
     * Удаление из коллекции {@code humanBeings}
     * последнего добавленного элемента
     */
    @Override
    @HelpInfo(info = RemoveHead.helpInfo)
    public void remove_head() {

        if (humanBeings.size() > 0) {
            console.println(humanBeings.poll());
            console.println("  Элемент удален");
        } else {
            console.println("  Коллекция пуста");
        }
    }


    /**
     * Удаление объектов коллекции {@code humanBeings},
     * которые привышают заданный элемент
     *
     * @param delimiter Заданный элемент
     */
    @Override
    @HelpInfo(info = RemoveGreater.helpInfo)
    public void remove_greater(HumanBeing delimiter) {

        int prevSize = humanBeings.size();

        humanBeings = humanBeings.stream().filter(hb -> hb.compareTo(delimiter) > 0).collect(Collectors.toCollection(
                LinkedList::new));

        console.println("  Удалено " + (prevSize - humanBeings.size()) + " элементов");
    }


    /**
     * Вывод в консоль последних пяти команд {@code commandList}
     */
    @Override
    @HelpInfo(info = History.helpInfo)
    public void history() {
        console.println(" Последние команды:");

        int i = 0;
        ListIterator<Command> it = commandList.listIterator(commandList.size());
        for (; it.hasPrevious() && i < 5; ++i) {

            console.println("   " + it.previous());
        }
    }


    /**
     * Вывод в консоль объекта коллекции {@code humanBeings},
     * который имеет максимальное значение переменной {@code mood}
     */
    @Override
    @HelpInfo(info = MaxByMood.helpInfo)
    public void max_by_mood() {

        boolean success = false;
        for (HumanBeing hb : humanBeings) {
            if (hb.getMood().equals(Mood.RAGE)) {
                console.println(hb);
                success = true;
                break;
            }
        }

        if (!success) {
            console.println("  В коллекции нет элемента с максимальным значением поля mood");
        }
    }


    /**
     * Вывод в консоль количества объектов коллекции {@code humanBeings}
     * с заданным значением поля {@code soundtrackName}
     *
     * @param soundtrackName Заданное значение поля {@code soundtrackName}
     */
    @Override
    @HelpInfo(info = CountBySoundtrackName.helpInfo)
    public void count_by_soundtrack_name(String soundtrackName) {

        console.println(humanBeings.stream().filter(h -> h.getSoundtrackName().equals(soundtrackName)).count());
    }


    /**
     * Вывод в консоль списка {@code soundtrackName} объектов коллекции
     * {@code humanBeings} в порядке убывания
     */
    @Override
    @HelpInfo(info = PrintFieldDescendingSoundtrackName.helpInfo)
    public void print_field_descending_soundtrack_name() {

        if (humanBeings.size() > 0) {
            humanBeings.stream().map(HumanBeing::getSoundtrackName).sorted(Comparator.reverseOrder()).forEachOrdered(
                    console::println);
        } else {
            console.println("  Коллекция пуста!");
        }
    }


    @HelpInfo(info = EnterNewInitFile.helpInfo)
    public void enter_new_init_file(String fileName) {
        File newFile = new File(fileName);

        if (!newFile.exists()) {
            console.println("  Файл " + newFile + " не существует!");
            return;
        }

        boolean notAllowed = false;

        if (!newFile.canRead()) {
            console.println("  Файл " + newFile + " не доступен для чтения");
            notAllowed = true;
        }

        if (!newFile.canWrite()) {
            console.println("  Файл " + newFile + " не доступен для записи");
            notAllowed = true;
        }

        if (notAllowed)
            return;

        this.objectsFile = newFile;
        if (!initCollection()) {
            console.println("  Данные в файле " + objectsFile + " записаны в неверном формате или повреждены, объекты могут быть инициализированы не в полном объеме.");
            console.println(
                    "  Для ввода нового файла используйте \"enter_new_init_file\". Для получения справки используйте \"help\".");

        }
        console.println("  Коллекция обновлена");
    }

    /**
     * Сортировка коллекции {@code humanBeings}
     */
    public void sortCollection() {
        humanBeings.sort(HumanBeing::compareTo);
    }
}