package lk.ijse.petcarecenter.bo.custom.impl;

import lk.ijse.petcarecenter.bo.custom.PaymentBO;
import lk.ijse.petcarecenter.dao.DAOFactory;
import lk.ijse.petcarecenter.dao.custom.*;
import lk.ijse.petcarecenter.db.DBConnection;
import lk.ijse.petcarecenter.entity.*;
import lk.ijse.petcarecenter.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    ItemDetailsDAO itemDetailsDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET_ITEM_DETAILS);
    MedicineDetailsDAO medicineDetailsDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEDICINE_DETAILS);
    ServiceDetailsDAO serviceDetailsDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SERVICE_DETAILS);
    PetItemDAO petItemDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET_ITEM);
    MedicineDAO medicineDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);
    PetDAO petDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET);
    OwnerDAO ownerDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.OWNER);
    ServiceDAO serviceDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SERVICR);

    public  boolean payment(PaymentDTO dto) throws SQLException {
        Connection con=null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            //PaymentDTO paymentDTO =new PaymentDTO(paymentId, petID, itemTotal,serviceTotal,medicineTotal,allTotal);
            Payment payment=new Payment(dto.getPaymentId(),dto.getPetID(),dto.getItemTotal(),dto.getServiceTotal(),dto.getMedicineTotal(),dto.getAllTotal());
            boolean isSaved = paymentDAO.save(payment);
            if(isSaved) {
                String itemCode=null;
                for(ItemCartDTO itemDetailsDTO : dto.getItemDetailsDTOList()) {
                    itemCode= itemDetailsDTO.getItemCode();
                }
                //ItemDetailsDTO itemDetailsDTO=new ItemDetailsDTO(petID,paymentId, itemCode,date);
                ItemDetail itemDetail=new ItemDetail(dto.getPetID(),dto.getPaymentId(),itemCode,dto.getDate());
                boolean isItemSaved = itemDetailsDAO.save(itemDetail);
                if(isItemSaved) {
                    String medicineID=null;
                    for(MedicineCartDTO medicineDetailsDTO :dto.getMedicineDetailsDTOList()) {
                        medicineID= medicineDetailsDTO.getMedicineID();
                    }
                   // MedicineDetailsDTO medicineDetailsDTO=new MedicineDetailsDTO(petID,paymentId,medicineID,date);
                    MedicineDetail medicineDetail=new MedicineDetail(dto.getPetID(),dto.getPaymentId(),medicineID,dto.getDate());
                    boolean isMedicineSaved = medicineDetailsDAO.save(medicineDetail);
                    if(isMedicineSaved) {
                        String serviceID=null;
                        for(ServiceCartDTO serviceDetailsDTO : dto.getServiceDetailsDTOList()) {
                            serviceID= serviceDetailsDTO.getServiceID();
                        }
                        //ServiceDetailsDTO serviceDetailsDTO=new ServiceDetailsDTO(petID,paymentId,serviceID,date);
                        ServiceDetail serviceDetail=new ServiceDetail(dto.getPetID(),dto.getPaymentId(),serviceID,dto.getDate());
                        boolean isServiceSaved= serviceDetailsDAO.save(serviceDetail);
                        if (isServiceSaved) {
                            boolean itemQtyUpdated= petItemDAO.itemQuantityUpdate(dto.getItemDetailsDTOList());
                            if(itemQtyUpdated) {
                                boolean medicineQtyUpdated= medicineDAO.medicineQuantityUpdate(dto.getMedicineDetailsDTOList());
                                if(medicineQtyUpdated) {
                                    con.commit();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("Payment done");
            con.setAutoCommit(true);
        }
    }

    public String getNextOrderId() throws SQLException {
        return paymentDAO.getNextId();
    }

    public boolean seachValiedPetID(String petID) throws SQLException {
        return petDAO.seachID(petID);
    }

    public PetDTO searchPetDetails(String petID) throws SQLException {
        Pet pet= petDAO.search(petID);
        return new PetDTO(pet.getPetID(),pet.getName(),pet.getAge(),pet.getType(),pet.getBread(),pet.getOwnerID());
    }

    public OwnerDTO searchOwnerDetails(String ownerId) throws SQLException {
        Owner owner= ownerDAO.search(ownerId);
        return new OwnerDTO(owner.getOwnerID(),owner.getName(),owner.getPhoneNumber());
    }

    public List<String> loadItemCodes() throws SQLException {
        return petItemDAO.loadCodes();
    }

    public List<String> loadMedicineCodes() throws SQLException {
        return medicineDAO.loadCodes();
    }

    public PetItemDTO searchItemCodes(String code) throws SQLException {
        PetItem item= petItemDAO.search(code);
        return new PetItemDTO(item.getItemCode(),item.getName(),item.getPrice(),item.getDescription());
    }

    public MedicineDTO searchMedicineId(String id) throws SQLException {
        Medicine medicine= medicineDAO.search(id);
        return new MedicineDTO(medicine.getMedicineID(),medicine.getName(),medicine.getQuantity(),medicine.getPrice());
    }

    public List<String> loadServiceID() throws SQLException {
        return serviceDAO.loadCodes();
    }

    public ServiceDTO searchServiceIDs(String id) throws SQLException {
        Service service=serviceDAO.search(id);
        return new ServiceDTO(service.getServiceID(),service.getName(),service.getCost());
    }
}
