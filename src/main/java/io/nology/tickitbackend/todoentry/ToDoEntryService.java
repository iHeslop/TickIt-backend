package io.nology.tickitbackend.todoentry;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.tickitbackend.exceptions.ServiceValidationException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ToDoEntryService {
    @Autowired
    private ToDoEntryRepository repo;

    public ToDoEntry createEntry(CreateToDoEntryDTO data) throws ServiceValidationException {
        ToDoEntry newEntry = new ToDoEntry();
        newEntry.setContent(data.getContent().trim());
        newEntry.setTitle(data.getTitle().trim());
        return this.repo.save(newEntry);
    }

    public List<ToDoEntry> findAllEntries() {
        return this.repo.findAll();
    }

    public Optional<ToDoEntry> findById(Long id) {
        return this.repo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<ToDoEntry> maybeEntry = this.findById(id);
        if (maybeEntry.isEmpty()) {
            return false;
        }
        this.repo.delete(maybeEntry.get());
        return true;
    }

    public Optional<ToDoEntry> updateById(Long id, UpdateToDoEntryDTO data) {
        Optional<ToDoEntry> maybeEntry = this.findById(id);
        if (maybeEntry.isEmpty()) {
            return maybeEntry;
        }

        ToDoEntry foundEntry = maybeEntry.get();
        foundEntry.setContent(data.getContent().trim());
        foundEntry.setTitle(data.getTitle().trim());
        ToDoEntry updatedEntry = this.repo.save(foundEntry);
        return Optional.of(updatedEntry);
    }
}
