package com.codeseita.productsearchservice.model.entity;

import com.codeseita.productsearchservice.type.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class ProductItem {

    @Id
    @GeneratedValue
    private int id;
    private String sku;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Size size;
    @ManyToOne
    private Color color;
    private Double price;
    private Integer count;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

}
