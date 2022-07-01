package lk.d24.hostelManagementSystem.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.d24.hostelManagementSystem.bo.custom.StudentBO;
import lk.d24.hostelManagementSystem.dao.DAOFactory;
import lk.d24.hostelManagementSystem.dao.custom.StudentDAO;
import lk.d24.hostelManagementSystem.dto.StudentDTO;
import lk.d24.hostelManagementSystem.entity.Student;
import lk.d24.hostelManagementSystem.presentation.tm.StudentNotPaidTM;

import java.util.ArrayList;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:49 PM
 * project D24-hostel-management-system
 */

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO dto) {
        return studentDAO.save(new Student(dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getDob(),
                dto.getGender(),
                dto.getDateRegistered()
        ));
    }

    @Override
    public boolean deleteStudent(String id) {
        System.out.println("bo");
        return studentDAO.delete(id);
    }

    @Override
    public String generateNextStudentId() {
        ArrayList<Student> all = studentDAO.getAll();
        if (all.size() > 0) {
            int newId = Integer.parseInt(all.get(all.size()-1).getStudentId().replace("S","")) +1;
            return String.format("S%03d",newId);
        }else
            return "S001";
    }

    @Override
    public ObservableList<StudentDTO> getAllStudents() {
        ObservableList<StudentDTO> dtos = FXCollections.observableArrayList();
        ArrayList<Student> all = studentDAO.getAll();
        for (Student entity : all) {
            dtos.add(new StudentDTO(
                    entity.getStudentId(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContact(),
                    entity.getDob(),
                    entity.getGender(),
                    entity.getDateRegistered()
            ));
        }
        return dtos;
    }

    @Override
    public boolean UpdateStudent(StudentDTO dto) {
        return studentDAO.update(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getDob(),
                dto.getGender(),
                dto.getDateRegistered()
        ));
    }

    @Override
    public StudentDTO getSpecificStudent(String id) {
        Student student = studentDAO.getSpecificEntity(id);
        return new StudentDTO(
                student.getStudentId(),
                student.getName(),
                student.getAddress(),
                student.getContact(),
                student.getDob(),
                student.getGender(),
                student.getDateRegistered()
        );
    }

    @Override
    public int getStudentCount() {
        return studentDAO.getAll().size();
    }

    @Override
    public int getStudentsCountByGender(String gender) {
        int i = 0;
        ArrayList<Student> all = studentDAO.getAll();
        for (Student student : all) {
            if (student.getGender().equals(gender)){
                i++;
            }
        }
        return i;
    }
}
