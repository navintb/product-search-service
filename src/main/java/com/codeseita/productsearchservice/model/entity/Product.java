package com.codeseita.productsearchservice.model.entity;

import com.codeseita.productsearchservice.type.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Brand brand;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

}
