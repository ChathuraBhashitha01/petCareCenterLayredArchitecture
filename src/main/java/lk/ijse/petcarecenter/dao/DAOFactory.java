package lk.ijse.petcarecenter.dao;

import lk.ijse.petcarecenter.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDAOFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        APPOINTMENT,DOCTOR,MEDICINE,MEDICINE_DETAILS,OWNER,PAYMENT,PET_ITEM,PET_ITEM_DETAILS,PET,SERVICR,SERVICE_DETAILS,SCHDULE
    }
    public <T extends SuperDAO> T getDAO(DAOFactory.DAOTypes daoTypes) {
        switch (daoTypes) {
            case APPOINTMENT:
                return (T) new AppointmentDAOImpl();
            case DOCTOR:
                return (T) new DoctorDAOImpl();
            case MEDICINE:
                return (T) new MedicineDAOImpl();
            case MEDICINE_DETAILS:
                return (T) new MedicineDetailsDAOImpl();
            case OWNER:
                return (T) new OwnerDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case PET_ITEM:
                return (T) new PetItemDAOImpl();
            case PET:
                return (T) new PetDAOImpl();
            case PET_ITEM_DETAILS:
                return (T) new ItemDetailsDAOImpl();
            case SERVICR:
                return (T) new ServiceDAOImpl();
            case SERVICE_DETAILS:
                return (T) new ServiceDetailsDAOImpl();
            case SCHDULE:
                return (T) new SchduleDAOImpl();
            default:
                return null;
        }
    }
}
