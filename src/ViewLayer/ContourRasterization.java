package ViewLayer;

import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.Food;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.SegmentoReta;

/**
 * Classe que implementa a estratégia de rasterização do contorno dos elementos no tabuleiro.
 * Responsabilidade: Representar os elementos do tabuleiro, como cobra, obstáculos e comida,
 * destacando apenas seus contornos.
 * @version 1.0 12/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class ContourRasterization extends RasterizationStrategy {

    /**
     * Construtor que inicializa a estratégia de rasterização do contorno com um tabuleiro de jogo.
     * @param gameBoard O tabuleiro do jogo que contém os elementos a serem representados.
     */
    public ContourRasterization(GameBoard gameBoard) {
        super(gameBoard);
    }

    /**
     * Representa o tabuleiro como uma string, incluindo todos os elementos rasterizados.
     * @return Uma string que representa o tabuleiro.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = this.rows - 1; i >= 0; i--) {
            for (int j = 0; j < this.cols; j++) {
                result.append(board[i][j].toString()).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Atualiza as células do tabuleiro que representam os contornos do corpo da cobra.
     * As células internas são deixadas vazias, enquanto as bordas são marcadas como contornos.
     */
    public void updateSnakeCells() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.HEAD || board[i][j].getCellType() == CellType.TAIL) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }

        for (int i = 1; i < this.gameBoard.getSnake().getBody().size(); i++) {
            Quadrado segment = this.gameBoard.getSnake().getBody().get(i);
            int minX = (int) segment.getMinX();
            int maxX = (int) segment.getMaxX() - 1;
            int minY = (int) segment.getMinY();
            int maxY = (int) segment.getMaxY() - 1;

            for (int j = minY; j <= maxY; j++) {
                for (int g = minX; g <= maxX; g++) {
                    if (j == minY || j == maxY || g == minX || g == maxX) {
                        board[j][g].setCellType(CellType.TAIL);
                    }
                }
            }
        }

        Quadrado head = this.gameBoard.getSnake().getHead();
        int minX = (int) head.getMinX();
        int maxX = (int) head.getMaxX() - 1;
        int minY = (int) head.getMinY();
        int maxY = (int) head.getMaxY() - 1;

        for (int j = minY; j <= maxY; j++) {
            for (int g = minX; g <= maxX; g++) {
                if (j == minY || j == maxY || g == minX || g == maxX) {
                    board[j][g].setCellType(CellType.HEAD);
                }
            }
        }
    }

    /**
     * Atualiza as células do tabuleiro que representam os contornos dos obstáculos.
     * As células internas são deixadas vazias, enquanto as bordas são marcadas como contornos.
     */
    public void updateObstacleCells() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.OBSTACLE) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }

        for (int i = 0; i < this.gameBoard.getObstaclesQuantity(); i++) {
            Poligono poligono = this.gameBoard.getListOfObstacles().get(i).getPoligono();

            for (SegmentoReta aresta : poligono.getAresta()) {
                int minX = (int) aresta.getMinX();
                int maxX = (int) aresta.getMaxX();
                int minY = (int) aresta.getMinY();
                int maxY = (int) aresta.getMaxY();

                if (minX != maxX) {
                    maxX = maxX - 1;
                }
                if (minY != maxY) {
                    maxY = maxY - 1;
                }

                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        if (aresta.contemPonto(new Ponto<>(x, y)) && y >= 0 && y < this.rows && x >= 0 && x < this.cols) {
                            board[y][x].setCellType(CellType.OBSTACLE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Atualiza as células do tabuleiro que representam os contornos da comida.
     * As células internas são deixadas vazias, enquanto as bordas são marcadas como contornos.
     */
    public void updateFoodCells() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.FOOD) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }

        Food food = this.gameBoard.getFood();
        int minX = (int) food.getMinX();
        int maxX = (int) food.getMaxX() - 1;
        int minY = (int) food.getMinY();
        int maxY = (int) food.getMaxY() - 1;

        for (int j = minY; j <= maxY; j++) {
            for (int g = minX; g <= maxX; g++) {
                if (j == minY || j == maxY || g == minX || g == maxX) {
                    board[j][g].setCellType(CellType.FOOD);
                }
            }
        }
    }
}
