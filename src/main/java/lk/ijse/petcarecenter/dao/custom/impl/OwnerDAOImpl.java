package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.OwnerDAO;
import lk.ijse.petcarecenter.entity.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {

    public  boolean save(Owner ownerDTO) throws SQLException {
        String sql = "INSERT INTO owner( ownerID, name, phoneNumber)" +
                "VALUES(?, ?, ?)";

        return SQLUtil.execute(
                sql,
                ownerDTO.getOwnerID(),
                ownerDTO.getName(),
                ownerDTO.getPhoneNumber()

        );
    }

    @Override
    public List<Owner> getAll() throws SQLException {
        return null;
    }

    public  Owner search(String ownerId) throws SQLException {
        String sql = "SELECT * FROM owner WHERE ownerID = ?";
        ResultSet resultSet= SQLUtil.execute(sql,ownerId);
        if (resultSet.next()){
            return new Owner(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );

        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT ownerID FROM owner ORDER BY ownerID DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String id) {
        if(id != null) {
            String[] strings = id.split("OW");
            int ids = Integer.parseInt(strings[1]);
            ids++;
            return "OW" + ids;
        }
        return "OW1";
    }

    public  boolean update(Owner ownerDTO) throws SQLException {
        String sql = "UPDATE owner SET name = ?, phoneNumber = ? WHERE ownerID = ?";

        return SQLUtil.execute(
                sql,
                ownerDTO.getName(),
                ownerDTO.getPhoneNumber(),
                ownerDTO.getOwnerID()

        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    public  boolean seachID(String ownerId) throws SQLException {
        String sql ="SELECT ownerID FROM owner";
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            if(ownerId.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
}
