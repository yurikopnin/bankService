package kopnin.ru.bankservice.controlles;

import kopnin.ru.bankservice.DTO.ClientDTO;
import kopnin.ru.bankservice.models.postgres.Client;
import kopnin.ru.bankservice.service.ClientService;
import kopnin.ru.bankservice.util.ConvertModelAndDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;

    }

    //Добавляем нового клиента в БД
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addClient(@RequestBody ClientDTO clientDTO) {
        Client client = ConvertModelAndDTO.convertToClient(clientDTO);
        clientService.addClient(client);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
