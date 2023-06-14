package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.MedicineDetailsDAO;
import lk.ijse.petcarecenter.entity.MedicineDetail;

import java.sql.SQLException;
import java.util.List;

public class MedicineDetailsDAOImpl implements MedicineDetailsDAO {
    @Override
    public List<MedicineDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public MedicineDetail search(String id) throws SQLException {
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

    public  boolean save(MedicineDetail dto) throws SQLException {

        String sql = "INSERT INTO medicinedetail(petID, paymentID, medicineID, date)" + "VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getPetID(), dto.getPaymentID(),dto.getMedicineID(), dto.getDate());
    }

    @Override
    public boolean update(MedicineDetail dto) throws SQLException {
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
