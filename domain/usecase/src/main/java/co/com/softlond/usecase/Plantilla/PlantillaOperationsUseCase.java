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

@Service
public class PlantillaOperationsUseCase  {
    
    private final PlantillaGateways plantillaGateways;
    private final HistorialOperationsUseCase historialOperationsUseCase;

    public PlantillaOperationsUseCase(PlantillaGateways plantillaGateways, HistorialOperationsUseCase historialOperationsUseCase) {
        this.plantillaGateways = plantillaGateways;
        this.historialOperationsUseCase = historialOperationsUseCase;
    }

    public Mono<PlantillaModel> savePlantilla(PlantillaModel plantilla) {
        return plantillaGateways.savePlantilla(plantilla)
                .doOnSuccess(savedPlantilla ->
                        saveHistorialAsync(savedPlantilla.getDescripcion())
                                .subscribeOn(Schedulers.boundedElastic())
                                .subscribe());
    }

    private Mono<Void> saveHistorialAsync(String descripcion){
        return historialOperationsUseCase.findById(Constans.HISTORIAL_ID)
                .defaultIfEmpty(new HistorialModel())
                .flatMap(history -> {
                    history.setContador(null == history.getContador() ? 1 : history.getContador() + 1);
                    history.setLastDescription(descripcion);
                    return historialOperationsUseCase.saveHistorial(history);
                })
                .then();
    }

    public Flux<PlantillaModel> listPlantilla(){
        return plantillaGateways.listPlantilla();
    }

    public Mono<Void> deletePlantillaById(String id){
        return plantillaGateways.deletePlantillaById(id);
    }

    public Mono<PlantillaModel> findById(String id){
        return  plantillaGateways.findById(id);
    }



}
