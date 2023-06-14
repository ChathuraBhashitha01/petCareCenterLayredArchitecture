package lk.ijse.petcarecenter.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PetItem {
    private String itemCode;
    private  String name;
    private double price;
    private int description;

}
