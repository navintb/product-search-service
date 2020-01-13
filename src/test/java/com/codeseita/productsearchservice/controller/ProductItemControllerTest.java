package com.codeseita.productsearchservice.controller;

import com.codeseita.productsearchservice.model.entity.*;
import com.codeseita.productsearchservice.service.ProductItemService;
import com.codeseita.productsearchservice.type.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductItemController.class)
public class ProductItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductItemService productItemService;

    @Test
    public void list_ifProductsExists_thenReturnProducts() throws Exception {
        ProductItem mockItem = getMockProductItem();
        List<ProductItem> items = new ArrayList<>();
        items.add(mockItem);
        Mockito.when(this.productItemService.list(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(),
                ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new PageImpl<>(items));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product-items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].id").value(mockItem.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].price").value(mockItem.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].count").value(mockItem.getCount()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].sku").value(mockItem.getSku()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].status").value(mockItem.getStatus().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].color.name").value(mockItem.getColor().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].size.name").value(mockItem.getSize().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].product.name").value(mockItem.getProduct().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].product.category.name").value(mockItem.getProduct().getCategory().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("content[0].product.brand.name").value(mockItem.getProduct().getBrand().getName()));
    }

    @Test
    public void get_ifProductItemExistsForASku_thenReturnProduct() throws Exception{
        ProductItem mockItem = getMockProductItem();
        Mockito.when(this.productItemService.get(ArgumentMatchers.any())).thenReturn(mockItem);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/product-items/SH0000001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(mockItem.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("price").value(mockItem.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("count").value(mockItem.getCount()))
                .andExpect(MockMvcResultMatchers.jsonPath("sku").value(mockItem.getSku()))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(mockItem.getStatus().name()))
                .andExpect(MockMvcResultMatchers.jsonPath("color.name").value(mockItem.getColor().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("size.name").value(mockItem.getSize().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("product.name").value(mockItem.getProduct().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("product.category.name").value(mockItem.getProduct().getCategory().getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("product.brand.name").value(mockItem.getProduct().getBrand().getName()));
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
