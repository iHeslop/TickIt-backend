package io.nology.tickitbackend.todoentry;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.nology.tickitbackend.exceptions.BadRequestException;
import io.nology.tickitbackend.exceptions.NotFoundException;
import io.nology.tickitbackend.exceptions.ServiceValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/entries")
public class ToDoEntryController {

    protected static final Logger logger = LogManager.getLogger();

    @Autowired
    private ToDoEntryService toDoEntryService;

    @Tag(name = "POST", description = "POST methods of todo API")
    @Operation(summary = "Create a new Todo entry", description = "Create a new Todo entry. The response is a new Todo object with title, content, date created, last updated date and completed properties.")
    @PostMapping()
    public ResponseEntity<ToDoEntry> createEntry(@Valid @RequestBody CreateToDoEntryDTO data)
            throws BadRequestException {
        ToDoEntry createdEntry;
        try {
            createdEntry = this.toDoEntryService.createEntry(data);
            logger.info("Entry created: " + createdEntry);
            return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
        } catch (ServiceValidationException e) {
            logger.info("Error creating entry");
            e.printStackTrace();
            throw new BadRequestException(e.generateMessage());
        }
    }

    @Tag(name = "GET", description = "GET methods of todo API")
    @Operation(summary = "Get all Todo entries", description = "Get all Todo entries. The response is a List of Todo objects with title, content, date created, last updated date and completed properties.")
    @GetMapping()
    public ResponseEntity<List<ToDoEntry>> findAllEntries() {
        List<ToDoEntry> allEntries = this.toDoEntryService.findAllEntries();
        logger.info("Found entries: " + allEntries);
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    @Tag(name = "GET", description = "GET methods of todo API")
    @Operation(summary = "Get Todo Entry by ID", description = "Get a single Todo entry by ID. The response is a new Todo object with title, content, date created, last updated date and completed properties.")
    @GetMapping("/{id}")
    public ResponseEntity<ToDoEntry> findEntryById(@PathVariable Long id) throws NotFoundException {
        Optional<ToDoEntry> maybeEntry = this.toDoEntryService.findById(id);
        ToDoEntry foundEntry = maybeEntry.orElseThrow(() -> new NotFoundException(ToDoEntry.class, id));
        logger.info("Found entry: " + foundEntry);
        return new ResponseEntity<>(foundEntry, HttpStatus.OK);
    }

    @Tag(name = "PATCH", description = "PATCH methods of todo API")
    @Operation(summary = "Update a Todo entry", description = "Update a Todo entry by ID. The response is an updated Todo object with title, content, date created, last updated date and completed properties.")
    @PatchMapping("/{id}")
    public ResponseEntity<ToDoEntry> updatedEntryById(@PathVariable Long id,
            @Valid @RequestBody UpdateToDoEntryDTO data) throws NotFoundException {
        Optional<ToDoEntry> maybeEntry = this.toDoEntryService.updateById(id, data);
        ToDoEntry updatedEntry = maybeEntry.orElseThrow(() -> new NotFoundException(ToDoEntry.class, id));
        logger.info("Updated entry: " + updatedEntry);
        return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
    }

    @Tag(name = "DELETE", description = "DELETE methods of todo API")
    @Operation(summary = "Delete a Todo entry", description = "Delete a Todo entry by ID. The response is a HTTP status.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntryById(@PathVariable Long id) throws NotFoundException {
        boolean isDeleted = this.toDoEntryService.deleteById(id);
        if (!isDeleted) {
            logger.info("Entry not found: " + id);
            throw new NotFoundException(ToDoEntry.class, id);
        }
        logger.info("Deleted Entry with id: " + id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}