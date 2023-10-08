package cat.tecnocampus.notes.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


public class NotEditableNote extends RuntimeException {
    public NotEditableNote(String title, String email) {
        super("User " + email + " can't edit note " + title);
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
