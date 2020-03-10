package LabProgramming5.Human;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * Объектная модель человеческого существа.
 * Класс содержит поля состояния человека, а также хранилище имен и саундтреков
 * для реализации случайной генерации объектов данного класса.
 */
public class HumanBeing implements Comparable<HumanBeing> {

    /**
     * Уникальный id каждого объекта типа {@code HumanBeing}
     * Поле генерируется автоматически при инициализации объекта
     */
    private final int id;


    /**
     * Время создания конкретного объекта.
     * Поле генерируется автоматически при инициализации объекта
     */
    @Stored
    private java.util.Date creationDate;

    /**
     * Имя человеческого существа
     */
    @Stored
    private final String name;

    /**
     * Местоположение человеческого существа
     */
    @Stored
    private Coordinates coordinates;

    /**
     * Имя человеческого существа
     */
    @Stored
    private Boolean realHero;

    @Stored
    private boolean hasToothpick;

    @Stored
    private double impactSpeed;

    @Stored
    private String soundtrackName;

    @Stored
    private Integer minutesOfWaiting;

    @Stored
    private Mood mood;

    @Stored
    private Car car;

    /**
     * Переменная для генерации {@code id} объекта
     */
    private static int numOfHumans;

    /**
     * Переменная хранилища имен и саундтреков
     * для случайной генерации объекта класса {@code HumanBeing}
     */
    private static HumanStorage humanStorage;

    {
        creationDate = new Date();
        id = ++numOfHumans;
    }

    static {
        humanStorage = new HumanStorage();
    }

    /**
     * Общий конструктор
     */
    public HumanBeing(String name,
                      Coordinates coordinates,
                      Boolean realHero,
                      boolean hasToothpick,
                      double impactSpeed,
                      String soundtrackName,
                      Integer minutesOfWaiting,
                      Mood mood,
                      Car car
    ) {
        if (name == null || coordinates == null || realHero == null || soundtrackName == null || minutesOfWaiting == null || mood == null || car == null)
            throw new NullPointerException();

        if (name.equals(""))
            throw new InappropriateHumanNameException("Недопустимое имя человека");

        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.mood = mood;
        this.car = car;
    }

    /**
     * Конструктор для создания объектов из файла.
     * Дополнительно принимается время создания объекта.
     */
    public HumanBeing(String name,
                      Coordinates coordinates,
                      Boolean realHero,
                      boolean hasToothpick,
                      double impactSpeed,
                      String soundtrackName,
                      Integer minutesOfWaiting,
                      Mood mood,
                      Car car,
                      Instant creationDate
    ) {
        this(name, coordinates, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, mood, car);

        if (creationDate != null)
            this.creationDate = Date.from(creationDate);
    }

    public int compareTo(HumanBeing o) {
        return this.minutesOfWaiting - o.minutesOfWaiting;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public int getId() {
        return id;
    }

    public static String getSoundtrack() {
        return humanStorage.getSoundtrack();
    }

    /**
     * Статический метод для генерации случайного объекта
     */
    public static HumanBeing getHuman() {
        Random r = new Random();

        return new HumanBeing(humanStorage.getName(),
                new Coordinates(r.nextInt(200), r.nextInt(200) * Math.random()),
                r.nextBoolean(),
                r.nextBoolean(),
                r.nextInt(150) * Math.random(),
                humanStorage.getSoundtrack(),
                r.nextInt(100),
                Mood.getRandom(),
                new Car(Math.random() > 0.5)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                name,
                coordinates,
                creationDate,
                realHero,
                hasToothpick,
                impactSpeed,
                soundtrackName,
                minutesOfWaiting,
                mood,
                car
        );
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public double getImpactSpeed() {
        return impactSpeed;
    }

    public Integer getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public Mood getMood() {
        return mood;
    }

    public Car getCar() {
        return car;
    }

    /**
     * Класс генерирует случайные значения поля {@code name} и {@code soundtrackName}
     */
    private static class HumanStorage {
        private ArrayList<String> nameList;
        private ArrayList<String> soundtrackList;

        {
            String path = new File(" ").getAbsolutePath().trim();
            String sp = File.separator;
            File names = new File(String.format("%1$s%2$sinitSources%2$snames", path, sp));
            File soundtracks = new File(String.format("%1$s%2$sinitSources%2$ssoundTracks", path, sp));

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(names)));
                nameList = (ArrayList<String>) reader.lines().collect(Collectors.toList());

                reader = new BufferedReader(new InputStreamReader(new FileInputStream(soundtracks)));
                soundtrackList = (ArrayList<String>) reader.lines().collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        /**
         * Генерация случайных значений поля {@code soundtrackName}
         */
        String getSoundtrack() {
            return soundtrackList.get(new Random().nextInt(soundtrackList.size()));
        }

        /**
         * Генерация случайных значений поля {@code name}
         */
        String getName() {
            return nameList.get(new Random().nextInt(nameList.size()));
        }
    }


    /**
     * @return Строковое представление объектов класса {@code HumanBeing}
     * для сохранения в файл
     */
    public String toCSVFormatString() {
        return String.format("%s;%d %f;%b;%b;%f;%s;%d;%s;%b;%s",
                name,
                coordinates.getX(),
                coordinates.getY(),
                realHero,
                hasToothpick,
                impactSpeed,
                soundtrackName,
                minutesOfWaiting,
                mood,
                car.isCool(),
                creationDate.toInstant()
        ).replaceAll(",", ".");
    }


    /**
     * @return Строковое представление названий полей класса {@code HumanBeing}
     * для сохранения в файл
     */
    public static String fieldHeadersCSVString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;",
                "name",
                "coordinates",
                "realHero",
                "hasToothpick",
                "impactSpeed",
                "soundtrackName",
                "minutesOfWaiting",
                "mood",
                "car",
                "creationDate"
        );
    }

    @Override
    public String toString() {
        return "{\n" + "\t\"id\": " + id + ",\n\t\"name\": \"" + name + '\"' + ",\n\t\"coordinates\": " + coordinates + ",\n\t\"creationDate\": " + creationDate + ",\n\t\"realHero: " + realHero + ",\n\t\"hasToothpick: " + hasToothpick + ",\n\t\"impactSpeed\": " + impactSpeed + ",\n\t\"soundtrackName\": \"" + soundtrackName + '\"' + "\",\n\t\"minutesOfWaiting\": " + minutesOfWaiting + ",\n\t\"mood: " + mood + ",\n\t\"car\": " + car + "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof HumanBeing))
            return false;
        HumanBeing that = (HumanBeing) o;
        return name.equals(that.name) && coordinates.equals(that.coordinates) && soundtrackName
                .equals(that.soundtrackName);
    }
}