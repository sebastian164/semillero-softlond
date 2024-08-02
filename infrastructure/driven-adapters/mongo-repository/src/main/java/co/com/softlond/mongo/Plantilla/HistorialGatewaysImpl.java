package co.com.softlond.mongo.Plantilla;

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
    private static final String HISTORIAL_ID = "historial_unico";

    @Override
    public Mono<HistorialModel> saveHistorial(HistorialModel historialModel) {
        return reactiveHistorialMongoRepository.findById(HISTORIAL_ID)
                .flatMap(existingHistorial -> {
                    existingHistorial.setContador(existingHistorial.getContador() + 1);
                    existingHistorial.setLastDescription(historialModel.getLastDescription());
                    return reactiveHistorialMongoRepository.save(existingHistorial);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    HistorialCollections newHistorial = new HistorialCollections();
                    newHistorial.setId(HISTORIAL_ID);
                    newHistorial.setContador(1);
                    newHistorial.setLastDescription(historialModel.getLastDescription());
                    return reactiveHistorialMongoRepository.save(newHistorial);
                }))
                .map(HistorialMapper::toModel)
                .onErrorResume(error -> {
                    return Mono.error(new RuntimeException("Error saving historial", error));
                });
    }

    @Override
    public Mono<HistorialModel> findById(String id) {
        return reactiveHistorialMongoRepository.findById(id)
                .map(HistorialMapper::toModel);
    }

}
