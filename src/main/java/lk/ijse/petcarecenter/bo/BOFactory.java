package lk.ijse.petcarecenter.bo;

import lk.ijse.petcarecenter.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        APPOINTMENT,DOCTOR,MEDICINE,PAYMENT,PET_ITEM,PET_MANAGEMENT,PET_REGISTRATION,SCHDULE
    }
    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case APPOINTMENT:
                return (T) new AppointmentBOImpl();
            case DOCTOR:
                return (T) new DoctorBOImpl();
            case MEDICINE:
                return (T) new MedicineBOImpl();
            case PAYMENT:
                return (T) new PaymentBOImpl();
            case PET_ITEM:
                return (T) new PetItemBOImpl();
            case PET_MANAGEMENT:
                return (T) new PetManagementBOImpl();
            case PET_REGISTRATION:
                return (T) new PetRegistrationBOImpl();
            case SCHDULE:
                return (T) new SchduleBOImpl();
            default:
                return null;
        }
    }

}
