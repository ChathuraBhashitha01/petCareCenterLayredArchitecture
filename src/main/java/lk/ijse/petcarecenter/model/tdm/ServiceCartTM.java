package lk.ijse.petcarecenter.model.tdm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ServiceCartTM {
    private String serviceID;
    private String serviceDes;
    private double serviceCharge;
    private double total;
}
