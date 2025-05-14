package com.dev.server.dtos.otp;

import com.dev.server.repositories.otp.OtpEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OtpMapper {
    OtpEntity toEntity(Otp otp);

    Otp toDto(OtpEntity otpEntity);

//    @Mapping(target = "idOtp", ignore = true)
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    OtpEntity updateExist(Otp otp, OtpEntity entity);
}
