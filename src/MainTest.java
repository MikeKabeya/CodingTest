import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;


class MainTest {

    @org.junit.jupiter.api.Test
    void calculations() {
        List<String> teamsAndGameScore = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split(",");
                Collections.addAll(teamsAndGameScore, arrOfStr);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Main.calculations(teamsAndGameScore);
    }

    @org.junit.jupiter.api.Test
    void outPut() {
        List<String> teamsAndGameScore = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split(",");
                Collections.addAll(teamsAndGameScore, arrOfStr);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        LinkedHashMap<String, Integer> scoresMap = new LinkedHashMap<>();
        Main.calculations(teamsAndGameScore);
        Main.outPut(teamsAndGameScore, scoresMap);
    }
}