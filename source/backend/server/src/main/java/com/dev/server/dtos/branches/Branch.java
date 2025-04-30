package com.dev.server.dtos.branches;

public record Branch(
        String idBranch,
        String branchName,
        String branchAddress,
        Double branchLatitude,
        Double branchLongitude
) {
}
