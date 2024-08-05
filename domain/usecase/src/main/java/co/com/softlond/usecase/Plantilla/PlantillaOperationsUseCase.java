package co.com.softlond.usecase.Plantilla;

import java.sql.Date;

import co.com.softlond.model.Constans;
import co.com.softlond.model.HistorialModel;
import org.springframework.stereotype.Service;

import co.com.softlond.model.PlantillaModel;
import co.com.softlond.model.gateways.PlantillaGateways;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.Logger;
import reactor.util.Loggers;

@Service
public class PlantillaOperationsUseCase {

    private final PlantillaGateways plantillaGateways;
    private final HistorialOperationsUseCase historialOperationsUseCase;
    private final Logger logger = Loggers.getLogger(PlantillaOperationsUseCase.class);


    public PlantillaOperationsUseCase(PlantillaGateways plantillaGateways, HistorialOperationsUseCase historialOperationsUseCase) {
        this.plantillaGateways = plantillaGateways;
        this.historialOperationsUseCase = historialOperationsUseCase;
    }

    public Mono<PlantillaModel> savePlantilla(PlantillaModel plantilla) {

        return Mono.justOrEmpty(plantilla.getId())
                .filter(id -> !id.isEmpty())
                .flatMap(id -> plantillaGateways.findById(id)
                        .flatMap(existingPlantilla -> {
                            return plantillaGateways.savePlantilla(plantilla)
                                    .doOnSuccess(savedPlantilla -> {
                                        if (savedPlantilla != null) {
                                            handleHistorial(savedPlantilla, false);
                                        } else {
                                            logger.warn("Saved plantilla is null after saving with existing ID");
                                        }
                                    });
                        }))
                .switchIfEmpty(
                        plantillaGateways.savePlantilla(plantilla)
                                .doOnSuccess(savedPlantilla -> {
                                    if (savedPlantilla != null) {
                                        handleHistorial(savedPlantilla, true);
                                    } else {
                                        logger.warn("Saved plantilla is null after saving new template");
                                    }
                                }))
                .onErrorResume(e -> {
                    logger.error("Error saving the plantilla: {}", e.getMessage(), e); // Manejo de errores
                    return Mono.empty();
                });

    }

    private void handleHistorial(PlantillaModel savedPlantilla, boolean isNew) {
        saveHistorialAsync(savedPlantilla.getDescripcion(), isNew)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    private Mono<Void> saveHistorialAsync(String descripcion, boolean isNew) {
        return historialOperationsUseCase.findById(Constans.HISTORIAL_ID)
                .defaultIfEmpty(new HistorialModel())
                .flatMap(history -> {
                    if (isNew) {
                        history.setContador(null == history.getContador() ? 1 : history.getContador() + 1);
                    }
                    history.setLastDescription(descripcion);
                    return historialOperationsUseCase.saveHistorial(history);
                })
                .then();
    }

    public Flux<PlantillaModel> listPlantilla() {
        return plantillaGateways.listPlantilla();
    }

    public Mono<Void> deletePlantillaById(String id) {
        return plantillaGateways.deletePlantillaById(id);
    }

    public Mono<PlantillaModel> findById(String id) {
        return plantillaGateways.findById(id);
    }


}
