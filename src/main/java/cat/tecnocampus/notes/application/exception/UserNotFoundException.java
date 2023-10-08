package cat.tecnocampus.notes.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("User with email " + email + "doesn't exist");
    }
    public ResponseEntity<String> getData(@PathVariable int id) {
        if (id == 1) {
            // Data found
            String data = "Sample data";
            return ResponseEntity.ok(data);
        } else {
            // Data not found - return 404 with an exception message
            String message = "User not found for id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
