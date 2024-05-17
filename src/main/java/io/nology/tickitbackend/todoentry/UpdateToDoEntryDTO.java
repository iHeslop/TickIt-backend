package io.nology.tickitbackend.todoentry;

import jakarta.validation.constraints.Pattern;

public class UpdateToDoEntryDTO {
    @Pattern(regexp = ".*\\S.*", message = "Content cannot be empty")
    private String content;
    @Pattern(regexp = ".*\\S.*", message = "Title cannot be empty")
    private String title;

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
