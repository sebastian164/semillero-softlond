package co.com.softlond.mongo.Plantilla;

import co.com.softlond.model.HistorialModel;
import co.com.softlond.model.gateways.HistorialGateways;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.softlond.model.PlantillaModel;
import co.com.softlond.model.gateways.PlantillaGateways;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PlantillaGatewaysImpl implements PlantillaGateways {    

    @Autowired
    private ReactivePlantillaMongoRepository reactivePlantillaMongoRepository;

    @Autowired
    private HistorialGateways historialGateways;

    @Override
    public Mono<PlantillaModel> savePlantilla(PlantillaModel plantilla) {
        HistorialModel historialModel = new HistorialModel();
        historialModel.setLastDescription(plantilla.getDescripcion());
        return historialGateways.saveHistorial(historialModel)
                .then(reactivePlantillaMongoRepository.save(PlantillaMapper.toCollection(plantilla)))
                .map(PlantillaMapper::toModel);
    }

    @Override
    public Flux<PlantillaModel> listPlantilla() {
        return reactivePlantillaMongoRepository.findAll()
                .map(PlantillaMapper::toModel);
    }

    @Override
    public Mono<Void> deletePlantillaById(String id) {
        return reactivePlantillaMongoRepository.deleteById(id);
    }


}
