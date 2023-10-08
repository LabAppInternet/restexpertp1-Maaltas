package cat.tecnocampus.notes.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

public class NoteNotOwnedException extends RuntimeException{
    public NoteNotOwnedException(String title, String email) {
        super("Note with title " + title + " is not owned by " + email);
    }
    private final Set<String> uniqueValues = new HashSet<>();

    @PostMapping("/unique")
    public ResponseEntity<String> addUniqueValue(@RequestParam String value) {
        if (uniqueValues.contains(value)) {
            // Value already exists - return 409 Conflict with an exception message
            String message = "Value '" + value + "' already exists.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else {
            // Add the value to the unique set
            uniqueValues.add(value);
            return ResponseEntity.ok("Value '" + value + "' added successfully.");
        }
    }
}
