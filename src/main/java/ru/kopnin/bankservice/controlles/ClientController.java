package ru.kopnin.bankservice.controlles;

import ru.kopnin.bankservice.DTO.ClientDTO;
import ru.kopnin.bankservice.mappers.ClientMapper;
import ru.kopnin.bankservice.models.postgres.Client;
import ru.kopnin.bankservice.service.ClientService;
import ru.kopnin.bankservice.util.ConvertModelAndDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public List<Client> showAllClient(){
        return clientService.findAllClient();
    }

    //Добавляем нового клиента в БД
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addClient(@RequestBody ClientDTO clientDTO) {
        clientService.addClient(clientService.convertToClient(clientDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
