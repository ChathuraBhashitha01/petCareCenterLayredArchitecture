package lk.ijse.petcarecenter.model;

import lombok.*;

@Data
@AllArgsConstructor
public class ServiceDetailsDTO {
    private String petID;
    private String paymentID;
    private String serviceID;
    private String date;
}
