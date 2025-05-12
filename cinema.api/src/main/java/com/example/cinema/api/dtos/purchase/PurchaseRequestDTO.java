package com.example.cinema.api.dtos.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDTO {

    /*
    private LocalDateTime purchaseDate;
    private BigDecimal totalPrice;

    private Long userId;
*/

    private Long ticketId;
    private Long movieSessionId;

}
