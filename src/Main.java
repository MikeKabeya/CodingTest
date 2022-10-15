import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String result, filename;
        int team;
        String[] teams = new String[0];
        List<String> es = new ArrayList<>();
        List<String> totalGames = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the number from the following options below");
        System.out.println("1. Enter the Values Manually");
        System.out.println("2. File to read from");

        result = sc.nextLine();
        if (result.equals("1"))
        {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Input number of teams:\t");
                team = Integer.parseInt(input.readLine());
                String[] textteams = new String[team];
               for (int i = 0; i < team; i++) {
                   System.out.println("please enter values please");
                   textteams[i] = input.readLine();
                }

                teams =  new String[team];

                System.out.println("-------------------------------------------------");

                int[][] scoreBoard = new int[team][5];
                for (int i = 0; i < team - 1; i++) {
                    for (int j = i + 1; j < team; j++) {
                        result = textteams[i];
                        es = new ArrayList<String>(Arrays.asList(result.split(",")));
                        int a = es.get(i).length();
                        String teamnamea = es.get(i).substring(0 ,a-2);
                        int teamAScore = Integer.parseInt(es.get(i).substring(a-1));
                        int b = es.get(j).length();
                        String teamnameb = es.get(j).substring(0 ,b-2);
                        int teamBScore = Integer.parseInt(es.get(j).substring(b-1));

                        scoreBoard[i][0]++;
                        scoreBoard[j][0]++;
                        if (teamAScore > teamBScore) {
                            scoreBoard[i][1]++;
                            scoreBoard[j][3]++;
                            scoreBoard[i][4] += 3;
                        } else if (teamAScore == teamBScore) {
                            scoreBoard[i][2]++;
                            scoreBoard[j][2]++;
                            scoreBoard[i][4]++;
                            scoreBoard[j][4]++;
                        } else {
                            scoreBoard[i][3]++;
                            scoreBoard[j][1]++;
                            scoreBoard[j][4] += 3;
                        }
                    }
                }

                System.out.println("TEAMS POINTS");
                for (int i = 0; i < team; i++) {
                    System.out.println(teams[i] + scoreBoard[i][4]);
                    es.clear();
                }
            } catch (IOException e) {
                System.out.println("Error");
            }

        }

        if (result.equals("2")) {
            System.out.println("Please enter the pathway to read the file from");
            filename = sc.nextLine();

            try (BufferedReader br = new BufferedReader(new FileReader("Test.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);

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
                team = totalGames.size();
                teams =  new String[team];
                int[][] scoreBoard = new int[team][5];
                for (int i = 0; i < team - 1; i++) {
                    for (int j = i + 1; j < team; j++) {

                        int a = es.get(i).length();
                        String teamnamea = es.get(i).substring(0 ,a-2);
                        teams[i] = teamnamea;
                        int teamAScore = Integer.parseInt(es.get(i).substring(a-1));
                        int b = es.get(j).length();
                        String teamnameb = es.get(j).substring(0 ,b-2);
                        teams[j] = teamnameb;
                        int teamBScore = Integer.parseInt(es.get(j).substring(b-1));

                        scoreBoard[i][0]++;
                        scoreBoard[j][0]++;
                        if (teamAScore > teamBScore) {
                            scoreBoard[i][1]++;
                            scoreBoard[j][3]++;
                            scoreBoard[i][4] += 3;
                        } else if (teamAScore == teamBScore) {
                            scoreBoard[i][2]++;
                            scoreBoard[j][2]++;
                            scoreBoard[i][4]++;
                            scoreBoard[j][4]++;
                        } else {
                            scoreBoard[i][3]++;
                            scoreBoard[j][1]++;
                            scoreBoard[j][4] += 3;
                        }
                    }
                }

                System.out.println("TEAMS POINTS");
                for (int i = 0; i < team; i++) {
                    System.out.println(teams[i] + scoreBoard[i][4]);
                    es.clear();
                }
            }
        }
    }
    public static void removeDuplicates(String[] a)
    {
        LinkedHashSet<String> set
                = new LinkedHashSet<String>();

        // adding elements to LinkedHashSet
        for (int i = 0; i < a.length; i++)
            set.add(a[i]);

        // Print the elements of LinkedHashSet
        System.out.print(set);
    }
}