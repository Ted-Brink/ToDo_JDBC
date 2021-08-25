package se.tedbrink.model;

//import se.tedbrink.data.TodoSequencer;

import java.sql.Date;
import java.time.LocalDate;

public class Todo {
    private int todoId; // tog bort final
    private String title;
    private String description;
    private Date deadline;
    private boolean done;       // tinyint ?? boolean ???????????????????????????????????
    private Person assignee;    // assignee_id ?????????????????????

    public Todo(int todoId, String title, String description, Date deadline, boolean done, Person assignee) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assignee = assignee;
    }

    public Todo(String title, String description, Date deadline, boolean done, Person assignee) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assignee = assignee;
    }

    public Todo(int todoId, String title, String description, Date deadline, boolean done) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
    }

    /*  public Todo(String description) {
        this.todoId = TodoSequencer.nextTodoId();
        this.description = description;
    }
*/
    // Ta bort onödiga get & set


    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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