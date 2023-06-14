package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.ItemDetailsDAO;
import lk.ijse.petcarecenter.entity.ItemDetail;

import java.sql.SQLException;
import java.util.List;

public class ItemDetailsDAOImpl implements ItemDetailsDAO {
    @Override
    public List<ItemDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public ItemDetail search(String id) throws SQLException {
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

    public  boolean save(ItemDetail dto) throws SQLException {
        String sql = "INSERT INTO itemdetail(petID, paymentID, itemCode, date)" + "VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql,dto.getPetID(),dto.getPaymentID(),dto.getItemCode(),dto.getDate() );
    }

    @Override
    public boolean update(ItemDetail dto) throws SQLException {
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
