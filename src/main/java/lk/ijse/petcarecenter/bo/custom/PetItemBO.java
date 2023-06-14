package lk.ijse.petcarecenter.bo.custom;

import lk.ijse.petcarecenter.bo.SuperBO;
import lk.ijse.petcarecenter.model.PetItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface PetItemBO extends SuperBO {
    public String getNextItemId() throws SQLException;
    public PetItemDTO searchPetItemId(String id) throws SQLException;
    public boolean seachValiedItemID(String id) throws SQLException;
    public boolean savePetItem(PetItemDTO petItemDTO) throws SQLException;
    public List<PetItemDTO> getAllPetItem() throws SQLException;
    public boolean deletePetItem(String id) throws SQLException;
    public boolean updatePetItem(PetItemDTO petItemDTO) throws SQLException;
}
