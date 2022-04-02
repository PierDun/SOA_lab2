package app.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import app.demo.model.enums.Color;
import app.demo.model.enums.DragonType;

@Data
@AllArgsConstructor
public class DragonFromClient {
    private long id;
    private String name;
    private String age;
    private Color color;
    private DragonType type;
    private String description;
    private CoordinatesFromClient coordinates;
    private CaveFromClient cave;

    public DragonFromClient() {}

    public DragonFromClient(String xml) {
        name = xml.substring(xml.indexOf("<name>") + 6, xml.indexOf("</name>"));
        age = xml.substring(xml.indexOf("<age>") + 5, xml.indexOf("</age>"));
        color = Color.valueOf(xml.substring(xml.indexOf("<color>") + 7, xml.indexOf("</color>")));
        type = DragonType.valueOf(xml.substring(xml.indexOf("<type>") + 6, xml.indexOf("</type>")));
        description = xml.substring(xml.indexOf("<description>") + 13, xml.indexOf("</description>"));

        coordinates = new CoordinatesFromClient(xml);
        cave = new CaveFromClient(xml);
    }
}