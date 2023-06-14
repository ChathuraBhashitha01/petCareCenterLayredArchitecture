package lk.ijse.petcarecenter.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    private String appointmentID;
    private String petID;
    private String schduleID;
    private String date;
    private String serviceID;

    public AppointmentDTO(String appointmentID) {
        this.appointmentID=appointmentID;
    }
}
