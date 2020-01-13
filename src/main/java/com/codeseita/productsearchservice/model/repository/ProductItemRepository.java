package com.codeseita.productsearchservice.model.repository;

import com.codeseita.productsearchservice.model.entity.ProductItem;
import com.codeseita.productsearchservice.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer>, JpaSpecificationExecutor<ProductItem> {

    Optional<ProductItem> findByStatusAndSku(Status status, String sku);
}
