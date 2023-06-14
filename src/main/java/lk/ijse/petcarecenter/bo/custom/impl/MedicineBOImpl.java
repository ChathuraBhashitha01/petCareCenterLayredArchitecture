package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.MedicineBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.MedicineDAO;
import lk.ijse.petcarecenter.entity.Medicine;
import lk.ijse.petcarecenter.model.MedicineDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineBOImpl implements MedicineBO {
    MedicineDAO medicineDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);

    public MedicineDTO searchMedicineId(String id) throws SQLException {
        Medicine medicine= medicineDAO.search(id);
        return new MedicineDTO(medicine.getMedicineID(),medicine.getName(),medicine.getQuantity(),medicine.getPrice());
    }

    public String getNextOrderId() throws SQLException {
        return medicineDAO.getNextId();
    }

    public boolean seachValiedID(String id) throws SQLException {
        return medicineDAO.seachID(id);
    }

    public boolean saveMedicine(MedicineDTO medicineDTO) throws SQLException {
        return medicineDAO.save(new Medicine(medicineDTO.getMedicineID(),medicineDTO.getName(),medicineDTO.getQuantity(),medicineDTO.getPrice()));
    }

    public List<MedicineDTO> getAllMedicine() throws SQLException {
        List<Medicine> all=medicineDAO.getAll();
        List<MedicineDTO> list=new ArrayList<>();
        for (Medicine d:all) {
            list.add(new MedicineDTO(d.getMedicineID(),d.getName(),d.getQuantity(),d.getPrice()));
        }
        return list;
    }

    public boolean updateMedicine(MedicineDTO medicineDTO) throws SQLException {
        return medicineDAO.update(new Medicine(medicineDTO.getMedicineID(),medicineDTO.getName(),medicineDTO.getQuantity(),medicineDTO.getPrice()));
    }

    public boolean delectMedicine(String id) throws SQLException {
        return medicineDAO.delete(id);
    }
}
