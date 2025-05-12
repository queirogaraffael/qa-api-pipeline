package com.example.cinema.api.resources;

import com.example.cinema.api.dtos.purchase.PurchaseRequestDTO;
import com.example.cinema.api.dtos.purchase.PurchaseResponseDTO;
import com.example.cinema.api.services.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Purchases")
@RestController
@RequestMapping("/api/purchases")
public class PurchaseResource {

    private final PurchaseService purchaseService;

    public PurchaseResource(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Operation(summary = "Criar nova compra", description = "Cria uma nova compra")
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "500", description = "Internal server error | Business validation error")
    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> createPurchase(@RequestBody PurchaseRequestDTO purchaseRequest) {
        PurchaseResponseDTO purchase = purchaseService.createPurchase(purchaseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchase);
    }


}
