package com.codeseita.productsearchservice.service.impl;

import com.codeseita.productsearchservice.exception.NotFoundException;
import com.codeseita.productsearchservice.model.entity.ProductItem;
import com.codeseita.productsearchservice.model.repository.ProductItemRepository;
import com.codeseita.productsearchservice.model.util.Filter;
import com.codeseita.productsearchservice.model.util.Search;
import com.codeseita.productsearchservice.service.ProductItemService;
import com.codeseita.productsearchservice.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Service
public class ProductItemServiceImpl implements ProductItemService {

    @Autowired
    private ProductItemRepository productItemRepository;

    @Override
    public Page<ProductItem> list(Collection<Integer> brandIds,
                                  Collection<Integer> categoryIds,
                                  Double minPrice,
                                  Double maxPrice,
                                  Collection<Integer> colorIds,
                                  Collection<Integer> sizeIds,
                                  Integer minStock,
                                  Integer maxStock,
                                  Integer page,
                                  Integer limit) {
        Search<ProductItem> search = new Search<>();
        search.addFilter(Filter.by("status").with(Status.ACTIVE).on(Filter.Operation.EQUALS));
        if (!CollectionUtils.isEmpty(brandIds)) {
            search.addFilter(Filter.by("product.brand.id").with(brandIds).on(Filter.Operation.IN));
        }
        if (!CollectionUtils.isEmpty(categoryIds)) {
            search.addFilter(Filter.by("product.category.id").with(categoryIds).on(Filter.Operation.IN));
        }
        if (minPrice != null) {
            search.addFilter(Filter.by("price").with(minPrice).on(Filter.Operation.GREATER_THAN_OR_EQUAL_TO));
        }
        if (maxPrice != null) {
            search.addFilter(Filter.by("price").with(maxPrice).on(Filter.Operation.LESS_THAN_OR_EQUAL_TO));
        }
        if (!CollectionUtils.isEmpty(colorIds)) {
            search.addFilter(Filter.by("color.id").with(colorIds).on(Filter.Operation.IN));
        }
        if (!CollectionUtils.isEmpty(sizeIds)) {
            search.addFilter(Filter.by("size.id").with(sizeIds).on(Filter.Operation.IN));
        }
        if (minStock != null) {
            search.addFilter(Filter.by("count").with(minStock).on(Filter.Operation.GREATER_THAN_OR_EQUAL_TO));
        }
        if (maxStock != null) {
            search.addFilter(Filter.by("count").with(maxStock).on(Filter.Operation.LESS_THAN_OR_EQUAL_TO));
        }
        page = (page == null || page < 0) ? 0 : page;
        limit = (limit == null || limit < 0) ? 100 : limit;
        return this.productItemRepository.findAll(search.build(), PageRequest.of(page, limit));
    }

    @Override
    public ProductItem get(String sku) {
        return this.productItemRepository.findByStatusAndSku(Status.ACTIVE, sku)
                .orElseThrow(NotFoundException::new);
    }

}
