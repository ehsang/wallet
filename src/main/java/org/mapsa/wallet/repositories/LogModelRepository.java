package org.mapsa.wallet.repositories;

import org.mapsa.wallet.configuration.aspects.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogModelRepository extends MongoRepository<LogModel, String> {

}
