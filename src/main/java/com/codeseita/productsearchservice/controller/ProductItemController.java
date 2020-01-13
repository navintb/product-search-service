package com.codeseita.productsearchservice.controller;

import com.codeseita.productsearchservice.model.entity.ProductItem;
import com.codeseita.productsearchservice.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/product-items")
public class ProductItemController {

    @Autowired
    private ProductItemService productItemService;

    @GetMapping
    public Page<ProductItem> list(@RequestParam(value = "brandId", required = false) Collection<Integer> brandIds,
                                  @RequestParam(value = "categoryId", required = false) Collection<Integer> categoryIds,
                                  @RequestParam(value = "minPrice", required = false) Double minPrice,
                                  @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                  @RequestParam(value = "colorId", required = false) Collection<Integer> colorIds,
                                  @RequestParam(value = "sizeId", required = false) Collection<Integer> sizeIds,
                                  @RequestParam(value = "minStock", required = false) Integer minStock,
                                  @RequestParam(value = "maxStock", required = false) Integer maxStock,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "limit", required = false) Integer limit
                                  ) {
        return this.productItemService.list(brandIds,categoryIds, minPrice, maxPrice, colorIds, sizeIds, minStock, maxStock, page, limit);
    }

    @GetMapping("/{sku}")
    public ProductItem get(@PathVariable("sku") String sku) {
        return this.productItemService.get(sku);
    }

}
