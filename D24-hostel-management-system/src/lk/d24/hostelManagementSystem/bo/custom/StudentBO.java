package lk.d24.hostelManagementSystem.bo.custom;

import javafx.collections.ObservableList;
import lk.d24.hostelManagementSystem.bo.SuperBO;
import lk.d24.hostelManagementSystem.dto.StudentDTO;
import lk.d24.hostelManagementSystem.presentation.tm.StudentNotPaidTM;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:49 PM
 * project D24-hostel-management-system
 */

public interface StudentBO extends SuperBO {

    boolean saveStudent(StudentDTO studentDTO);

    boolean deleteStudent(String id);

    String generateNextStudentId();

    ObservableList<StudentDTO> getAllStudents();

    boolean UpdateStudent(StudentDTO dto);

    StudentDTO getSpecificStudent(String newValue);

    int getStudentCount();

    int getStudentsCountByGender(String male);
}
