package lk.ijse.petcarecenter.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Service {
    private String serviceID;
    private String name;
    private int cost;
}
