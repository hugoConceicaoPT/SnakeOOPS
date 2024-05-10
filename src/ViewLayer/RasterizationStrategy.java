package ViewLayer;

import ModelLayer.BoardLayer.Cell;
import ModelLayer.BoardLayer.GameBoard;

/**
 * Classe abstrata que define a estratégia de rasterização para representar o tabuleiro do jogo.
 * Responsabilidade: Fornecer um layout base para as classes derivadas, que implementam estratégias de rasterização específicas.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public abstract class RasterizationStrategy {

    // Matriz que representa o tabuleiro, com células de diferentes tipos
    protected Cell[][] board;
    // O tabuleiro do jogo que contém os elementos a serem representados
    protected GameBoard gameBoard;
    // Número de linhas e colunas no tabuleiro
    protected int rows;
    protected int cols;

    /**
     * Construtor que inicializa a estratégia de rasterização com um tabuleiro de jogo.
     * Prepara uma matriz de células para representar os elementos no tabuleiro.
     * @param gameBoard O tabuleiro do jogo que contém os elementos a serem representados.
     */
    public RasterizationStrategy(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.rows = this.gameBoard.getHeightBoard();
        this.cols = this.gameBoard.getWidthBoard();
        // Inicializa a matriz de células
        this.board = new Cell[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Representa o tabuleiro como uma string. Cada classe derivada deve implementar seu próprio formato.
     * @return Uma string que representa o tabuleiro.
     */
    @Override
    public abstract String toString();

    /**
     * Atualiza as células que representam os obstáculos no tabuleiro.
     * Cada estratégia de rasterização deve implementar esta lógica conforme necessário.
     */
    public abstract void updateObstacleCells();

    /**
     * Atualiza as células que representam a cobra no tabuleiro.
     * Cada estratégia de rasterização deve implementar esta lógica conforme necessário.
     */
    public abstract void updateSnakeCells();

    /**
     * Atualiza as células que representam a comida no tabuleiro.
     * Cada estratégia de rasterização deve implementar esta lógica conforme necessário.
     */
    public abstract void updateFoodCells();

    /**
     * Obtém a matriz que representa o tabuleiro.
     * @return A matriz de células do tabuleiro.
     */
    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Define uma nova matriz para representar o tabuleiro.
     * @param board A nova matriz de células para o tabuleiro.
     */
    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    /**
     * Obtém o tabuleiro de jogo associado a esta estratégia de rasterização.
     * @return O tabuleiro de jogo.
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Define um novo tabuleiro de jogo para esta estratégia de rasterização.
     * @param gameBoard O novo tabuleiro de jogo.
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Obtém o número de linhas no tabuleiro.
     * @return O número de linhas.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Define um novo número de linhas no tabuleiro.
     * @param rows O novo número de linhas.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Obtém o número de colunas no tabuleiro.
     * @return O número de colunas.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Define um novo número de colunas no tabuleiro.
     * @param cols O novo número de colunas.
     */
    public void setCols(int cols) {
        this.cols = cols;
    }
}
