package lk.ijse.petcarecenter.dao.custom;

import lk.ijse.petcarecenter.dao.CrudDAO;
import lk.ijse.petcarecenter.entity.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO extends CrudDAO<Service,String> {
    List<String> loadCodes() throws SQLException;
}
