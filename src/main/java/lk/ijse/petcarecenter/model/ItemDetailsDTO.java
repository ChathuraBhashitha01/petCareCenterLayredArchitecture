package lk.ijse.petcarecenter.model;

import lombok.*;

@Data
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ItemDetailsDTO {
    private String petID;
    private String paymentID;
    private String itemCode;
    private String date;

}
