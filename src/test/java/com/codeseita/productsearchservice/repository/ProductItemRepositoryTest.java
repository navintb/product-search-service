package com.codeseita.productsearchservice.repository;

import com.codeseita.productsearchservice.model.entity.ProductItem;
import com.codeseita.productsearchservice.model.repository.ProductItemRepository;
import com.codeseita.productsearchservice.model.util.Filter;
import com.codeseita.productsearchservice.model.util.Search;
import com.codeseita.productsearchservice.type.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductItemRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Test
    public void findAll_ifBrandFilterApplied_thenReturnFilteredProductItems(){
        Search<ProductItem> search = new Search<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        search.addFilter(Filter.by("product.brand.id").with(list).on(Filter.Operation.IN));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertEquals(item.getProduct().getBrand().getId(), 1);
        }
    }

    @Test
    public void findAll_ifCategoryFilterApplied_thenReturnFilteredProductItems(){
        Search<ProductItem> search = new Search<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        search.addFilter(Filter.by("product.category.id").with(list).on(Filter.Operation.IN));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertEquals(item.getProduct().getCategory().getId(), 1);
        }
    }

    @Test
    public void findAll_ifMinPriceFilterApplied_thenReturnFilteredProductItems(){
        Search<ProductItem> search = new Search<>();
        search.addFilter(Filter.by("price").with(1000).on(Filter.Operation.GREATER_THAN_OR_EQUAL_TO));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertTrue(item.getPrice() >= 1000);
        }
    }

    @Test
    public void findAll_ifMaxPriceFilterApplied_thenReturnFilteredProductItems(){
        Search<ProductItem> search = new Search<>();
        search.addFilter(Filter.by("price").with(1000).on(Filter.Operation.LESS_THAN_OR_EQUAL_TO));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertTrue(item.getPrice() <= 1000);
        }
    }

    @Test
    public void findAll_ifColorFilterApplied_thenReturnFilteredProductItems(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Search<ProductItem> search = new Search<>();
        search.addFilter(Filter.by("color.id").with(list).on(Filter.Operation.IN));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertEquals(item.getColor().getId(), 1);
        }
    }

    @Test
    public void findAll_ifSizeFilterApplied_thenReturnFilteredProductItems(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Search<ProductItem> search = new Search<>();
        search.addFilter(Filter.by("size.id").with(list).on(Filter.Operation.IN));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertEquals(item.getSize().getId(), 1);
        }
    }

    @Test
    public void findAll_ifMinStockFilterApplied_thenReturnFilteredProductItems(){
        Search<ProductItem> search = new Search<>();
        search.addFilter(Filter.by("count").with(10).on(Filter.Operation.GREATER_THAN_OR_EQUAL_TO));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertTrue(item.getCount() >= 10);
        }
    }

    @Test
    public void findAll_ifMaxStockFilterApplied_thenReturnFilteredProductItems(){
        Search<ProductItem> search = new Search<>();
        search.addFilter(Filter.by("count").with(10).on(Filter.Operation.LESS_THAN_OR_EQUAL_TO));
        Page<ProductItem> page = productItemRepository.findAll(search.build(), PageRequest.of(0, 10));
        Assert.assertTrue(page.hasContent());
        for(ProductItem item: page.getContent()) {
            Assert.assertTrue(item.getCount() <= 10);
        }
    }

    @Test
    public void findByStatusAndSku_ifExists_returnProductItem(){
        Optional<ProductItem> productItem = productItemRepository.findByStatusAndSku(Status.ACTIVE, "SH0000001");
        Assert.assertTrue(productItem.isPresent());
        Assert.assertEquals(productItem.get().getSku(), "SH0000001");
    }

}
