package com.clothes.clothes.responses;

import java.sql.Date;

import com.clothes.clothes.entities.OcDetalle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcDetalleRelationsResponse {
    public OcDetalleRelationsResponse(OcDetalle ocDetalle) {
        this.setId(ocDetalle.getId());
        this.setStock(ocDetalle.getStock());
        this.setPrice(ocDetalle.getPrice());
        this.setTotalPrice(ocDetalle.getTotalPrice());
        this.setCreatedAt(ocDetalle.getCreatedAt());
        this.setUpdatedAt(ocDetalle.getUpdatedAt());
        this.setUser(new UserResponse(ocDetalle.getUser()));
        this.setStockId(new StockResponse(ocDetalle.getStockId()));
        this.setCltoheResponse(new ClotheResponse(ocDetalle.getClothe()));
        this.setOc(new OcResponse(ocDetalle.getOc()));
    }
    
    private Long id;
    private Short stock;
    private float price;
    private float totalPrice;
    private Date createdAt;
    private Date updatedAt;
    private UserResponse user;
    private StockResponse stockId;
    private ClotheResponse cltoheResponse;
    private OcResponse oc;
}
