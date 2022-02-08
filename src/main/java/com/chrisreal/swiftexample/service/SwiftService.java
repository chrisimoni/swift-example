package com.chrisreal.swiftexample.service;

import com.chrisreal.swiftexample.model.Client;
import com.chrisreal.swiftexample.repository.SwiftRepository;
import com.chrisreal.swiftexample.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SwiftService {
    @Autowired
    private SwiftRepository repository;

    public Client save(Client client) {
        String clientId = client.getClientName() +"_"+client.getBankCode();
        String secret = Utility.generatedRandomCharacters(6);

        while(repository.findBySecret(secret) != null) {
            secret = Utility.generatedRandomCharacters(6);
        }

        client.setClientId(clientId);
        client.setSecret(secret);
        client.setStatus(true);
        client.setCreatedAt(new Date());

        return repository.save(client);

    }

    public Boolean checkClientRecordExist(Client client) {
        if (repository.findByContactEmail(client.getContactEmail()) != null
                || repository.findByClientId(client.getClientId()) != null)
            return true;

        return false;
    }

    public Client getClientById(Long id) {
        Optional<Client> client = repository.findById(id);
        if(client.isPresent()) {
            return client.get();
        }

        return null;
    }

    public Client updateClient(Client client) {
        return repository.save(client);
    }

    public List<Client> getAllClients(int page, int limit) {
        if(page > 0) page = page - 1;
        Page<Client> clients = repository.findAll(PageRequest.of(page, limit));

        if(clients.hasContent()) {
            return clients.getContent();
        } else {
            return new ArrayList<Client>();
        }
    }
}
