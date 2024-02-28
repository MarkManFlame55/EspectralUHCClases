package net.mmf55dev.uhcclases.utils;

public class Time {
    public static long minutes(long minutes) {
        return minutes * 60 * 1000;
    }
    public static long seconds(long seconds) {
        return seconds * 1000;
    }

    public static String getRemainTime(long timeElapsed, long finishTime) {
        String remainTime = String.valueOf(finishTime - timeElapsed);
        return remainTime.substring(0, remainTime.length() - 3);
    }
    public static boolean hasFinished(long timeElapsed, long finishedTime) {
        return finishedTime - timeElapsed <= 0;
    }
}
