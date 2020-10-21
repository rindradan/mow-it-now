package fr.publicis.sapient.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mower {
    private String id;
    private int posX;
    private int posY;
    private Direction direction;

    @Override
    public String toString() {
        return posX +" "+ posY +" "+ direction.getValue();
    }
}
