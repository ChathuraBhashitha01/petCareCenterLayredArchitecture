package lk.ijse.petcarecenter.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MedicineDetail {
    private String petID;
    private String paymentID;
    private String medicineID;
    private  String date;
}
