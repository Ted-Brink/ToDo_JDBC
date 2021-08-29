package se.tedbrink.data;

public class TodoSequencer {

    private static int todoId = 0;

    public static int nextTodoId() { return ++todoId; }

    void reset() {
        todoId = 0;
    }
}