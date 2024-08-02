package co.com.softlond.mongo.Plantilla;

import co.com.softlond.model.Constans;
import co.com.softlond.model.HistorialModel;
import co.com.softlond.model.gateways.HistorialGateways;
import co.com.softlond.mongo.Collections.HistorialCollections;
import co.com.softlond.mongo.Plantilla.Mapper.HistorialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class HistorialGatewaysImpl implements HistorialGateways {

    @Autowired
    private ReactiveHistorialMongoRepository reactiveHistorialMongoRepository;


    @Override
    public Mono<HistorialModel> saveHistorial(HistorialModel historialModel) {
        return reactiveHistorialMongoRepository.save(HistorialMapper.toCollection(historialModel))
                .map(HistorialMapper::toModel);
    }

    @Override
    public Mono<HistorialModel> findById(String id) {
        return reactiveHistorialMongoRepository.findById(id)
                .map(HistorialMapper::toModel);
    }

}
