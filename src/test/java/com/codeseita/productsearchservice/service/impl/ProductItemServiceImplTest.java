package com.codeseita.productsearchservice.service.impl;

import com.codeseita.productsearchservice.exception.NotFoundException;
import com.codeseita.productsearchservice.model.entity.*;
import com.codeseita.productsearchservice.model.repository.ProductItemRepository;
import com.codeseita.productsearchservice.service.ProductItemService;
import com.codeseita.productsearchservice.type.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ProductItemServiceImplTest {

    @TestConfiguration
    static class contextConfig{

        @Bean
        public ProductItemService productItemService(){
            return new ProductItemServiceImpl();
        }
    }

    @Autowired
    private ProductItemService productItemService;

    @MockBean
    private ProductItemRepository productItemRepository;

    @Test
    public void list_ifProductItemExists_returnProductItem(){
        ProductItem item = getMockProductItem();
        List<ProductItem> items = new ArrayList<>();
        items.add(item);
        Mockito.when(this.productItemRepository.findAll(ArgumentMatchers.any(Specification.class), (Pageable) ArgumentMatchers.any())).thenReturn(new PageImpl<>(items));
        Page<ProductItem> page = this.productItemService.list(null,null,null,null,null,null,null,null,null,null);
        Assert.assertEquals(page.getTotalElements(), 1);
        Assert.assertTrue(new ReflectionEquals(page.getContent().get(0)).matches(item));
    }

    @Test
    public void get_ifProductWithSkuExists_returnProductItem(){
        ProductItem item = getMockProductItem();
        Mockito.when(this.productItemRepository.findByStatusAndSku(Status.ACTIVE,"SH0000001")).thenReturn(Optional.of(item));
        ProductItem result = this.productItemService.get("SH0000001");
        Assert.assertTrue(new ReflectionEquals(result).matches(item));
    }

    @Test(expected = NotFoundException.class)
    public void get_ifProductWithSkuNotFound_thenThrowsNotFoundExcpetion(){
        Mockito.when(this.productItemRepository.findByStatusAndSku(Status.ACTIVE,"SH0000001")).thenReturn(Optional.empty());
        this.productItemService.get("SH0000001");
    }

    private Category getMockCategory() {
        Category category = new Category();
        category.setName("SHIRT");
        category.setId(1);
        category.setStatus(Status.ACTIVE);
        return category;
    }

    private Color getMockColor() {
        Color color = new Color();
        color.setId(1);
        color.setName("BLUE");
        color.setStatus(Status.ACTIVE);
        return color;
    }

    private Size getMockSize() {
        Size size = new Size();
        size.setId(1);
        size.setName("XL");
        return size;
    }


    private Brand getMockBrand() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Test-brand");
        brand.setStatus(Status.ACTIVE);
        return brand;
    }

    private Product getMockProduct() {
        Product product = new Product();
        product.setId(1);
        product.setBrand(getMockBrand());
        product.setCategory(getMockCategory());
        product.setName("test-shirt");
        product.setStatus(Status.ACTIVE);
        return product;
    }

    private ProductItem getMockProductItem() {
        ProductItem item = new ProductItem();
        item.setId(1);
        item.setPrice(1000d);
        item.setSku("SH0000001");
        item.setStatus(Status.ACTIVE);
        item.setColor(getMockColor());
        item.setProduct(getMockProduct());
        item.setSize(getMockSize());
        item.setCount(10);
        return item;
    }
}


