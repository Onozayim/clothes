package com.clothes.clothes.entities;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oc_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EnableJpaAuditing
public class OcDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;   

    @Column(nullable = false)
    private Short stock;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false, name = "total_price")
    private float totalPrice;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stockId;

    @ManyToOne
    @JoinColumn(name = "clothe_id", referencedColumnName = "id")
    private Clothe clothe;

    @ManyToOne
    @JoinColumn(name = "oc_id", referencedColumnName = "id")
    private Oc oc;
}
