package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.PetManagementBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.OwnerDAO;
import lk.ijse.petcarecenter.dao.custom.PetDAO;
import lk.ijse.petcarecenter.entity.Owner;
import lk.ijse.petcarecenter.entity.Pet;
import lk.ijse.petcarecenter.model.OwnerDTO;
import lk.ijse.petcarecenter.model.PetDTO;

import java.sql.SQLException;

public class PetManagementBOImpl implements PetManagementBO {
    PetDAO petDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET);
    OwnerDAO ownerDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.OWNER);

    public boolean seachValiedPetID(String id) throws SQLException {
        return petDAO.seachID(id);
    }

    public PetDTO searchPetDetails(String id) throws SQLException {
        Pet pet= petDAO.search(id);
        return new PetDTO(pet.getPetID(),pet.getName(),pet.getAge(),pet.getType(),pet.getBread(),pet.getOwnerID());
    }

    public OwnerDTO searchOwnerDetails(String id) throws SQLException {
        Owner owner= ownerDAO.search(id);
        return new OwnerDTO(owner.getOwnerID(),owner.getName(),owner.getPhoneNumber());
    }

    public boolean searchValiedOwnerID(String id) throws SQLException {
        return ownerDAO.seachID(id);
    }

    public boolean updatePetDetails(PetDTO petDTO) throws SQLException {
        return petDAO.update(new Pet(petDTO.getPetID(),petDTO.getName(),petDTO.getAge(),petDTO.getType(),petDTO.getBread(),petDTO.getOwnerID()));
    }

    public boolean updateOwnerDetails(OwnerDTO ownerDTO) throws SQLException {
        return ownerDAO.update(new Owner(ownerDTO.getOwnerID(),ownerDTO.getName(),ownerDTO.getPhoneNumber()));
    }

    public boolean delectPetDetails(String id) throws SQLException {
        return petDAO.delete(id);
    }
}
