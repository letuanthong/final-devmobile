package com.dev.server.repositories.branches;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class BranchEntity {
    @Id
    @Column (name = "branch_id")
    String idBranch;

    @Column (name = "branch_name")
    String branchName;

    @Column (name = "branch_address")
    String branchAddress;

    @Column (name = "branch_latitude")
    Double branchLatitude;

    @Column (name = "branch_longitude")
    Double branchLongitude;
}
