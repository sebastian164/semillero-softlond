package co.com.softlond.usecase.Plantilla;

import co.com.softlond.model.HistorialModel;
import co.com.softlond.model.gateways.HistorialGateways;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class HistorialOperationsUseCase {
    private final HistorialGateways historialGateways;

    public HistorialOperationsUseCase(HistorialGateways historialGateways) {
        this.historialGateways = historialGateways;
    }

    public Mono<HistorialModel> saveHistorial(HistorialModel historialModel){
        return  historialGateways.saveHistorial(historialModel);
    }

    public Mono<HistorialModel> findById(String id){
        return historialGateways.findById(id);
    }


}
