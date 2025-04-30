package com.dev.server.dtos.ekyc;

import com.dev.server.repositories.ekyc.EkycEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EkycMapper {
    EkycEntity toEntity(Ekyc ekyc);

    Ekyc toDto(EkycEntity ekycEntity);
}
