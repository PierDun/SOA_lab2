package app.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaveFromClient {
    private int id;
    private String depth;

    public CaveFromClient (String xml) {
        depth = xml.substring(xml.indexOf("<depth>") + 7, xml.indexOf("</depth>"));
    }
}