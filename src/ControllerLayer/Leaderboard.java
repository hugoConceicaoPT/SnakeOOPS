package ControllerLayer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ModelLayer.SnakeLayer.Player;
import ModelLayer.SnakeLayer.Score;
public class Leaderboard {
    private String filePath;
    
    public Leaderboard() {
        this.filePath = "src\\ranking.txt";
    }

   /* public String generateLeaderboard() {
        String result = "";
        try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
            List<Player> topPlayers = lines.map(line -> {
                String[] parts = line.split(" ");
                String name = parts[1];
                int points = Integer.parseInt(parts[3]);
                return new Player(name, new Score(points,0));
            })
            .sorted(Comparator.comparing(Player player) -> player.getScore().getPoints()).reversed())
            .collect(Collectors.toList());

            for(int i = 0; i < topScores.size(); i++) {
                result += i+1 + "º " + topScores.get(i).getPoints() + " " + "Pontos"+ "\n"; 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
*/

    public void updateLeaderboard(Score newScore) {
        try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
            List<Score> topScores = lines.map(line -> {
                String[] parts = line.split(" ");
                int points = Integer.parseInt(parts[1]);
                return new Score(points, 0);
            })
            .sorted(Comparator.comparing(Score::getPoints).reversed())
            .collect(Collectors.toList());

            topScores.add(newScore);
            topScores.sort(Comparator.comparing(Score::getPoints).reversed());

            List<String> formattedScores = topScores.stream()
                .map(topScore -> (topScores.indexOf(topScore) + 1) + "º " + topScore.getPoints() + " " + "Pontos")
                .collect(Collectors.toList());

            Files.write(Paths.get(this.filePath),formattedScores);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
