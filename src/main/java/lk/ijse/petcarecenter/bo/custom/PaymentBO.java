package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.*;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public  boolean payment(PaymentDTO dto) throws SQLException;
    public String getNextOrderId() throws SQLException;
    public boolean seachValiedPetID(String petID) throws SQLException;
    public PetDTO searchPetDetails(String petID) throws SQLException;
    public OwnerDTO searchOwnerDetails(String ownerId) throws SQLException;
    public List<String> loadItemCodes() throws SQLException;
    public List<String> loadMedicineCodes() throws SQLException;
    public PetItemDTO searchItemCodes(String code) throws SQLException;
    public MedicineDTO searchMedicineId(String id) throws SQLException;
    public List<String> loadServiceID() throws SQLException;
    public ServiceDTO searchServiceIDs(String id) throws SQLException;
}
