import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String result, filename;
        int team;
        String[] teams;
        int[] points;
        List<String> es = new ArrayList<>();
        List<String> teamslist = new ArrayList<>();
        List<String> totalGames = new ArrayList<>();
        LinkedHashMap<String, Integer> scoresMap = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the number from the following options below");
        System.out.println("1. Enter the Values Manually");
        System.out.println("2. File to read from");

        result = sc.nextLine();
        if (result.equals("1")) {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Input number of Games:\t");
                int games = Integer.parseInt(input.readLine());
                team = games * 2;
                for (int i = 0; i < games; i++) {
                    System.out.println("please enter values please");
                    result = input.readLine();
                    String[] arrOfStr = result.split(",");
                    Collections.addAll(es, arrOfStr);
                }

                System.out.println("-------------------------------------------------");

                int[][] scoreBoard = new int[team][5];
                for (int i = 0; i < games - 1; i += 2) {
                    int j = i + 1;

                    int a = es.get(i).length();
                    String teamnamea = es.get(i).substring(0, a - 2);
                    int teamAScore = Integer.parseInt(es.get(i).substring(a - 1));
                    int b = es.get(j).length();
                    String teamnameb = es.get(j).substring(0, b - 2);
                    int teamBScore = Integer.parseInt(es.get(j).substring(b - 1));

                    scoreBoard[i][0]++;
                    scoreBoard[j][0]++;
                    if (teamAScore > teamBScore) {
                        scoreBoard[i][1]++;
                        scoreBoard[j][3]++;
                        teamslist.add(teamnamea + " " + String.valueOf(scoreBoard[i][4] += 3));
                        teamslist.add(teamnameb + " " + String.valueOf(scoreBoard[j][4] += 0));
                    } else if (teamAScore == teamBScore) {
                        scoreBoard[i][2]++;
                        scoreBoard[j][2]++;
                        teamslist.add(teamnamea + " " + String.valueOf(scoreBoard[i][4] += 1));
                        teamslist.add(teamnameb + " " + String.valueOf(scoreBoard[j][4] += 1));
                    } else {
                        scoreBoard[i][3]++;
                        scoreBoard[j][1]++;
                        teamslist.add(teamnameb + " " + String.valueOf(scoreBoard[j][4] += 3));
                        teamslist.add(teamnamea + " " + String.valueOf(scoreBoard[i][4] += 0));
                    }
                }
                for (int i = 0; i < team - 1; i++) {
                    int a = teamslist.get(i).length();
                    String teamName = teamslist.get(i).substring(0, a - 2);
                    int amount = Integer.parseInt(teamslist.get(i).substring(a - 1));
                    if (scoresMap.containsKey(teamName)) {
                        scoresMap.put(teamName, scoresMap.get(teamName) + amount);
                    } else {
                        scoresMap.put(teamName, amount);
                    }
                }
                int i = 1;
                LinkedHashMap<String, Integer> sortedscoresMap = new LinkedHashMap<>();

                scoresMap.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(x -> sortedscoresMap.put(x.getKey(), x.getValue()));

                System.out.println("TEAMS POINTS");
                int prev = -10;
                for (Map.Entry<String, Integer> entry : sortedscoresMap.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if(prev == value) {
                        i--;
                    }
                    System.out.println(i + " " + key + "  " + value + "pts");
                    i++;
                    prev = value;
                }
            } catch (IOException e) {
                System.out.println("Error");
            }

        }

        if (result.equals("2")) {
            System.out.println("Please enter the pathway to read the file from");
            filename = sc.nextLine();

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {

                    String[] totals = line.split("/n");
                    Collections.addAll(totalGames, totals);

                    String[] arrOfStr = line.split(",");
                    Collections.addAll(es, arrOfStr);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("-------------------------------------------------");
            if (!es.isEmpty()) {
                team = es.size();
                teams = new String[team];
                points = new int[team];
                int[][] scoreBoard = new int[team][5];
                for (int i = 0; i < team - 1; i += 2) {
                    int j = i + 1;

                    int a = es.get(i).length();
                    String teamnamea = es.get(i).substring(0, a - 2);
                    teams[i] = teamnamea;
                    int teamAScore = Integer.parseInt(es.get(i).substring(a - 1));
                    int b = es.get(j).length();
                    String teamnameb = es.get(j).substring(0, b - 2);
                    teams[j] = teamnameb;
                    int teamBScore = Integer.parseInt(es.get(j).substring(b - 1));

                    scoreBoard[i][0]++;
                    scoreBoard[j][0]++;
                    if (teamAScore > teamBScore) {
                        scoreBoard[i][1]++;
                        scoreBoard[j][3]++;
                        teamslist.add(teamnamea + " " + String.valueOf(scoreBoard[i][4] += 3));
                        teamslist.add(teamnameb + " " + String.valueOf(scoreBoard[j][4] += 0));
                    } else if (teamAScore == teamBScore) {
                        scoreBoard[i][2]++;
                        scoreBoard[j][2]++;
                        teamslist.add(teamnamea + " " + String.valueOf(scoreBoard[i][4] += 1));
                        teamslist.add(teamnameb + " " + String.valueOf(scoreBoard[j][4] += 1));
                    } else {
                        scoreBoard[i][3]++;
                        scoreBoard[j][1]++;
                        teamslist.add(teamnameb + " " + String.valueOf(scoreBoard[j][4] += 3));
                        teamslist.add(teamnamea + " " + String.valueOf(scoreBoard[i][4] += 0));
                    }
                }
                for (int i = 0; i < team; i++) {
                    int a = teamslist.get(i).length();
                    String teamName = teamslist.get(i).substring(0, a - 2);
                    int amount = Integer.parseInt(teamslist.get(i).substring(a - 1));
                    if (scoresMap.containsKey(teamName)) {
                        scoresMap.put(teamName, scoresMap.get(teamName) + amount);
                    } else {
                        scoresMap.put(teamName, amount);
                    }
                }
                int i = 1;
                LinkedHashMap<String, Integer> sortedscoresMap = new LinkedHashMap<>();

                scoresMap.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(x -> sortedscoresMap.put(x.getKey(), x.getValue()));

                System.out.println("TEAMS POINTS");
                int prev = -10;
                for (Map.Entry<String, Integer> entry : sortedscoresMap.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if(prev == value) {
                        i--;
                    }
                    System.out.println(i + " " + key + "  " + value + "pts");
                    i++;
                    prev = value;
                }
            }
        }
    }
}