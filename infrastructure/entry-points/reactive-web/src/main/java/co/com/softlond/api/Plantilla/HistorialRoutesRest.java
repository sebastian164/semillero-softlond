package co.com.softlond.api.Plantilla;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class HistorialRoutesRest {
    @Bean
    public RouterFunction<ServerResponse> historialRoutes(HistorialHandler historialHandler) {
        return route(GET("api/historial/{id}"), historialHandler::findById);
    }
}
