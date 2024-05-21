package io.nology.tickitbackend.todoentry;

import jakarta.validation.constraints.NotBlank;

public class CreateToDoEntryDTO {
    @NotBlank
    private String content;

    @NotBlank
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
