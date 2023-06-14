package lk.ijse.petcarecenter.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pet {
    private String petID;
    private  String name;
    private int age;
    private String type;
    private String bread;
    private String ownerID;
}
