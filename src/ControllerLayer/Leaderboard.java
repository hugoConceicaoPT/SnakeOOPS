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

    public String generateLeaderboard() {
        String result = "";
        try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
            List<Player> topPlayers =  lines.filter(line -> !line.trim().isEmpty())
                .map(line -> {
                    String[] parts = line.split(" ");
                    String name = parts[1];
                    int points = Integer.parseInt(parts[2]);
                    return new Player(name, new Score(points,0));
                })
                .sorted(Comparator.comparing(player -> player.getScore().getPoints(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

            for(int i = 0; i < topPlayers.size(); i++) {
                result += i+1 + "º " + topPlayers.get(i).getName() + " " + topPlayers.get(i).getScore().getPoints() + " " + "Pontos"+ "\n"; 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateLeaderboard(Player newPlayer) {
        try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
            List<Player> topPlayers = lines.map(line -> {
                String[] parts = line.split(" ");
                String name = parts[1];
                int points = Integer.parseInt(parts[2]);
                return new Player(name, new Score(points,0));
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

            if(!playerExists)
                topPlayers.add(newPlayer);
            topPlayers.sort(Comparator.comparing(player -> player.getScore().getPoints(), Comparator.reverseOrder()));
            
            List<String> formattedScores = topPlayers.stream()
                .map(topPlayer -> (topPlayers.indexOf(topPlayer) + 1) + "º " + topPlayer.getName() + " " + topPlayer.getScore().getPoints() + " " + "Pontos")
                .collect(Collectors.toList());

            Files.write(Paths.get(this.filePath),formattedScores);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
