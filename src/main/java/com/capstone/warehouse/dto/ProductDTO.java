package com.capstone.warehouse.dto;

import com.capstone.warehouse.entity.Product;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private String sku;
    private BigDecimal price;
    private String description;
    private String categoryName;
    private String supplierName;

    public static ProductDTO from(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSku(product.getSku());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategoryName(product.getCategory() != null ? product.getCategory().getName() : null);
        dto.setSupplierName(product.getSupplier() != null ? product.getSupplier().getName() : null);
        return dto;
    }
}