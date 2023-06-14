package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.SchduleDAO;
import lk.ijse.petcarecenter.entity.Schdule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchduleDAOImpl implements SchduleDAO {
    @Override
    public List<Schdule> getAll() throws SQLException {
        String sql = "SELECT * FROM schdule";

        List<Schdule> data = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Schdule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            ));
        }
        return data;
    }

    @Override
    public Schdule search(String id) throws SQLException {
        String sql = "SELECT * FROM schdule WHERE schduleID = ?";

        ResultSet resultSet = SQLUtil.execute(sql,id);
        if(resultSet.next()) {
            return new Schdule(
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
        String sql = "SELECT schduleID FROM schdule ORDER BY schduleID DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String id) {
        if(id != null) {
            String[] strings = id.split("SC");
            int ids = Integer.parseInt(strings[1]);
            ids++;
            return "SC" + ids;
        }
        return "SC1";
    }

    @Override
    public boolean save(Schdule dto) throws SQLException {
        String sql = "INSERT INTO schdule( schduleID, inTime, outTime, date,doctorID )" + "VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getSchduleID(), dto.getInTime(), dto.getOutTime(), dto.getDate(), dto.getDoctorID());
    }

    @Override
    public boolean update(Schdule dto) throws SQLException {
        String sql = "UPDATE schdule SET inTime = ?, outTime = ?,date=?,doctorID=? WHERE schduleID=?";
        return SQLUtil.execute(sql, dto.getInTime(), dto.getOutTime(), dto.getDate(), dto.getDoctorID(), dto.getSchduleID());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM schdule  WHERE schduleID = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean seachID(String id) throws SQLException {
        String sql="SELECT schduleID FROM schdule";
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()) {
            if(id.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
}
