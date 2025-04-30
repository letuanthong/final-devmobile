package com.dev.server.dtos.bills;

import com.dev.server.repositories.bills.BillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toDto(BillEntity entity);

    BillEntity toModel(Bill dto);
}
