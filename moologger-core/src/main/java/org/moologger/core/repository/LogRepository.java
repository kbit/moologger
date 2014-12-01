package org.moologger.core.repository;

import org.moologger.core.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {}