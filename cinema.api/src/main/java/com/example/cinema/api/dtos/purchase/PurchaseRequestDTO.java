package com.example.cinema.api.dtos.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDTO {

    private LocalDateTime purchaseDate;
    private BigDecimal totalPrice;

    private Long userId;
    private Long ticketId;
    private Long movieSessionId;

}
