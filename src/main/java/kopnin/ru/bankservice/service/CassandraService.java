package kopnin.ru.bankservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;

@Service
public class CassandraService {

    private final CassandraRepository cassandraRepository;

@Autowired
    public CassandraService(CassandraRepository cassandraRepository) {
        this.cassandraRepository = cassandraRepository;
    }
}
