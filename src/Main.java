import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int count = 1;
    public static int position = -10;
    public static void main(String[] args) {
        String result, filename;
        List<String> teamsAndGameScore = new ArrayList<>();
        List<String> teamslist = new ArrayList<>();
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

                for (int i = 0; i < games; i++) {
                    System.out.println("please enter values please");
                    result = input.readLine();
                    String[] arrOfStr = result.split(",");
                    Collections.addAll(teamsAndGameScore, arrOfStr);
                }
                calculations(teamsAndGameScore,teamslist);
                outPut(teamsAndGameScore,scoresMap,teamslist);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

        if (result.equals("2")) {
            System.out.println("Please enter the pathway to read the file from");
            filename = sc.nextLine();

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] arrOfStr = line.split(",");
                    Collections.addAll(teamsAndGameScore, arrOfStr);
                }
                calculations(teamsAndGameScore,teamslist);
                outPut(teamsAndGameScore,scoresMap,teamslist);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void calculations(List<String> teamsAndGameScore,List<String> teamsList) {
        System.out.println("-------------------------------------------------");
        int num = teamsAndGameScore.size();
        if (!teamsAndGameScore.isEmpty()) {
            int[][] scoreBoard = new int[num][5];
            for (int i = 0; i < num - 1; i += 2) {
                int j = i + 1;

                int a = teamsAndGameScore.get(i).length();
                String teamNamea = teamsAndGameScore.get(i).substring(0, a - 2);
                int teamAScore = Integer.parseInt(teamsAndGameScore.get(i).substring(a - 1));
                int b = teamsAndGameScore.get(j).length();
                String teamNameb = teamsAndGameScore.get(j).substring(0, b - 2);
                int teamBScore = Integer.parseInt(teamsAndGameScore.get(j).substring(b - 1));

                scoreBoard[i][0]++;
                scoreBoard[j][0]++;
                if (teamAScore > teamBScore) {
                    teamsList.add(teamNamea + " " + (scoreBoard[i][4] += 3));
                    teamsList.add(teamNameb + " " + (scoreBoard[j][4] += 0));
                } else if (teamAScore == teamBScore) {
                    teamsList.add(teamNamea + " " + (scoreBoard[i][4] += 1));
                    teamsList.add(teamNameb + " " + (scoreBoard[j][4] += 1));
                } else {
                    teamsList.add(teamNameb + " " + (scoreBoard[j][4] += 3));
                    teamsList.add(teamNamea + " " + (scoreBoard[i][4] += 0));
                }
            }
        }
    }

    public static void outPut(List<String> teamsAndGameScore,LinkedHashMap<String, Integer> scoresMap,List<String> teamsList) {
        for (int i = 0; i < teamsAndGameScore.size(); i++) {
            int a = teamsList.get(i).length();
            String teamName = teamsList.get(i).substring(0, a - 2);
            int amount = Integer.parseInt(teamsList.get(i).substring(a - 1));
            if (scoresMap.containsKey(teamName)) {
                scoresMap.put(teamName, scoresMap.get(teamName) + amount);
            } else {
                scoresMap.put(teamName, amount);
            }
        }
        int i = count;
        LinkedHashMap<String, Integer> sortedScoresMap = new LinkedHashMap<>();

        scoresMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedScoresMap.put(x.getKey(), x.getValue()));

        System.out.println("TEAMS POINTS");
        int prev = position;
        for (Map.Entry<String, Integer> entry : sortedScoresMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (prev == value) {
                i--;
            }
            System.out.println(i + " " + key + "  " + value + "pts");
            i++;
            prev = value;
        }
    }
}
