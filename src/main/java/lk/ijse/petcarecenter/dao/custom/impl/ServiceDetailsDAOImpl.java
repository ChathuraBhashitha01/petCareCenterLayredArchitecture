package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.ServiceDetailsDAO;
import lk.ijse.petcarecenter.entity.ServiceDetail;

import java.sql.SQLException;
import java.util.List;

public class ServiceDetailsDAOImpl implements ServiceDetailsDAO {
    @Override
    public List<ServiceDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public ServiceDetail search(String id) throws SQLException {
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

    public  boolean save(ServiceDetail dto) throws SQLException {
        String sql = "INSERT INTO servicedetail(petID, paymentID, serviceID, date)" + "VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getPetID(), dto.getPaymentID(), dto.getServiceID(), dto.getDate());
    }

    @Override
    public boolean update(ServiceDetail dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean seachID(String id) throws SQLException {
        return false;
    }
}
