package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.AppointmentBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.AppointmentDAO;
import lk.ijse.petcarecenter.dao.custom.PetDAO;
import lk.ijse.petcarecenter.dao.custom.SchduleDAO;
import lk.ijse.petcarecenter.dao.custom.ServiceDAO;
import lk.ijse.petcarecenter.entity.Appointment;
import lk.ijse.petcarecenter.model.AppointmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBOImpl implements AppointmentBO {
    AppointmentDAO appointmentDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    SchduleDAO schduleDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SCHDULE);
    PetDAO petDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET);
    ServiceDAO serviceDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SERVICR);

    public String getNextAppointmentId() throws SQLException {
        return appointmentDAO.getNextId();
    }

    public AppointmentDTO searchAppointmentDetails(String text) throws SQLException {
        Appointment appointment= appointmentDAO.search(text);
        return new AppointmentDTO(appointment.getAppointmentID(),appointment.getPetID(),appointment.getSchduleID(),appointment.getDate(),appointment.getServiceID());
    }

    public boolean seachAppointmentIDs(String appointmentID) throws SQLException {
        return appointmentDAO.seachID(appointmentID);
    }

    public boolean saveAppointment(AppointmentDTO appointmentDTO) throws SQLException {
        return appointmentDAO.save(new Appointment(appointmentDTO.getAppointmentID(),appointmentDTO.getPetID(),appointmentDTO.getSchduleID(),appointmentDTO.getDate(),appointmentDTO.getServiceID()));
    }

    public boolean delectAppointment(String id) throws SQLException {
        return appointmentDAO.delete(id);
    }

    public List<AppointmentDTO> getAllAppointment() throws SQLException {
        List<Appointment> all=appointmentDAO.getAll();
        List<AppointmentDTO> list=new ArrayList<>();
        for (Appointment a:all) {
            list.add(new AppointmentDTO(a.getAppointmentID(),a.getPetID(),a.getSchduleID(),a.getDate(),a.getServiceID()));
        }
        return list;
    }

    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException {
        return appointmentDAO.update(new Appointment(appointmentDTO.getAppointmentID(),appointmentDTO.getPetID(),appointmentDTO.getSchduleID(),appointmentDTO.getDate(),appointmentDTO.getServiceID()));
    }

    public boolean seachValiedPetID(String petID) throws SQLException {
        return petDAO.seachID(petID);
    }

    public boolean seachValiedSchduleID(String schduleID) throws SQLException {
        return schduleDAO.seachID(schduleID);
    }

    public boolean seachValiedServiceID(String serviceID) throws SQLException {
        return serviceDAO.seachID(serviceID);
    }
}
