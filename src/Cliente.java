import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ControllerLayer.SnakeGame;
import ModelLayer.SnakeLayer.Ponto;

/**
 * Classe principal que atua como cliente do jogo da Cobra.
 * Responsabilidade: Coletar as preferências do jogador e iniciar o jogo com os parâmetros fornecidos.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class Cliente {
    public static void main(String[] args) {
        // Inicia um scanner para coletar as entradas do usuário
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------- Bem vindo ao jogo da Cobra -----------------------------");
        
        // Coleta o nome do jogador (ou usa um nome padrão)
        System.out.print("Indique o seu nome (clique enter se quiser o nome default): ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            name = "Player";
        }

        // Coleta as dimensões da arena de jogo (largura e altura)
        System.out.print("Digite o comprimento da arena: ");
        int width = sc.nextInt();
        System.out.print("Digite a largura da arena: ");
        int height = sc.nextInt();

        // Coleta a dimensão da cabeça da cobra
        System.out.print("Digite a dimensão da cabeça: ");
        int headDimension = sc.nextInt();
        sc.nextLine();

        // Verifica se a cobra deve ser controlada manualmente
        System.out.print("Deseja o movimento da cobra manual (sim/nao)? ");
        String movementSnake = sc.nextLine();
        boolean isSnakeManualMovement = false;
        if(movementSnake.equals("sim"))
            isSnakeManualMovement = true;

        // Escolhe o modo de rasterização (contorno ou completa)
        System.out.print("Digite o modo de rasterização (contorno/completa): ");
        String rasterizationMode = sc.nextLine();

        // Coleta a dimensão da comida, assegurando que seja menor do que a da cabeça
        System.out.print("Digite a dimensão da comida (menor do que a dimensão da cabeça): ");
        int foodDimension = sc.nextInt();
        while (foodDimension >= headDimension) {
            System.out.print("Digite a dimensão da comida (menor do que a dimensão da cabeça): ");
            foodDimension = sc.nextInt();
        }
        sc.nextLine();

        // Coleta o tipo de comida (quadrado ou círculo) e a pontuação por coleta
        System.out.print("Indique o tipo de comida (podem ser quadrados ou círculos): ");
        String foodType = sc.nextLine();
        System.out.print("Defina a pontuação por cada comida: ");
        int foodScore = sc.nextInt();
        sc.nextLine();

        // Coleta a quantidade de obstáculos desejados na arena
        System.out.print("Indique quantos obstáculos deseja aparecer na arena: ");
        int obstaclesQuantity = sc.nextInt();
        sc.nextLine();

        // Verifica se os obstáculos devem ser dinâmicos
        System.out.print("Deseja que os obstáculos sejam dinâmicos (sim/nao): ");
        String obstacleMovement = sc.nextLine();
        boolean isObstacleDynamic = false;
        if(obstacleMovement.equals("sim"))
            isObstacleDynamic = true;

        // Coleta pontos de rotação específicos para os obstáculos dinâmicos
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

        // Obtém a semente para o gerador de números aleatórios
        long seed = System.currentTimeMillis();

        // Cria e executa o jogo da Cobra
        SnakeGame snakeGame = new SnakeGame(name,width, height, headDimension, isSnakeManualMovement ,rasterizationMode, foodDimension, foodType, foodScore, obstaclesQuantity, listObstacleRotacionPoint,listObstacleAngles,isObstacleDynamic, UIMode, seed);
        snakeGame.runGame(sc);
        snakeGame.endGame();
    }
}
