package com.example.shoppingcart.payment.service.mapper;

import com.example.shoppingcart.payment.model.dto.PaymentDto;
import com.example.shoppingcart.payment.model.entity.PaymentEntity;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T18:12:50+0800",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 21.0.4 (Eclipse Adoptium)"
)
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentEntity toEntity(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setId( paymentDto.getId() );
        paymentEntity.setOrderDate( paymentDto.getOrderDate() );
        paymentEntity.setOrderId( paymentDto.getOrderId() );
        paymentEntity.setTotalPrice( paymentDto.getTotalPrice() );

        return paymentEntity;
    }

    @Override
    public PaymentDto toDto(PaymentEntity paymentEntity) {
        if ( paymentEntity == null ) {
            return null;
        }

        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setId( paymentEntity.getId() );
        paymentDto.setOrderDate( paymentEntity.getOrderDate() );
        paymentDto.setOrderId( paymentEntity.getOrderId() );
        paymentDto.setTotalPrice( paymentEntity.getTotalPrice() );

        return paymentDto;
    }
}
