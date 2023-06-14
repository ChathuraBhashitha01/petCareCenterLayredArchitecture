package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.DoctorBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.DoctorDAO;
import lk.ijse.petcarecenter.entity.Doctor;
import lk.ijse.petcarecenter.model.DoctorDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorBOImpl implements DoctorBO {
    DoctorDAO doctorDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DOCTOR);

    public String getNextDoctorId() throws SQLException {
        return doctorDAO.getNextId();
    }

    public DoctorDTO searchDoctors(String id) throws SQLException {
        Doctor doctor= doctorDAO.search(id);
        return new DoctorDTO(doctor.getDoctorID(),doctor.getName(),doctor.getContact());
    }

    public boolean saveDoctors(DoctorDTO doctorDTO) throws SQLException {
        return doctorDAO.save(new Doctor(doctorDTO.getDoctorID(),doctorDTO.getName(),doctorDTO.getPhoneNumber()));
    }

    public boolean updateDoctorDetails(DoctorDTO doctorDTO) throws SQLException {
        return doctorDAO.update(new Doctor(doctorDTO.getDoctorID(),doctorDTO.getName(),doctorDTO.getPhoneNumber()));
    }

    public boolean deleteDoctor(String id) throws SQLException {
        return doctorDAO.delete(id);
    }

    public List<DoctorDTO> getAllDoctors() throws SQLException {
        List<Doctor> all= doctorDAO.getAll();
        List<DoctorDTO> list=new ArrayList<>();
        for (Doctor d:all) {
            list.add(new DoctorDTO(d.getDoctorID(),d.getName(),d.getContact()));
        }
        return list;
    }

    public boolean seachValiedDoctorID(String id) throws SQLException {
        return doctorDAO.seachID(id);
    }
}
