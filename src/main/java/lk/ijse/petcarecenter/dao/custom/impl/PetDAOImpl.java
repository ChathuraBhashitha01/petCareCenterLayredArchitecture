package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.PetDAO;
import lk.ijse.petcarecenter.entity.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    public  String getNextId() throws SQLException {
        String sql = "SELECT petID FROM pet ORDER BY petID DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }
    public   String splitId(String id) {
        if(id != null) {
            String[] strings = id.split("P");
            int ids = Integer.parseInt(strings[1]);
            ids++;
            return "P" + ids;
        }
        return "P1";
    }

    public  boolean save(Pet dto) throws SQLException {
        String sql = "INSERT INTO pet( petID, name, age, type, bread, ownerID)" + "VALUES(?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getPetID(),dto.getName(), dto.getAge(), dto.getType(), dto.getBread(), dto.getOwnerID());
    }

    @Override
    public List<Pet> getAll() throws SQLException {
        return null;
    }

    public  Pet search(String id) throws SQLException {
        String sql = "SELECT * FROM pet WHERE petID = ?";
        ResultSet resultSet= SQLUtil.execute(sql,id);
        if (resultSet.next()){
            return new Pet(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getInt(3),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7)
            );

        }
        return null;
    }

    public  boolean update(Pet dto) throws SQLException {
        String sql = "UPDATE pet SET name = ?, age = ?, type = ?, bread = ?, ownerID = ? WHERE petID=?";
        return SQLUtil.execute(sql, dto.getName(), dto.getAge(), dto.getType(), dto.getBread(), dto.getOwnerID(), dto.getPetID());
    }

    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM pet  WHERE petID = ?";
        return SQLUtil.execute(sql, id);
    }

    public  boolean seachID(String id) throws SQLException {
        String sql ="SELECT petID FROM pet";
        ResultSet resultSet = SQLUtil.execute(sql);


        while (resultSet.next()) {
            if(id.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
}
