package com.dev.server.repositories.ekyc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "ekyc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class EkycEntity {
    @Id
    @Column(name = "ekyc_id")
    String idEkyc;

    @Column(name = "user_id")
    String idUser;

    @Column(name = "ekyc_image_url")
    String ekycImageUrl;

    @Column(name = "ekyc_verified")
    Boolean ekycVerified;

    @Column(name = "ekyc_verified_at")
    LocalDateTime ekycVerifiedAt;

}
