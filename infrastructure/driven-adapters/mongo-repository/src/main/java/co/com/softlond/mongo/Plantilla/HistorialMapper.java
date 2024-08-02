package co.com.softlond.mongo.Plantilla;

import co.com.softlond.model.HistorialModel;
import co.com.softlond.model.PlantillaModel;
import co.com.softlond.mongo.Collections.HistorialCollections;
import co.com.softlond.mongo.Collections.PlantillaCollections;

public class HistorialMapper {
    public static HistorialModel toModel(HistorialCollections historialCollections) {
        return HistorialModel.builder()
                .id(historialCollections.getId())
                .contador(historialCollections.getContador())
                .lastDescription(historialCollections.getLastDescription())
                .build();
    }

    public static HistorialCollections toCollection(HistorialModel historialModel) {
        HistorialCollections historialCollections = new HistorialCollections();
        historialCollections.setId(historialModel.getId());
        historialCollections.setContador(historialModel.getContador());
        historialCollections.setLastDescription(historialModel.getLastDescription());
        return historialCollections;
    }
}
