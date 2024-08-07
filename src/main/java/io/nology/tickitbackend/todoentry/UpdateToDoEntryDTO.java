package io.nology.tickitbackend.todoentry;

import jakarta.validation.constraints.Pattern;

public class UpdateToDoEntryDTO {
    private String content;
    @Pattern(regexp = ".*\\S.*", message = "Title cannot be empty")
    private String title;

    private boolean completed = false;

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public boolean getCompleted() {
        return completed;
    }
}
