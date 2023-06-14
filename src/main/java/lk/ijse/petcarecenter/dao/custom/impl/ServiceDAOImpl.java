package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.ServiceDAO;
import lk.ijse.petcarecenter.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public List<Service> getAll() throws SQLException {
        return null;
    }

    public  Service search(String id) throws SQLException {
        String sql="SELECT * FROM service WHERE serviceID = ?";
        ResultSet resultSet= SQLUtil.execute(sql,id);

        if (resultSet.next()) {
            return new Service(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        return null;
    }

    @Override
    public String splitId(String id) {
        return null;
    }

    @Override
    public boolean save(Service dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Service dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    public  List<String> loadCodes() throws SQLException {
        String sql="SELECT serviceID FROM service";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data =new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public  boolean seachID(String serviceID) throws SQLException {
        String sql="SELECT serviceID FROM service";
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()) {
            if(serviceID.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
}
