package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.SchduleBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.DoctorDAO;
import lk.ijse.petcarecenter.dao.custom.SchduleDAO;
import lk.ijse.petcarecenter.entity.Schdule;
import lk.ijse.petcarecenter.model.SchduleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchduleBOImpl implements SchduleBO {
    SchduleDAO schduleDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SCHDULE);
    DoctorDAO doctorDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DOCTOR);

    public String getNextSchduleId() throws SQLException {
        return schduleDAO.getNextId();
    }

    public SchduleDTO searchSchduleDetails(String id) throws SQLException {
        Schdule schdule=schduleDAO.search(id);
        return new SchduleDTO(schdule.getSchduleID(),schdule.getInTime(),schdule.getOutTime(),schdule.getDate(),schdule.getDoctorID());
    }

    public boolean seachValiedSchduleID(String id) throws SQLException {
        return schduleDAO.seachID(id);
    }

    public boolean saveSchdule(SchduleDTO schduleDTO) throws SQLException {
        return schduleDAO.save(new Schdule(schduleDTO.getSchduleID(),schduleDTO.getInTime(),schduleDTO.getOutTime(),schduleDTO.getDate(),schduleDTO.getDoctorID()));
    }

    public boolean deleteSchdule(String id) throws SQLException {
        return schduleDAO.delete(id);
    }

    public List<SchduleDTO> getAllSchdule() throws SQLException {
        List<Schdule> all=schduleDAO.getAll();
        List<SchduleDTO> list=new ArrayList<>();
        for (Schdule i:all) {
            list.add(new SchduleDTO(i.getSchduleID(),i.getInTime(),i.getOutTime(),i.getDate(),i.getDoctorID()));
        }
        return list;
    }

    public boolean updateSchdule(SchduleDTO schduleDTO) throws SQLException {
        return schduleDAO.update(new Schdule(schduleDTO.getSchduleID(),schduleDTO.getInTime(),schduleDTO.getOutTime(),schduleDTO.getDate(),schduleDTO.getDoctorID()));
    }

    public boolean seachValiedDoctorID(String doctorId) throws SQLException {
        return doctorDAO.seachID(doctorId);
    }
}
