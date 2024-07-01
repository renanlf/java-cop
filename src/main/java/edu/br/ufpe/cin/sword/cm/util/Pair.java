package edu.br.ufpe.cin.sword.cm.util;

public class Pair<First, Second> {

    private final First first;
    private final Second second;

    private Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public First getFirst() {
        return first;
    }

    public Second getSecond() {
        return second;
    }

}
