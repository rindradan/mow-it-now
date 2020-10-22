package fr.publicis.sapient;

import fr.publicis.sapient.model.Grass;
import fr.publicis.sapient.model.Mower;
import fr.publicis.sapient.service.GrassService;
import fr.publicis.sapient.service.MowerService;
import fr.publicis.sapient.service.ParserService;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;

@Log
public class MowItNow {

    public static void main(String[] args) {
        try {
            String filename = args[0];

            GrassService grassService = new GrassService();
            MowerService mowerService = new MowerService();
            ParserService parserService = new ParserService();

            List<String> data = parserService.readFile(filename);
            Grass grass = grassService.initGrass(data.get(0));
            data.remove(0);

            for (int i = 0; i < data.size(); ) {
                try {
                    Mower mower = mowerService.initMower(grass, data.get(i));
                    String[] actions = data.get(i + 1).split("");
                    mowerService.move(grass, mower, actions);
                    // log.info(mower.toString());
                    System.out.println(mower);
                } catch (Exception ignored) {}
                i += 2;
            }

        } catch (IOException e) {
            log.severe("Failed to read the file: " + e.getMessage());
        } catch (Exception e) {
            log.severe("Execution failed: " + e.getMessage());
        }
    }
}
