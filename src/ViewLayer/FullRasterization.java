package ViewLayer;

import ModelLayer.BoardLayer.Cell;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

/**
 * Classe que implementa a estratégia de rasterização completa dos elementos no tabuleiro.
 * Responsabilidade: Representar de forma completa todos os elementos no tabuleiro, incluindo a cobra, obstáculos e comida.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class FullRasterization extends RasterizationStrategy {

    /**
     * Construtor que inicializa a estratégia de rasterização completa com um tabuleiro de jogo.
     * @param gameBoard O tabuleiro do jogo que contém os elementos a serem representados.
     */
    public FullRasterization(GameBoard gameBoard) {
        super(gameBoard);
    }

    /**
     * Representa o tabuleiro como uma string, incluindo todos os elementos rasterizados.
     * @return Uma string que representa o tabuleiro.
     */
    @Override
    public String toString() {
        String result = "";
        for(int i = this.rows - 1; i >= 0; i--) {
            for(int j = 0; j < this.cols; j++) {
                result += board[i][j].toString() + " ";
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Atualiza as células do tabuleiro que representam o corpo da cobra.
     * Diferente da rasterização por contorno, esta abordagem preenche todas as células ocupadas pela cobra.
     */
    public void updateSnakeCells() {
        // Limpa as células que atualmente representam a cobra
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.HEAD || board[i][j].getCellType() == CellType.TAIL) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }

        // Preenche completamente as células do corpo da cobra
        for (int i = 1; i < this.gameBoard.getSnake().getBody().size(); i++) {
            for(int w = (int) this.gameBoard.getSnake().getBody().get(i).getMinY(); w < (int) this.gameBoard.getSnake().getBody().get(i).getMaxY(); w++) {
                for(int j = (int) this.gameBoard.getSnake().getBody().get(i).getMinX() ; j < (int) this.gameBoard.getSnake().getBody().get(i).getMaxX(); j++) {
                    board[w][j].setCellType(CellType.TAIL);
                }
            }
        }

        // Preenche completamente as células da cabeça da cobra
        Quadrado head = this.gameBoard.getSnake().getHead();
        for(int w = (int) head.getMinY(); w < (int) head.getMaxY(); w++) {
            for(int j = (int) head.getMinX(); j < (int) head.getMaxX(); j++) {
                board[w][j].setCellType(CellType.HEAD);
            }
        }
    }

    /**
     * Atualiza as células do tabuleiro que representam obstáculos.
     * Cada obstáculo é preenchido completamente.
     */
    public void updateObstacleCells() {
        // Limpa as células que atualmente representam os obstáculos
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.OBSTACLE) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }   

        // Preenche as células que contêm obstáculos
        for(int i = 0; i < this.gameBoard.getListOfObstacles().size(); i++) {
            for(int row = 0; row < this.rows; row++) {
                for(int col = 0; col < this.cols; col++) {
                    if(this.gameBoard.getListOfObstacles().get(i).getPoligono().contemPonto(new Ponto<Double>(col + 0.5, row + 0.5))) {
                        board[row][col].setCellType(CellType.OBSTACLE);
                    }
                }
            }
        }
    }

    /**
     * Atualiza as células do tabuleiro que representam a comida.
     * Garante que a comida esteja colocada em uma posição que não coincida com outros elementos.
     */
    public void updateFoodCells() {
        boolean isFoodNotReseted = false;
        while (!isFoodNotReseted) {
            // Limpa as células que atualmente representam a comida
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    if (board[i][j].getCellType() == CellType.FOOD) {
                        board[i][j].setCellType(CellType.EMPTY);
                    }
                }
            }

            // Tenta preencher as células da comida, mas verifica colisões com outros elementos
            for (int i = this.gameBoard.getFood().getMinY(); i < this.gameBoard.getFood().getMaxY(); i++) {
                for (int j = this.gameBoard.getFood().getMinX(); j < this.gameBoard.getFood().getMaxX(); j++) {
                    if (board[i][j].getCellType() != CellType.EMPTY) {
                        // Se houver colisão, limpa novamente e gera nova comida
                        for (int w = 0; w < this.rows; w++) {
                            for (int z = 0; z < this.cols; z++) {
                                if (board[w][z].getCellType() == CellType.FOOD) {
                                    board[w][z].setCellType(CellType.EMPTY);
                                }
                            }
                        }
                        this.gameBoard.removeFood();
                        this.gameBoard.generateFood();
                        isFoodNotReseted = false;
                        break;
                    } else {
                        board[i][j].setCellType(CellType.FOOD);
                        isFoodNotReseted = true;
                    }
                }
                if (!isFoodNotReseted) {
                    break;
                }
            }
        }
        // Exibe as informações da nova comida no console
        System.out.println(this.gameBoard.getFood().toString());
    }

    

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
