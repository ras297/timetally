package ir.maleki.sideprojects.timetally.common;

public class Strings {
    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean hasText(String s) {
        return !isEmpty(s);
    }
}
