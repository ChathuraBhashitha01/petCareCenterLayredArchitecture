package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;
import lk.ijse.petcarecenter.model.AddPasswordDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddPasswordDAOImpl {
    public static ResultSet select(String user) throws SQLException {
        String sql = "SELECT userName,password FROM userLogin WHERE user = ?";

        ResultSet resultSet = SQLUtil.execute(sql,user);
        return resultSet;
    }

    public static boolean update(AddPasswordDTO addPasswordDTO) throws SQLException {
        String sql = "UPDATE userLogin SET userName=?, password = ? WHERE user=?";

        return SQLUtil.execute(
                sql,
                addPasswordDTO.getUserName(),
                addPasswordDTO.getPassword(),
                addPasswordDTO.getUser()
        );
    }
}
