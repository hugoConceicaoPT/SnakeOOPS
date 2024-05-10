package ControllerLayer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ModelLayer.BoardLayer.Player;
import ModelLayer.BoardLayer.Score;

/**
 * Classe responsável pela gestão da classificação dos jogadores.
 * Responsabilidade: Gerenciar e atualizar o arquivo de classificação dos jogadores com base em suas pontuações.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv Deve manter um arquivo consistente de ranking de jogadores.
 */
public class Leaderboard {
    private String filePath; // Caminho para o arquivo de ranking.
    
    /**
     * Construtor padrão que inicializa o caminho do arquivo de ranking.
     */
    public Leaderboard() {
        this.filePath = "src\\ranking.txt"; // Define o caminho padrão para o arquivo de ranking.
    }

    /**
     * Gera a tabela de classificação com os jogadores e suas pontuações ordenadas do maior para o menor.
     * @return Uma string formatada com a classificação dos jogadores.
     */
    public String generateLeaderboard() {
        String result = "";
        try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
            List<Player> topPlayers = lines.filter(line -> !line.trim().isEmpty())
                .map(line -> {
                    String[] parts = line.split(" ");
                    String name = parts[1];
                    int points = Integer.parseInt(parts[2]);
                    return new Player(name, new Score(points, 0));
                })
                .sorted(Comparator.comparing(player -> player.getScore().getPoints(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

            for (int i = 0; i < topPlayers.size(); i++) {
                result += (i + 1) + "º " + topPlayers.get(i).getName() + " " + topPlayers.get(i).getScore().getPoints() + " Pontos\n";
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log and handle errors appropriately.
        }
        return result;
    }

    /**
     * Atualiza a classificação ao adicionar um novo jogador ou atualizar a pontuação de um jogador existente.
     * @param newPlayer O jogador com pontuação atualizada a ser adicionado ou atualizado na classificação.
     */
    public void updateLeaderboard(Player newPlayer) {
        try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
            List<Player> topPlayers = lines.map(line -> {
                String[] parts = line.split(" ");
                String name = parts[1];
                int points = Integer.parseInt(parts[2]);
                return new Player(name, new Score(points, 0));
            })
            .sorted(Comparator.comparing(player -> player.getScore().getPoints(), Comparator.reverseOrder()))
            .collect(Collectors.toList());

            boolean playerExists = false;
            for (Player player : topPlayers) {
                if (player.getName().equals(newPlayer.getName())) {
                    if (newPlayer.getScore().getPoints() > player.getScore().getPoints()) {
                        player.setScore(newPlayer.getScore());
                    }
                    playerExists = true;
                    break;
                }
            }

            if (!playerExists)
                topPlayers.add(newPlayer);

            // Re-sort and save the updated list
            topPlayers.sort(Comparator.comparing(player -> player.getScore().getPoints(), Comparator.reverseOrder()));
            List<String> formattedScores = topPlayers.stream()
                .map(topPlayer -> (topPlayers.indexOf(topPlayer) + 1) + "º " + topPlayer.getName() + " " + topPlayer.getScore().getPoints() + " Pontos")
                .collect(Collectors.toList());

            Files.write(Paths.get(this.filePath), formattedScores);
        } catch (Exception e) {
            e.printStackTrace(); // Log and handle errors appropriately.
        }
    }
}
