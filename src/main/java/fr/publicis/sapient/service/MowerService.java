package fr.publicis.sapient.service;

import fr.publicis.sapient.model.Action;
import fr.publicis.sapient.model.Direction;
import fr.publicis.sapient.model.Grass;
import fr.publicis.sapient.model.Mower;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.util.UUID;

@Log
@AllArgsConstructor
public class MowerService {

    public Mower initMower(Grass grass, String line) {
        String[] tab = line.split("\\s");
        try {
            int posX = Integer.parseInt(tab[0]);
            int posY = Integer.parseInt(tab[1]);
            String direction = tab[2];
            return initMower(grass, posX, posY, direction);
        } catch (Exception e) {
            log.severe("Unable to parse mower info: "+ e.getMessage());
            throw e;
        }
    }

    public Mower initMower(Grass grass, int posX, int posY, String direction) {
        UUID id = UUID.randomUUID();
        Direction directionValue = Direction.fromValue(direction);
        if (posX <= grass.getWidth() && posY <= grass.getHeight()) {
            return new Mower(id.toString(), posX, posY, directionValue);
        }
        throw new RuntimeException("Impossible to put the mow inside the grass");
    }

    public void move(Grass grass, Mower mower, String... actions) {
        for (String rawAction : actions) {
            Action action = Action.fromValue(rawAction);
            switch (action) {
                case TURN_RIGHT: turnRight(mower); break;
                case TURN_LEFT: turnLeft(mower); break;
                case GO: go(grass, mower); break;
                default: break;
            }
        }
    }

    private void turnRight(Mower mower) {
        try {
            int newDirectionOrdinal = mower.getDirection().ordinal() == (Direction.values().length-1) ? -1 : mower.getDirection().ordinal();
            Direction newDirection = Direction.values()[newDirectionOrdinal+1];
            mower.setDirection(newDirection);
        } catch (IndexOutOfBoundsException ignored) {}
    }

    private void turnLeft(Mower mower) {
        try {
            int newDirectionOrdinal = mower.getDirection().ordinal() == 0 ? Direction.values().length : mower.getDirection().ordinal();
            Direction newDirection = Direction.values()[newDirectionOrdinal-1];
            mower.setDirection(newDirection);
        } catch (IndexOutOfBoundsException ignored) {}
    }

    private void go(Grass grass, Mower mower) {
        switch (mower.getDirection()) {
            case NORTH: goUp(grass, mower); break;
            case SOUTH: goDown(mower); break;
            case EAST: goRight(grass, mower); break;
            case WEST: goLeft(mower); break;
            default: break;
        }
    }

    private void goUp(Grass grass, Mower mower) {
        if (grass == null) throw new RuntimeException("Execution failed: the grass was not initiated");
        if (grass.getHeight() > mower.getPosY()) mower.setPosY(mower.getPosY()+1);
    }

    private void goDown(Mower mower) {
        if (mower.getPosY() > 0) mower.setPosY(mower.getPosY()-1);
    }

    private void goRight(Grass grass, Mower mower) {
        if (grass == null) throw new RuntimeException("Execution failed: the grass was not initiated");
        if (grass.getWidth() > mower.getPosX()) mower.setPosX(mower.getPosX()+1);
    }

    private void goLeft(Mower mower) {
        if (mower.getPosX() > 0) mower.setPosX(mower.getPosX()-1);
    }
}
