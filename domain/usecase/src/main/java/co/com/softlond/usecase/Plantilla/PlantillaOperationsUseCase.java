package co.com.softlond.usecase.Plantilla;

import java.sql.Date;

import org.springframework.stereotype.Service;

import co.com.softlond.model.PlantillaModel;
import co.com.softlond.model.gateways.PlantillaGateways;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlantillaOperationsUseCase  {
    
    private final PlantillaGateways plantillaGateways;

    public PlantillaOperationsUseCase(PlantillaGateways plantillaGateways) {
        this.plantillaGateways = plantillaGateways;
    }

    public Mono<PlantillaModel> savePlantilla(PlantillaModel plantilla) {
        /* lOGICA DE NEGOCIO */
        plantilla.setFechaActualizacion(new Date(System.currentTimeMillis()));
        return plantillaGateways.savePlantilla(plantilla);
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
