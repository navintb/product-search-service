package com.codeseita.productsearchservice.service;

import com.codeseita.productsearchservice.model.entity.ProductItem;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface ProductItemService {

    Page<ProductItem> list(Collection<Integer> brandIds,
                           Collection<Integer> categoryIds,
                           Double minPrice,
                           Double maxPrice,
                           Collection<Integer> colorIds,
                           Collection<Integer> sizeIds,
                           Integer minStock,
                           Integer maxStock,
                           Integer page,
                           Integer limit);

    ProductItem get(String sku);
}
