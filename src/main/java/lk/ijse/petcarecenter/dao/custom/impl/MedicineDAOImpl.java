package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.MedicineDAO;
import lk.ijse.petcarecenter.entity.Medicine;
import lk.ijse.petcarecenter.model.MedicineCartDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public List<Medicine> getAll() throws SQLException {
        String sql = "SELECT * FROM medicine";
        List<Medicine> data = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new Medicine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }
        return data;
    }

    @Override
    public Medicine search(String id) throws SQLException {
        String sql = "SELECT * FROM medicine WHERE medicineID = ?";

        ResultSet resultSet = SQLUtil.execute(sql,id);
        if(resultSet.next()) {
            return new Medicine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT medicineID FROM medicine ORDER BY medicineID DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String id) {
        if(id != null) {
            String[] strings = id.split("M");
            int ids = Integer.parseInt(strings[1]);
            ids++;
            return "M" + ids;
        }
        return "M1";
    }

    @Override
    public boolean save(Medicine dto) throws SQLException {
        String sql = "INSERT INTO medicine(medicineID, name, quantity,price)" + "VALUES(?, ?, ?,?)";
        return SQLUtil.execute(sql, dto.getMedicineID(), dto.getName(), dto.getQuantity(), dto.getPrice());
    }

    @Override
    public boolean update(Medicine dto) throws SQLException {
        String sql = "UPDATE medicine SET name = ?, quantity = ?, price=? WHERE medicineID=?";
        return SQLUtil.execute(sql, dto.getName(), dto.getQuantity(), dto.getPrice(), dto.getMedicineID());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM medicine  WHERE medicineID = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean seachID(String id) throws SQLException {
        String sql="SELECT medicineID FROM medicine";
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            if(id.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
    public  List<String> loadCodes() throws SQLException {
        String sql ="SELECT medicineID FROM medicine";
        ResultSet resultSet =SQLUtil.execute(sql);
        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public  boolean medicineQuantityUpdate(List<MedicineCartDTO> medicineDetailsDTOList) throws SQLException {
        String medicineID = null;
        int quantity = 0;
        int medicineQuantity = 0;
        int totalQty = 0;
        for (MedicineCartDTO medicineCartDTO : medicineDetailsDTOList) {
            medicineID = medicineCartDTO.getMedicineID();
            quantity = medicineCartDTO.getMedicineQuantity();
        }
        String sql = "SELECT  quantity FROM medicine WHERE medicineID = ?";

        ResultSet resultSet = SQLUtil.execute(sql, medicineID);
        if (resultSet.next()) {
            medicineQuantity = resultSet.getInt(1);
        }
        totalQty = medicineQuantity - quantity;
        String newSql = "UPDATE medicine SET   quantity= ? WHERE medicineID=?";
        return SQLUtil.execute(
                newSql,
                totalQty,
                medicineID
        );
    }
}
