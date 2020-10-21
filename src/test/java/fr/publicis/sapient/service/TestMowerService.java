package fr.publicis.sapient.service;

import fr.publicis.sapient.model.Direction;
import fr.publicis.sapient.model.Grass;
import fr.publicis.sapient.model.Mower;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@Log
public class TestMowerService {

    private MowerService mowerService;

    @Before
    public void setUp() {
        mowerService = new MowerService();
    }

    @Test
    public void should_init_mower() {
        Grass grass = getGrass1();
        assertNotNull(mowerService.initMower(grass, 0, 0, "N"));
        assertThrows(IllegalArgumentException.class, () -> mowerService.initMower(grass, 1,10, "fake"));
        assertThrows(RuntimeException.class, () -> mowerService.initMower(grass, 4, 2, "W"));
    }

    @Test
    public void should_move_mower_to_right() {
        Grass grass = getGrass1();
        Mower mower = getMowerV();
        assertEquals("N", mower.getDirection().getValue());

        mowerService.move(grass, mower, "D");
        assertEquals("E", mower.getDirection().getValue());

        mowerService.move(grass, mower, "D");
        assertEquals("S", mower.getDirection().getValue());

        mowerService.move(grass, mower, "D");
        assertEquals("W", mower.getDirection().getValue());

        mowerService.move(grass, mower, "D");
        assertEquals("N", mower.getDirection().getValue());
    }

    @Test
    public void should_move_mower_to_left() {
        Grass grass = getGrass1();
        Mower mower = getMowerV();
        assertEquals("N", mower.getDirection().getValue());

        mowerService.move(grass, mower, "G");
        assertEquals("W", mower.getDirection().getValue());

        mowerService.move(grass, mower, "G");
        assertEquals("S", mower.getDirection().getValue());

        mowerService.move(grass, mower, "G");
        assertEquals("E", mower.getDirection().getValue());

        mowerService.move(grass, mower, "G");
        assertEquals("N", mower.getDirection().getValue());
    }

    @Test
    public void should_move_mower_forward() {
        Grass grass = getGrass2();
        Mower mowerV = getMowerV();
        mowerService.move(grass, mowerV, "A");
        assertEquals(0, mowerV.getPosX());
        assertEquals(1, mowerV.getPosY());

        Mower mowerH = getMowerH();
        mowerService.move(grass, mowerH, "A");
        assertEquals(1, mowerH.getPosX());
        assertEquals(0, mowerH.getPosY());
    }

    @Test
    public void should_hold_position() {
        Grass grass = getGrass3();
        Mower mowerV = getMowerV();
        mowerV.setPosY(7);
        mowerService.move(grass, mowerV, "A");
        assertEquals(0, mowerV.getPosX());
        assertEquals(7, mowerV.getPosY());

        Mower mowerH = getMowerH();
        mowerH.setPosX(15);
        mowerService.move(grass, mowerH, "A");
        assertEquals(15, mowerH.getPosX());
        assertEquals(0, mowerH.getPosY());
    }

    private Grass getGrass1() {
        return new Grass("G001", 3, 2);
    }

    private Grass getGrass2() {
        return new Grass("G002", 10, 5);
    }

    private Grass getGrass3() {
        return new Grass("G003", 15, 7);
    }

    private Mower getMowerV() {
        return new Mower("1234", 0, 0, Direction.NORTH);
    }

    private Mower getMowerH() {
        return new Mower("5678", 0, 0, Direction.EAST);
    }
}
