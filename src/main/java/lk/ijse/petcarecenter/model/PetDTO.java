package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PetDTO {
    private String petID;
    private  String name;
    private int age;
    private String type;
    private String bread;
    private String ownerID;


}
