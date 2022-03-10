package com.kit.product_delivery.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany
    @JoinTable(
            name = "supply_price",
            joinColumns = @JoinColumn(name = "supply_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id"))
    List<Price> prices;
}
