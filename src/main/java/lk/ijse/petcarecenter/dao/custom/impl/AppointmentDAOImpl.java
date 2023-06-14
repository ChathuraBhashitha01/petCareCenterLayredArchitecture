package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.AppointmentDAO;
import lk.ijse.petcarecenter.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public List<Appointment> getAll() throws SQLException {
        List<Appointment> data = new ArrayList<>();
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM appointment");
        while (resultSet.next()) {
            data.add(new Appointment(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }
        return data;
    }

    @Override
    public Appointment search(String id) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE appointmentID = ?";
        ResultSet resultSet= SQLUtil.execute(sql, id);
        if (resultSet.next()){
            return new Appointment(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT appointmentID FROM appointment ORDER BY appointmentID DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String id) {
        if(id != null) {
            String[] strings = id.split("AP");
            int ids = Integer.parseInt(strings[1]);
            ids++;
            return "AP" + ids;
        }
        return "AP1";
    }

    @Override
    public boolean save(Appointment dto) throws SQLException {
        String sql = "INSERT INTO appointment(appointmentID, petID, schduleID, date, serviceID)" +
                "VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getAppointmentID(), dto.getPetID(), dto.getSchduleID(), dto.getDate(), dto.getServiceID());
    }

    @Override
    public boolean update(Appointment dto) throws SQLException {
        String sql = "UPDATE appointment SET petID = ?, schduleID = ?,date=?,serviceID=? WHERE appointmentID=?";

        return SQLUtil.execute(sql, dto.getPetID(), dto.getSchduleID(), dto.getDate(), dto.getServiceID(), dto.getAppointmentID());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM appointment  WHERE appointmentID = ?";

        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean seachID(String id) throws SQLException {
        String sql ="SELECT appointmentID FROM appointment";
        ResultSet resultSet= SQLUtil.execute(sql);

        while (resultSet.next()) {
            if(id.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
}
