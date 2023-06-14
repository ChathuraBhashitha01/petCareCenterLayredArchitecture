package lk.ijse.petcarecenter.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
   private String petID;
   private String paymentId;
   private String date;
   private double itemTotal;
   private double medicineTotal;
   private double serviceTotal;
   private double allTotal;
   private List<ItemCartDTO> itemDetailsDTOList;
   private List<MedicineCartDTO> medicineDetailsDTOList;
   private List<ServiceCartDTO> serviceDetailsDTOList;
}
