package by.bratchykau.receipt.model;

import javax.validation.constraints.*;
import java.time.LocalDate;


public class User {
    private static long nextId = 1;
    private long id;
    private String name;
    @NotNull
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Invalid email address")
    private String email;
    private LocalDate birthdate;

    @NotEmpty(message = "Age is required")
    @Min(value = 18, message = "Age must be greater than 18")
    private Integer age;

    static {
        nextId = 1;
    }

    public User() {
        this.id = nextId;
        nextId++;
    }

    public User(String name, String email, LocalDate birthdate, Integer age) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", birthdate=" + birthdate + ", age=" + age + '}';
    }
}