package co.com.softlond.mongo.Plantilla;

import co.com.softlond.mongo.Collections.HistorialCollections;
import co.com.softlond.mongo.Collections.PlantillaCollections;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface ReactiveHistorialMongoRepository extends ReactiveMongoRepository<HistorialCollections, String> {
    
}
