package com.example.shoppingcart.cart.model.entity;

import lombok.Data;

import java.util.Date;

import javax.persistence.*;


@Entity
@Data
@Table(name="CUSTOMER")
public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long customerId;
	
	@Column
    private String firstName;
	
	@Column
    private String lastName;
	
	@Column
    private String email;
	
	@Column
    private String phoneNumber;
	
	@Column
    private String address;
	
	@Column
    private String postalCode;
	
	@Column
    private String status;
	
	@Column
    private String gender;
	
	@Column
    private Date dateOfBirth;
	
	@Transient
	@OneToOne
    @JoinColumn(name = "customer_id")
    private OrderEntity customerOrder;

}
