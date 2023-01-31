package hn.services.client.app.webfluxreactive.dao;

import hn.services.client.app.webfluxreactive.entities.Societe;
import hn.services.client.app.webfluxreactive.entities.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepo extends ReactiveMongoRepository<Transaction,String> {
    Flux<Transaction> findBySociete(Societe soc);
}
