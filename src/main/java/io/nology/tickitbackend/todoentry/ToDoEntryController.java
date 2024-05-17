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

@RestController
@RequestMapping("/entries")
public class ToDoEntryController {
    @Autowired
    private ToDoEntryService toDoEntryService;

    @PostMapping()
    public ResponseEntity<ToDoEntry> createEntry(@Valid @RequestBody CreateToDoEntryDTO data)
            throws BadRequestException {
        ToDoEntry createdEntry;
        try {
            createdEntry = this.toDoEntryService.createEntry(data);
            return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
        } catch (ServiceValidationException e) {
            e.printStackTrace();
            throw new BadRequestException(e.generateMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<ToDoEntry>> findAllEntries() {
        List<ToDoEntry> allEntries = this.toDoEntryService.findAllEntries();
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoEntry> findEntryById(@PathVariable Long id) throws NotFoundException {
        Optional<ToDoEntry> maybeEntry = this.toDoEntryService.findById(id);
        ToDoEntry foundEntry = maybeEntry.orElseThrow(() -> new NotFoundException(ToDoEntry.class, id));
        return new ResponseEntity<>(foundEntry, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ToDoEntry> updatedEntryById(@PathVariable Long id,
            @Valid @RequestBody UpdateToDoEntryDTO data) throws NotFoundException {
        Optional<ToDoEntry> maybeEntry = this.toDoEntryService.updateById(id, data);
        ToDoEntry updatedEntry = maybeEntry.orElseThrow(() -> new NotFoundException(ToDoEntry.class, id));
        return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntryById(@PathVariable Long id) throws NotFoundException {
        boolean isDeleted = this.toDoEntryService.deleteById(id);
        if (!isDeleted) {
            throw new NotFoundException(ToDoEntry.class, id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}