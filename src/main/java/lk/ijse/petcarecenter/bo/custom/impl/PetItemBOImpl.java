package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.PetItemBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.PetItemDAO;
import lk.ijse.petcarecenter.entity.PetItem;
import lk.ijse.petcarecenter.model.PetItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetItemBOImpl implements PetItemBO {
    PetItemDAO petItemDAO=  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET_ITEM);

    public String getNextItemId() throws SQLException {
        return petItemDAO.getNextId();
    }

    public PetItemDTO searchPetItemId(String id) throws SQLException {
        PetItem item= petItemDAO.search(id);
        return new PetItemDTO(item.getItemCode(),item.getName(),item.getPrice(),item.getDescription());
    }

    public boolean seachValiedItemID(String id) throws SQLException {
        return petItemDAO.seachID(id);
    }

    public boolean savePetItem(PetItemDTO petItemDTO) throws SQLException {
        return petItemDAO.save(new PetItem(petItemDTO.getItemCode(),petItemDTO.getName(),petItemDTO.getPrice(),petItemDTO.getQuantity()));
    }

    public List<PetItemDTO> getAllPetItem() throws SQLException {
        List<PetItem> all=petItemDAO.getAll();
        List<PetItemDTO> list=new ArrayList<>();
        for (PetItem i:all) {
            list.add(new PetItemDTO(i.getItemCode(),i.getName(),i.getPrice(),i.getDescription()));
        }
        return list;
    }

    public boolean deletePetItem(String id) throws SQLException {
        return petItemDAO.delete(id);
    }

    public boolean updatePetItem(PetItemDTO petItemDTO) throws SQLException {
        return petItemDAO.update(new PetItem(petItemDTO.getItemCode(),petItemDTO.getName(),petItemDTO.getPrice(),petItemDTO.getQuantity()));
    }
}
