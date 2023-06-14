package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PetItemDTO {
    private String itemCode;
    private  String name;
    private double price;
    private int quantity;

}
