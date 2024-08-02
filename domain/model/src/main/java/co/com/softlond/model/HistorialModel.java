package co.com.softlond.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HistorialModel {
    private String id;
    private Integer contador;
    private String lastDescription;
}
