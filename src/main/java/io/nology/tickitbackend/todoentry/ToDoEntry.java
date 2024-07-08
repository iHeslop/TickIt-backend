package io.nology.tickitbackend.todoentry;

import io.nology.tickitbackend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "todo_entries")
public class ToDoEntry extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String title;

    @Column
    private boolean completed = false;

    ToDoEntry() {
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "ToDoEntry{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

}
