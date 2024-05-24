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
import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/entries")
public class ToDoEntryController {

    protected static final Logger logger = LogManager.getLogger();

    @Autowired
    private ToDoEntryService toDoEntryService;

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

    @GetMapping()
    public ResponseEntity<List<ToDoEntry>> findAllEntries() {
        List<ToDoEntry> allEntries = this.toDoEntryService.findAllEntries();
        logger.info("Found entries: " + allEntries);
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoEntry> findEntryById(@PathVariable Long id) throws NotFoundException {
        Optional<ToDoEntry> maybeEntry = this.toDoEntryService.findById(id);
        ToDoEntry foundEntry = maybeEntry.orElseThrow(() -> new NotFoundException(ToDoEntry.class, id));
        logger.info("Found entry: " + foundEntry);
        return new ResponseEntity<>(foundEntry, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDoEntry> updatedEntryById(@PathVariable Long id,
            @Valid @RequestBody UpdateToDoEntryDTO data) throws NotFoundException {
        Optional<ToDoEntry> maybeEntry = this.toDoEntryService.updateById(id, data);
        ToDoEntry updatedEntry = maybeEntry.orElseThrow(() -> new NotFoundException(ToDoEntry.class, id));
        logger.info("Updated entry: " + updatedEntry);
        return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
    }

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