package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MedicineDTO {
    private String medicineID;
    private  String name;
    private int quantity;
    private double price;

}
