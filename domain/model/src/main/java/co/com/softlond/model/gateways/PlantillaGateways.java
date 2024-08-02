package co.com.softlond.model.gateways;

import co.com.softlond.model.PlantillaModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlantillaGateways {
    Mono<PlantillaModel> savePlantilla(PlantillaModel plantilla);
    Flux<PlantillaModel> listPlantilla();
    Mono<Void> deletePlantillaById(String id);
}
