package fr.mastersid.meghasli.alienslayer2184;

public interface ScoreBackEnd {
    void saveScore(String name, int score, int pos);
    String[] loadNames();
    Integer[] loadScores();

    static String DEFAULT_NAME_VALUE = "--------";
    static int DEFAULT_SCORE_VALUE = 0;
}
