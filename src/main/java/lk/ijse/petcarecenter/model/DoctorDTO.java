package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DoctorDTO {
    private String doctorID;
    private String name;
    private int phoneNumber;
}
