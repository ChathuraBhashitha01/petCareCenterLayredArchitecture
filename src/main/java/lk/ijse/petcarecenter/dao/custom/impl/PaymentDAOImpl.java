package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.PaymentDAO;
import lk.ijse.petcarecenter.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    public  boolean save(Payment dto) throws SQLException {
        String sql = "INSERT INTO payment(paymentID, petID, itemSalary, serviceSalary, doctorSalary,clinicPayment)" + "VALUES(?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getPaymentID(), dto.getPetID(), dto.getItemSalary(), dto.getServiceSalary(), dto.getDoctorSalary(), dto.getClinicPayment());
    }

    @Override
    public boolean update(Payment dto) throws SQLException {
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

    @Override
    public List<Payment> getAll() throws SQLException {
        return null;
    }

    @Override
    public Payment search(String id) throws SQLException {
        return null;
    }

    public  String getNextId() throws SQLException {
        String sql = "SELECT paymentID FROM payment ORDER BY paymentID DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("PM");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "PM" + id;
        }
        return "PM1";
    }

}
