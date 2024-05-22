package ViewLayer;

import ControllerLayer.ML;

import javax.swing.*;
import java.awt.Graphics;

/**
 * Classe abstrata que define a estrutura básica de uma cena no jogo.
 * Responsabilidade: Definir os métodos abstratos para atualização, desenho e obtenção do painel da cena.
 * @version 1.0 22/05/2024
 */
public abstract class Scene {

    /**
     * Atualiza a cena com base nos eventos de mouse.
     * Cada cena específica deve implementar esta lógica conforme necessário.
     * @param mouseListener O listener de mouse que captura os eventos.
     */
    public abstract void update(ML mouseListener);

    /**
     * Desenha a cena no contexto gráfico fornecido.
     * Cada cena específica deve implementar esta lógica conforme necessário.
     * @param g O contexto gráfico a ser usado para desenhar.
     */
    public abstract void draw(Graphics g);

    /**
     * Obtém o painel associado à cena.
     * Cada cena específica deve fornecer seu próprio painel.
     * @return O painel da cena.
     */
    public abstract JPanel getPanel();
}
