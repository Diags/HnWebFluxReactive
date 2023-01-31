package hn.services.client.app.webfluxreactive.dao;

import hn.services.client.app.webfluxreactive.entities.Societe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SocieteRepo  extends ReactiveMongoRepository<Societe, String> {
}
