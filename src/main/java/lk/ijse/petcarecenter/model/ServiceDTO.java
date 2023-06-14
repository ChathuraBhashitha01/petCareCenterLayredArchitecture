package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ServiceDTO {
    private String serviceID;
    private String name;
    private int cost;
}
