package com.chrisreal.swiftexample.controller;

import com.chrisreal.swiftexample.exceptions.BadRequestException;
import com.chrisreal.swiftexample.model.Client;
import com.chrisreal.swiftexample.service.SwiftService;
import com.chrisreal.swiftexample.utils.AppUtils;
import com.chrisreal.swiftexample.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class SwitftController {
    @Autowired
    private SwiftService service;

    @PostMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClientApplication(@RequestBody Client client) {
        if(client == null)
            throw new BadRequestException("400", "RequestBody is required");

        AppUtils.validateRequiredFields(client, ConstantUtils.CLIENT_APPLICATION_REQUIRED_FIELDS);

        if(service.checkClientRecordExist(client))
            throw new BadRequestException("400", "Record already exist for the provided client email/client ID");

        return service.save(client);
    }

    @PatchMapping(value = "/client/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Client changeStatus(@RequestBody Client clientDetails, @PathVariable Long id) {
        Client client = service.getClientById(id);

        if(client == null)
            throw new BadRequestException("400", "Client info does not exist");

        if(clientDetails.getStatus() == null)
            throw new BadRequestException("400", "status is required");

        client.setStatus(clientDetails.getStatus());

        return service.updateClient(client);
    }

    @GetMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Client> getAllClients(@RequestParam(value = "pagenumber", defaultValue = "0") int pageNumber,
                                      @RequestParam(value = "pagesize", defaultValue = "20") int pageSize) {
        return service.getAllClients(pageNumber, pageSize);
    }

}
