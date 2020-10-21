package fr.publicis.sapient.service;

import fr.publicis.sapient.model.Grass;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.UUID;

@Log
@Data
public class GrassService {

    public Grass initGrass(int width, int height) {
        UUID id = UUID.randomUUID();
        return new Grass(id.toString(), width, height);
    }

    public Grass initGrass(String line) {
        String[] tab = line.split("\\s");
        try {
            int posX = Integer.parseInt(tab[0]);
            int posY = Integer.parseInt(tab[1]);
            return initGrass(posX, posY);
        } catch (Exception e) {
            log.severe("Unable to parse grass size: "+ e.getMessage());
            throw e;
        }
    }
}
