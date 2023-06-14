package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.SchduleDTO;

import java.sql.SQLException;
import java.util.List;

public interface SchduleBO extends SuperBO {
    public String getNextSchduleId() throws SQLException;
    public SchduleDTO searchSchduleDetails(String id) throws SQLException;
    public boolean seachValiedSchduleID(String id) throws SQLException;
    public boolean saveSchdule(SchduleDTO schduleDTO) throws SQLException;
    public boolean deleteSchdule(String id) throws SQLException;
    public List<SchduleDTO> getAllSchdule() throws SQLException;
    public boolean updateSchdule(SchduleDTO schduleDTO) throws SQLException;
    public boolean seachValiedDoctorID(String doctorId) throws SQLException;
}
