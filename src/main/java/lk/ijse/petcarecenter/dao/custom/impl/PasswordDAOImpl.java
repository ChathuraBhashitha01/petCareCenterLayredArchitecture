package lk.ijse.petcarecenter.dao.custom.impl;

import lk.ijse.petcarecenter.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordDAOImpl {

    public static ResultSet search(String user) throws SQLException {
        String sql = "SELECT * FROM userLogin WHERE user = ?";
       return SQLUtil.execute(sql,user);
    }
}
