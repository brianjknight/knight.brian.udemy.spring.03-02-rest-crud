package knight.brian.restdemo.rest;

import jakarta.annotation.PostConstruct;
import knight.brian.restdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentList;

    @PostConstruct
    private void loadData() {

        Student a = new Student("Jean-Luc", "Picard");
        Student b = new Student("Will", "Riker");
        Student c = new Student("Geordi", "LaForge");

        studentList = List.of(a, b, c);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        try {
            return studentList.get(studentId);
        } catch (RuntimeException e) {
            throw new StudentNotFoundException("Student ID: %d not found".formatted(studentId));
        }

    }

}
