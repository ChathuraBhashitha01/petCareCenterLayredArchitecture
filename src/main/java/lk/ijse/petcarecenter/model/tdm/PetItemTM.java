package lk.ijse.petcarecenter.model.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PetItemTM  {
    private String itemCode;
    private  String name;
    private double price;
    private int quantity;

}
