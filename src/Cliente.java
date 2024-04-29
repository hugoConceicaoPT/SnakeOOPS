import java.util.Scanner;

import ControllerLayer.SnakeGame;
import ModelLayer.SnakeLayer.Ponto;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------- Bem vindo ao jogo da Cobra -----------------------------");  
        System.out.print("Digite o comprimento da arena: ");
        int width = sc.nextInt();
        System.out.print("Digite a largura da arena: ");
        int height = sc.nextInt();
        System.out.print("Digite a dimensão da cabeça: ");
        int headDimension = sc.nextInt();
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
        sc.nextLine();
        System.out.print("Indique o tipo de comida (podem ser quadrados ou círculos): ");
        String foodType = sc.nextLine();
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
        Ponto obstacleRotacionPoint = null;
        boolean isObstacleMovementAroundCenter = false;
        if(isObstacleDynamic) {
            System.out.println("Indique ou não o movimento de rotação desses obstáculos: ");
            String obstacleRotacionString = sc.nextLine(); 
            String [] obstaclesParts = obstacleRotacionString.split(" ");
            if(obstaclesParts.length > 2)
                obstacleRotacionPoint = new Ponto(Double.parseDouble(obstaclesParts[1]), Double.parseDouble(obstaclesParts[2]));
            else
                isObstacleMovementAroundCenter = true;
        }
        System.out.print("Indique o modo de interface (textual/grafica): ");
        String UIMode = sc.nextLine();
        SnakeGame snakeGame = new SnakeGame(width, height, headDimension, isSnakeManualMovement ,rasterizationMode, foodDimension, foodType, foodScore, obstaclesQuantity, obstacleRotacionPoint, isObstacleMovementAroundCenter, isObstacleDynamic, UIMode, sc);
        snakeGame.runGame();
    }
}
