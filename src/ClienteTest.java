import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
public class ClienteTest {

    /* Mooshak performs black box tests
    * Each row of the array represents one test
    * First element in the row is the input (what to put in STDIN)
    * Second (and last) element in the row is the expected output (at STDOUT)
    */
    static private String [][] stdiotests = {
        {"Retangulo 1 1 5 1 5 3 1 3\n3 3\n", "Retangulo: [(1,2), (5,2), (5,4), (1,4)]\n" },
        {"Triangulo 1 0 3 0 2 3\n2 3\n", "Triangulo: [(1,2), (3,2), (2,5)]\n" }
    };

    // Redirect STDIN and STDOUT for Mooshak like black box tests
    static private ByteArrayOutputStream setIOstreams(String input) {
        //set stdin
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        //set stdout
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        return os;
    }

     /*
    * Mooshak like black box tests
    */
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void testCase0() {
        for (String[] test : stdiotests) {
            String input = test[0];
            String expected = test[1].trim();
            ByteArrayOutputStream os = setIOstreams(input);
            try {
                Cliente.main(null);
                assertEquals(expected, os.toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }    
}
