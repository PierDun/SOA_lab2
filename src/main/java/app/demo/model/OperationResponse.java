package app.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationResponse {
    private Long dragonId;
    private String message;
}