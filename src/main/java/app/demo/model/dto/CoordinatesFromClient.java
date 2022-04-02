package app.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesFromClient {
    private int id;
    private String x;
    private String y;

    public CoordinatesFromClient (String xml) {
        x = xml.substring(xml.indexOf("<x>") + 3, xml.indexOf("</x>"));
        y = xml.substring(xml.indexOf("<y>") + 3, xml.indexOf("</y>"));
    }
}