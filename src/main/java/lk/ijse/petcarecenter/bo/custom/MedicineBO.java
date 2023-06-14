package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.MedicineDTO;

import java.sql.SQLException;
import java.util.List;

public interface MedicineBO extends SuperBO {
    public MedicineDTO searchMedicineId(String id) throws SQLException;
    public String getNextOrderId() throws SQLException;
    public boolean seachValiedID(String id) throws SQLException;
    public boolean saveMedicine(MedicineDTO medicineDTO) throws SQLException;
    public List<MedicineDTO> getAllMedicine() throws SQLException;
    public boolean updateMedicine(MedicineDTO medicineDTO) throws SQLException;
    public boolean delectMedicine(String id) throws SQLException;
}
