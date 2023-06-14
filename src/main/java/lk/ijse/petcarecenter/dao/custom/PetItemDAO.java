package lk.ijse.petcarecenter.dao.custom;

import lk.ijse.petcarecenter.dao.CrudDAO;
import lk.ijse.petcarecenter.entity.PetItem;
import lk.ijse.petcarecenter.model.ItemCartDTO;

import java.sql.SQLException;
import java.util.List;

public interface PetItemDAO extends CrudDAO<PetItem,String> {
    List<String> loadCodes() throws SQLException;
    boolean itemQuantityUpdate(List<ItemCartDTO> itemDetailsDTOList) throws SQLException;
}
