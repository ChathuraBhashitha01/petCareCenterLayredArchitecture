package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OwnerDTO {
    private String ownerID;
    private String name;
    private int phoneNumber;
}
