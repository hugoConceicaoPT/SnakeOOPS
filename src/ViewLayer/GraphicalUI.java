package ViewLayer;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface gráfica para o jogo, utilizando a biblioteca Swing.
 * Responsabilidade: Exibir o tabuleiro do jogo e o placar, além de capturar os eventos do teclado.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class GraphicalUI extends JFrame implements UI {

    /**
     * Construtor que inicializa a interface gráfica.
     * Pode ser configurado posteriormente para definir a aparência do jogo.
     */
    public GraphicalUI() {
    }

    /**
     * Exibe o placar e o tabuleiro do jogo.
     * Esta função pode ser aprimorada para desenhar os elementos do tabuleiro e o score usando gráficos.
     * @param score O placar atual do jogo.
     * @param gameBoard O tabuleiro do jogo, contendo os elementos a serem desenhados.
     */
    public void display(Score score, GameBoard gameBoard) {
        
    }

    /**
     * Adiciona um listener para capturar eventos de teclado durante o jogo.
     * @param listener O listener de teclado a ser registrado.
     */
    @Override
    public void addKeyListener(KeyListener listener) {
        super.addKeyListener(listener);
    }

    /**
     * Fecha a janela do jogo.
     * Pode ser melhorado para limpar recursos ou exibir uma mensagem final antes de sair.
     */
    public void close() {
        
    }
}


