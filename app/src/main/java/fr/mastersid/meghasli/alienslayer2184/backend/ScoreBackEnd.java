package fr.mastersid.meghasli.alienslayer2184.backend;

public interface ScoreBackEnd {
    void saveScore(String name, int score, int pos);
    String[] loadNames();
    Integer[] loadScores();

    public static String DEFAULT_NAME_VALUE = "-----------------";
    public static int DEFAULT_SCORE_VALUE = 0;
}
