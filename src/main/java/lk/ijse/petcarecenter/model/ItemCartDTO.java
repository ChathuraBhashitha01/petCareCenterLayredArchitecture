package lk.ijse.petcarecenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCartDTO {
    private String itemCode;
    private int itemQuantity;

}
