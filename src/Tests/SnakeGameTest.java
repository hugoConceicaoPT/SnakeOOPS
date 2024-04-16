package Tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GameLayer.SnakeLayer.Quadrado;
import GameLayer.SnakeLayer.Snake;
import GameLayer.SnakeLayer.SnakeGame;

import java.awt.Component;
import java.awt.event.KeyEvent;

public class SnakeGameTest{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void keyReleasedTest() {
        String input1 = "1 1 1 3 3 3 3 1";
        String input2 = "3 3 3 1 5 1 5 3";
        String input3 = "5 3 5 1 7 1 7 3";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        quadrado.add(new Quadrado(input2));
        quadrado.add(new Quadrado(input3));
        Snake snake = new Snake(quadrado);
        SnakeGame snakeGame = new SnakeGame(snake);
        Component source = new Component() {};
        @SuppressWarnings("deprecation")
        KeyEvent keyEvent = new KeyEvent(source, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP);
        snakeGame.keyReleased(keyEvent);
        assertEquals("Tecla Solta!", outContent.toString().trim());
    }    
}
