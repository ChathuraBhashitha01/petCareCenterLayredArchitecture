package lk.ijse.petcarecenter.model.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SchduleTM {
    private String schduleID;
    private String inTime;
    private String outTime;
    private String date;
    private String doctorID;
}
