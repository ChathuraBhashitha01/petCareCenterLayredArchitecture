package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.PetRegistrationBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.OwnerDAO;
import lk.ijse.petcarecenter.dao.custom.PetDAO;
import lk.ijse.petcarecenter.entity.Owner;
import lk.ijse.petcarecenter.entity.Pet;
import lk.ijse.petcarecenter.model.OwnerDTO;
import lk.ijse.petcarecenter.model.PetDTO;

import java.sql.SQLException;

public class PetRegistrationBOImpl implements PetRegistrationBO {
    PetDAO petDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET);
    OwnerDAO ownerDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.OWNER);

    public String getNextPetId() throws SQLException {
        return petDAO.getNextId();
    }

    public boolean seachValiedPetID(String id) throws SQLException {
        return petDAO.seachID(id);
    }

    public boolean savePetDetails(PetDTO petDTO) throws SQLException {
        return petDAO.save(new Pet(petDTO.getPetID(),petDTO.getName(),petDTO.getAge(),petDTO.getType(),petDTO.getBread(),petDTO.getOwnerID()));
    }

    public String getNextOwnerId() throws SQLException {
        return petDAO.getNextId();
    }

    public boolean seachValiedOwnerID(String id) throws SQLException {
        return ownerDAO.seachID(id);
    }

    public boolean saveOwnerDetails(OwnerDTO ownerDTO) throws SQLException {
        return ownerDAO.save(new Owner(ownerDTO.getOwnerID(),ownerDTO.getName(),ownerDTO.getPhoneNumber()));
    }
}
