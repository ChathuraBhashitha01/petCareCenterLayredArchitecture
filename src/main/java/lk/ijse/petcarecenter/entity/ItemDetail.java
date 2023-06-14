package lk.ijse.petcarecenter.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemDetail {
    private String petID;
    private String paymentID;
    private String itemCode;
    private  String date;
}
