package se.tedbrink.model;

import se.tedbrink.data.TodoSequencer;

public class Todo {
    private final int todoId;
    private String description;
    private boolean done;
    private Person assignee;

    public Todo(String description) {
        this.todoId = TodoSequencer.nextTodoId();
        this.description = description;
    }

    // Ta bort onödiga get & set

    public int getTodoId() {
        return todoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getAssignee() { return assignee; }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }
}