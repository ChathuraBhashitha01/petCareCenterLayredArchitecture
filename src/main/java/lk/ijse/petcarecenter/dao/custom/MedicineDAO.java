package lk.ijse.petcarecenter.dao.custom;

import lk.ijse.petcarecenter.dao.CrudDAO;
import lk.ijse.petcarecenter.entity.Medicine;
import lk.ijse.petcarecenter.model.MedicineCartDTO;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDAO extends CrudDAO<Medicine,String> {
     List<String> loadCodes() throws SQLException;
     boolean medicineQuantityUpdate(List<MedicineCartDTO> medicineDetailsDTOList) throws SQLException;

}
