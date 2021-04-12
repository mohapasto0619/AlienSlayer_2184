package fr.mastersid.meghasli.alienslayer2184;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SoloGameOverFragmentModel extends ViewModel {
    MutableLiveData<String> playerName = new MutableLiveData<>(DEFAULT_PLAYER_NAME);
    MutableLiveData<Integer> playerScore = new MutableLiveData<>(DEFAULT_PLAYER_SCORE);
    Integer[] scoreBoard;
    String[] namesBoard;
    ScoreRepository scoreRepository;

    public SoloGameOverFragmentModel(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
        scoreBoard = this.scoreRepository.loadScores();
        namesBoard = this.scoreRepository.loadNames();
    }

    public void updateBoard(){
        String[] nameGenericBoard = scoreRepository.loadNames();
        Integer[] scoreGenericBoard = scoreRepository.loadScores();
        for (int i=0; i<11; i++){
            if (playerScore.getValue() > scoreBoard[i]){
                for (int j=0; j<11-i-1; j++) {
                    namesBoard[i + j + 1] = nameGenericBoard[i + j];
                    scoreBoard[i + j + 1] = scoreGenericBoard[(i + j)];
                    scoreRepository.saveScore(namesBoard[i + j + 1], scoreBoard[i + j + 1], i + j + 1);
                }

                scoreRepository.saveScore(playerName.getValue(),playerScore.getValue(),i);
                break;
            }
        }
    }

    static String DEFAULT_PLAYER_NAME = "DEFAULT";
    static Integer DEFAULT_PLAYER_SCORE = 0;

}
