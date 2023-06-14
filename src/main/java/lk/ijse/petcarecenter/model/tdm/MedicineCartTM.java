package lk.ijse.petcarecenter.model.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MedicineCartTM {
    private  String medicineID;
    private String name;
    private int medicineQuantity;
    private  double price;
    private double medicineTotal;
}
