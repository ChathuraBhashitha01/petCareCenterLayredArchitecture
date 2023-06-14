package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.AppointmentDTO;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBO extends SuperBO {
    public String getNextAppointmentId() throws SQLException;
    public AppointmentDTO searchAppointmentDetails(String text) throws SQLException;
    public boolean seachAppointmentIDs(String appointmentID) throws SQLException;
    public boolean saveAppointment(AppointmentDTO appointmentDTO) throws SQLException;
    public boolean delectAppointment(String id) throws SQLException;
    public List<AppointmentDTO> getAllAppointment() throws SQLException;
    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException;
    public boolean seachValiedPetID(String petID) throws SQLException;
    public boolean seachValiedSchduleID(String schduleID) throws SQLException;
    public boolean seachValiedServiceID(String serviceID) throws SQLException;
}
