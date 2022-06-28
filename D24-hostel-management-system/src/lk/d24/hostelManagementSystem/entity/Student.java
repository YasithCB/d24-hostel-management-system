package lk.d24.hostelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * author  Yasith C Bandara
 * created 6/18/2022 - 9:26 PM
 * project D24-hostel-management-system
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Student {
    @Id
    private String studentId;
    private String name;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;
    private LocalDate dateRegistered;

    @OneToMany (mappedBy = "student", fetch = FetchType.EAGER)
    private List<Reserve> reserveList = new ArrayList<>();

    public Student(String studentId, String name, String address, String contact, LocalDate dob, String gender, LocalDate dateRegistered) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.dateRegistered = dateRegistered;
    }

    @Override
    public String toString() {
        return studentId;
    }
}
