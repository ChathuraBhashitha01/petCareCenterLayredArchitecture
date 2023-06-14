package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.DoctorDAO;
import lk.ijse.petcarecenter.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {
    @Override
    public List<Doctor> getAll() throws SQLException {
        String sql = "SELECT * FROM doctor";

        List<Doctor> data = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            ));
        }
        return data;
    }

    @Override
    public Doctor search(String id) throws SQLException {
        String sql = "SELECT * FROM doctor WHERE doctorID = ?";

        ResultSet resultSet = SQLUtil.execute(sql,id);
        if(resultSet.next()) {
            return new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT doctorID FROM doctor ORDER BY doctorID DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String id) {
        if(id != null) {
            String[] strings = id.split("DO");
            int ids = Integer.parseInt(strings[1]);
            ids++;
            return "DO" + ids;
        }
        return "DO1";
    }

    @Override
    public boolean save(Doctor dto) throws SQLException {
        String sql = "INSERT INTO doctor(doctorID, name, contact)" + "VALUES(?, ?, ?)";
        return SQLUtil.execute(sql, dto.getDoctorID(), dto.getName(), dto.getContact());
    }

    @Override
    public boolean update(Doctor dto) throws SQLException {
        String sql = "UPDATE doctor SET name = ?, contact = ? WHERE doctorID=?";
        return SQLUtil.execute(sql, dto.getName(), dto.getContact(), dto.getDoctorID());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM doctor  WHERE doctorID = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean seachID(String id) throws SQLException {
        String sql="SELECT doctorID FROM doctor";
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            if(id.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
}
