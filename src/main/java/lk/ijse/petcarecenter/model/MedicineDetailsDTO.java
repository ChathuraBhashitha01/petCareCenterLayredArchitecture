package lk.ijse.petcarecenter.model;

import lombok.*;

@Data
@AllArgsConstructor
public class MedicineDetailsDTO {
    private String petID;
    private String paymentID;
    private String medicineID;
    private String date;
}
