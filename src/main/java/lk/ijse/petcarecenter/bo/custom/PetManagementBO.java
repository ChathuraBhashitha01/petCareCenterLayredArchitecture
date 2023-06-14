package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.OwnerDTO;
import lk.ijse.petcarecenter.model.PetDTO;

import java.sql.SQLException;

public interface PetManagementBO extends SuperBO {
    public boolean seachValiedPetID(String id) throws SQLException;
    public PetDTO searchPetDetails(String id) throws SQLException ;
    public OwnerDTO searchOwnerDetails(String id) throws SQLException;
    public boolean searchValiedOwnerID(String id) throws SQLException;
    public boolean updatePetDetails(PetDTO petDTO) throws SQLException;
    public boolean updateOwnerDetails(OwnerDTO ownerDTO) throws SQLException;
    public boolean delectPetDetails(String id) throws SQLException;
}
