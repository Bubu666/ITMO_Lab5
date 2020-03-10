package LabProgramming5.Human;

/**
 * {@code Mood} enum для описания натроения объекта
 */
public enum Mood {
    SADNESS,
    GLOOM,
    CALM,
    RAGE;

    public static Mood of(String name) {
        switch (name) {
            case "sadness":
                return Mood.SADNESS;
            case "gloom":
                return Mood.GLOOM;
            case "calm":
                return Mood.CALM;
            default:
                return Mood.RAGE;
        }
    }

    public static Mood getRandom() {
        double random = Math.random();

        if (random < 0.25D)
            return Mood.SADNESS;
        if (random < 0.5D)
            return Mood.GLOOM;
        if (random < 0.75D)
            return Mood.CALM;
        else
            return Mood.RAGE;
    }
}