package lk.ijse.petcarecenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicineCartDTO {
    private  String medicineID;
    private int medicineQuantity;
}
