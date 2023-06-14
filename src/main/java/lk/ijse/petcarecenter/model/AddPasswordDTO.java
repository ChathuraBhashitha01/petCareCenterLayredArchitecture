package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddPasswordDTO {
    private String user;
    private String userName;
    private String password;
}
