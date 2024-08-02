package co.com.softlond.api.Plantilla;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class PlantillaRoutesRest {
    
    @Bean
    public RouterFunction<ServerResponse> plantillaRoutes(PlantillaHandler plantillaHandler) {
        return route(POST("/api/plantilla/save"), plantillaHandler::savePlantilla)
                .andRoute(GET("/api/plantilla/list"), plantillaHandler::getAllPlantillas)
                .andRoute(DELETE("/api/plantilla/delete/{id}"), plantillaHandler::deletePlantilla)
                .andRoute(GET("api/plantilla/{id}"), plantillaHandler::findById);
    }
}
