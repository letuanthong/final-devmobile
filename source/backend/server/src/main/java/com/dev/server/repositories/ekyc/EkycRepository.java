package com.dev.server.repositories.ekyc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EkycRepository extends JpaRepository<EkycEntity, String> {

}
