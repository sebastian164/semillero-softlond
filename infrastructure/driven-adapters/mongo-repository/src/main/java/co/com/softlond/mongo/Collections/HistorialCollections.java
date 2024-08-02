package co.com.softlond.mongo.Collections;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "historial")
public class HistorialCollections {
    @Id
    private String id;
    private Integer contador;
    private String lastDescription;
}