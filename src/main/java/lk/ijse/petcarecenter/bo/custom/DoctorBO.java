package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.DoctorDTO;

import java.sql.SQLException;
import java.util.List;

public interface DoctorBO extends SuperBO {
    public String getNextDoctorId() throws SQLException;
    public DoctorDTO searchDoctors(String id) throws SQLException;
    public boolean saveDoctors(DoctorDTO doctorDTO) throws SQLException;
    public boolean updateDoctorDetails(DoctorDTO doctorDTO) throws SQLException;
    public boolean deleteDoctor(String id) throws SQLException;
    public List<DoctorDTO> getAllDoctors() throws SQLException;
    public boolean seachValiedDoctorID(String id) throws SQLException;
}
