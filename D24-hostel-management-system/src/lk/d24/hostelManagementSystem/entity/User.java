package lk.d24.hostelManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 6/18/2022 - 9:35 PM
 * project D24-hostel-management-system
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class User {
    @Id
    private String userid;
    private String userName;
    private String password;
    private LocalDate dateCreated;
}
