package co.com.softlond.api.Plantilla;

import co.com.softlond.usecase.Plantilla.HistorialOperationsUseCase;
import co.com.softlond.usecase.Plantilla.PlantillaOperationsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class HistorialHandler{
    private final HistorialOperationsUseCase historialOperationsUseCase;

    public HistorialHandler(HistorialOperationsUseCase historialOperationsUseCase) {
        this.historialOperationsUseCase = historialOperationsUseCase;
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");

        return historialOperationsUseCase.findById(id)
                .flatMap(historial -> ServerResponse.ok().bodyValue(historial))
                .switchIfEmpty(ServerResponse.noContent().build())
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
    }

}
