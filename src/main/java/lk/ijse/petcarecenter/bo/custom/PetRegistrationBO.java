package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.OwnerDTO;
import lk.ijse.petcarecenter.model.PetDTO;

import java.sql.SQLException;

public interface PetRegistrationBO extends SuperBO {
    public String getNextPetId() throws SQLException;
    public boolean seachValiedPetID(String id) throws SQLException;
    public boolean savePetDetails(PetDTO petDTO) throws SQLException;
    public String getNextOwnerId() throws SQLException;
    public boolean seachValiedOwnerID(String id) throws SQLException;
    public boolean saveOwnerDetails(OwnerDTO ownerDTO) throws SQLException;
}
