package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.dao.custom.PetItemDAO;
import lk.ijse.petcarecenter.entity.PetItem;
import lk.ijse.petcarecenter.model.ItemCartDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetItemDAOImpl implements PetItemDAO {
    @Override
    public List<PetItem> getAll() throws SQLException {
        String sql = "SELECT * FROM petitem";
        List<PetItem> data = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            data.add(new PetItem(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            ));
        }
        return data;
    }

    @Override
    public PetItem search(String id) throws SQLException {
        String sql="SELECT * FROM petitem WHERE ItemCode = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);
        if(resultSet.next()) {
            return new PetItem(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT itemCode FROM petitem ORDER BY itemCode DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);

    }

    @Override
    public String splitId(String id) {
        if(id != null) {
            String[] strings = id.split("PI");
            int ids = Integer.parseInt(strings[1]);
            ids++;
            return "PI" + ids;
        }
        return "PI1";
    }

    @Override
    public boolean save(PetItem dto) throws SQLException {
        String sql = "INSERT INTO petitem(itemCode, name, price, description)" + "VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getItemCode(), dto.getName(), dto.getPrice(), dto.getDescription());
    }

    @Override
    public boolean update(PetItem dto) throws SQLException {
        String sql = "UPDATE petitem SET name = ?, price = ?,description=? WHERE itemCode=?";
        return SQLUtil.execute(sql, dto.getName(), dto.getPrice(),dto.getDescription(), dto.getItemCode());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM petitem  WHERE itemCode = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean seachID(String id) throws SQLException {
        String sql="SELECT itemCode FROM petitem";
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            if(id.equals(resultSet.getString(1))){
                return true;
            }
        }
        return false;
    }
    public  List<String> loadCodes() throws SQLException {
        String sql= "SELECT itemCode FROM petitem";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public  boolean itemQuantityUpdate(List<ItemCartDTO> itemDetailsDTOList) throws SQLException {
        String itemCode=null;
        int quantity=0;
        int itemQuantity=0;
        int totalQty=0;
        for(ItemCartDTO itemCartDTO : itemDetailsDTOList) {
            itemCode= itemCartDTO.getItemCode();
            quantity= itemCartDTO.getItemQuantity();
        }
        String sql="SELECT  description FROM petitem WHERE itemCode = ?";

        ResultSet resultSet = SQLUtil.execute(sql,itemCode);
        if (resultSet.next()) {
            itemQuantity = resultSet.getInt(1);
        }
        totalQty=itemQuantity-quantity;

        String newSql = "UPDATE petitem SET   description= ? WHERE itemCode=?";
        return SQLUtil.execute(newSql,totalQty,itemCode);
    }
}
