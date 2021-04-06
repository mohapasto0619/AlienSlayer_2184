package fr.mastersid.meghasli.alienslayer2184;

public class ScoreRepository {
    ScoreBackEnd scoreBackEnd;
    public ScoreRepository(ScoreBackEnd scoreBackEnd){
        this.scoreBackEnd = scoreBackEnd;
    }

    public void saveScore(String name, int score, int pos){
        scoreBackEnd.saveScore(name,score,pos);
    }

    public String[] loadNames(){
        return scoreBackEnd.loadNames();
    }

    public Integer[] loadScores(){
        return scoreBackEnd.loadScores();
    }

    static String DEFAULT_NAME_VALUE = ScoreBackEnd.DEFAULT_NAME_VALUE;
    static int DEFAULT_SCORE_VALUE = ScoreBackEnd.DEFAULT_SCORE_VALUE;

}
