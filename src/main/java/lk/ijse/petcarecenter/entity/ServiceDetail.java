package lk.ijse.petcarecenter.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ServiceDetail {
    private String petID;
    private String paymentID;
    private String serviceID;
    private  String date;
}
