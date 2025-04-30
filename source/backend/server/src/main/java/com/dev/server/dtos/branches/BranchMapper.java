package com.dev.server.dtos.branches;

import com.dev.server.repositories.branches.BranchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchEntity toEntity(Branch branch);

    Branch toDto(BranchEntity branchEntity);
}
