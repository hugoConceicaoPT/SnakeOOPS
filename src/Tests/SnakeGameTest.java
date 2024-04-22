package Tests;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ControllerLayer.SnakeGame;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

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
    public void keyReleasedTest() throws CloneNotSupportedException {
        String input1 = "1 1 1 3 3 3 3 1";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado);
        snake.increaseSize();
        snake.increaseSize();
        SnakeGame snakeGame = new SnakeGame(snake,null,3,4);
        Component source = new Component() {};
        @SuppressWarnings("deprecation")
        KeyEvent keyEvent = new KeyEvent(source, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP);
        snakeGame.keyReleased(keyEvent);
        assertEquals("Tecla Solta!", outContent.toString().trim());
    }    
}
