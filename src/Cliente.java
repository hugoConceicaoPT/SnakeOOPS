import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**	Classe que representa um cliente para testar as funcionalidades
    Responsabilidade: Interagir com o usuário para receber entradas 
    e testar as funcionalidades das outras classes
    @version 1.0 26/03/2024
    @author Hugo Conceição
*/
public class Cliente {

    public static String capital(String s) {
        if (s == null || s.isEmpty())
        return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Constructor<?> constructor;
        Class<?> cl;
        Poligono p;
        String s;
        String[] aos;
        int centroX = 0;
        int centroY = 0;
        List<Poligono> poligonos = new ArrayList<>();
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            if (s.isEmpty())
                break;
            aos = s.split(" ", 2);
            try {
                cl = Class.forName(capital(aos[0]));
                constructor = cl.getConstructor(String.class);
                p = (Poligono) constructor.newInstance(aos[1]);
                poligonos.add(p);
                centroX = sc.nextInt();
                centroY = sc.nextInt();
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sc.close();
        Poligono poligonoTranslado = poligonos.get(0).translateCentroide(centroX,centroY);
        if(poligonos.get(0).equals(poligonoTranslado))
        {
            System.out.print("Duplicado");
            System.exit(0);
        }

        System.out.println(poligonoTranslado.toString());
    }    
}