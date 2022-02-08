package com.chrisreal.swiftexample.repository;

import com.chrisreal.swiftexample.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SwiftRepository extends PagingAndSortingRepository<Client, Long> {
    Client findByContactEmail(String email);
    Client findByClientId(String clientId);
    Client findBySecret(String secret);
}
