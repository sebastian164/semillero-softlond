package co.com.softlond.model.gateways;

import co.com.softlond.model.HistorialModel;
import reactor.core.publisher.Mono;

public interface HistorialGateways {
    Mono<HistorialModel> saveHistorial(HistorialModel historialModel);
    Mono<HistorialModel> findById(String id);
}
