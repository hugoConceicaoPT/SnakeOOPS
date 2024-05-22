import java.util.*;

import ControllerLayer.Leaderboard;
import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;
import ViewLayer.*;

/**
 * Classe principal que atua como cliente do jogo da Cobra.
 * Responsabilidade: Coletar as preferências do jogador e iniciar o jogo com os parâmetros fornecidos.
 * @version 1.0 12/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class Cliente {

    /**
     * Cria pontos que formarao o quadrado da cabeca da cobra.
     * @param widthBoard Largura do tabuleiro.
     * @param heightBoard Altura do tabuleiro.
     * @param size Tamanho da cabeca da cobra.
     * @param random gerador de números aleatórios
     * @return Lista de pontos que formam o quadrado da cabeca da cobra.
     */
    private static List<Ponto<? extends Number>> createSquarePoints(int widthBoard, int heightBoard, int size, Random random) {
        int y = 1;
        int x = 1;
        while(x % size != 0) {
            x = random.nextInt(widthBoard - size);
        }
        while(y % size != 0)
            y = random.nextInt(heightBoard - size);
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x + size, y));
        pontos.add(new Ponto<Integer>(x + size, y + size));
        pontos.add(new Ponto<Integer>(x, y + size));
        return pontos;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------- Bem vindo ao jogo da Cobra -----------------------------");

        System.out.print("Indique o seu nome (clique enter se quiser o nome default): ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            name = "Player";
        }

        System.out.print("Digite o comprimento da arena: ");
        int width = sc.nextInt();
        System.out.print("Digite a largura da arena: ");
        int height = sc.nextInt();

        System.out.print("Digite a dimensão da cabeça: ");
        int headSnakeDimension = sc.nextInt();

        sc.nextLine();
        System.out.print("Deseja o movimento da cobra manual (sim/nao)? ");
        String movementSnake = sc.nextLine();
        boolean isSnakeManualMovement = false;
        if(movementSnake.equals("sim"))
            isSnakeManualMovement = true;
        System.out.print("Digite o modo de rasterização (contorno/completa): ");
        String rasterizationMode = sc.nextLine();

        System.out.print("Digite a dimensão da comida (menor do que a dimensão da cabeça): ");
        int foodDimension = sc.nextInt();
        while (foodDimension >= headSnakeDimension) {
            System.out.print("Digite a dimensão da comida (menor do que a dimensão da cabeça): ");
            foodDimension = sc.nextInt();
        }
        sc.nextLine();

        System.out.print("Indique o tipo de comida (podem ser quadrados ou círculos): ");
        String foodStringType = sc.nextLine();
        FoodType foodType;
        if(foodStringType.equals("quadrados"))
            foodType = FoodType.SQUARE;
        else
            foodType = FoodType.CIRCLE;
        System.out.print("Defina a pontuação por cada comida: ");
        int foodScore = sc.nextInt();
        sc.nextLine();

        System.out.print("Indique quantos obstáculos deseja aparecer na arena: ");
        int obstaclesQuantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Deseja que os obstáculos sejam dinâmicos (sim/nao): ");
        String obstacleMovement = sc.nextLine();
        boolean isObstacleDynamic = false;
        if(obstacleMovement.equals("sim"))
            isObstacleDynamic = true;

        List<Ponto<? extends Number>> listObstacleRotacionPoint = new ArrayList<>();
        List<Integer> listObstacleAngles = new ArrayList<>();
        if(isObstacleDynamic) {
            for(int i = 0; i < obstaclesQuantity; i++) {
                System.out.print("Indique ou não o movimento de rotação de cada obstáculo: ");
                String obstacleRotacionString = sc.nextLine();
                System.out.print("Indique o ângulo para cada obstáculo: ");
                int obstacleAngle = sc.nextInt();
                sc.nextLine();
                listObstacleAngles.add(obstacleAngle);
                String [] obstaclesParts = obstacleRotacionString.split(" ");
                if(obstaclesParts.length > 1)
                    listObstacleRotacionPoint.add(new Ponto<Integer>(Integer.parseInt(obstaclesParts[0]), Integer.parseInt(obstaclesParts[1])));
                else
                    listObstacleRotacionPoint.add(null);
            }
        }
        System.out.print("Indique o modo de interface (textual/grafica): ");
        String UIMode = sc.nextLine();

        LinkedList<Quadrado> bodySnake = new LinkedList<>();
        Quadrado quadrado = new Quadrado(createSquarePoints(width, height, headSnakeDimension,random));
        bodySnake.addFirst(quadrado);
        Snake snake = new Snake(bodySnake, isSnakeManualMovement,random);
        GameBoard gameBoard = new GameBoard(snake,width,height,foodType,foodDimension,obstaclesQuantity,listObstacleRotacionPoint,listObstacleAngles,isObstacleDynamic,random);
        UI userInterface;
        if("textual".equals(UIMode)) {
            RasterizationTextualStrategy rasterizationTextualStrategy;
            if(rasterizationMode.equals("contorno"))
                rasterizationTextualStrategy = new ContourTextualRasterization(gameBoard);
            else
                rasterizationTextualStrategy = new FullTextualRasterization(gameBoard);
            userInterface = new TextualUI(rasterizationTextualStrategy);
        }
        else {
            RasterizationGraphicStrategy rasterizationGraphicStrategy;
            if(rasterizationMode.equals("contorno"))
                rasterizationGraphicStrategy = new ContourGraphicRasterization(gameBoard);
            else
                rasterizationGraphicStrategy = new FullGraphicRasterization(gameBoard);
            userInterface = new GraphicalUI(rasterizationGraphicStrategy);
        }
        SnakeGame snakeGame = new SnakeGame(name, gameBoard,userInterface, foodScore);
        if(userInterface instanceof TextualUI)
            snakeGame.runGame(sc);
    }
}
