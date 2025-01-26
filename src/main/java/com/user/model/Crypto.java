package com.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cryptos")
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String coin;
    private String wallet;
    private String network;
}
