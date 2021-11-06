package fr.mastersid.meghasli.alienslayer2184.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.mastersid.meghasli.alienslayer2184.backend.ScoreBackEnd;
import fr.mastersid.meghasli.alienslayer2184.repository.ScoreRepository;

public class ScoreFragmentModel extends ViewModel {
    public ScoreRepository scoreRepository;
    public MutableLiveData<String>[] names = new MutableLiveData[11];
    public MutableLiveData<Integer>[] scores = new MutableLiveData[11];
    public String[] namesValues = new String[11];
    public Integer[] scoresValues = new Integer[11];

    public ScoreFragmentModel(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
        for (int i = 0; i<11; i++) {
            names[i] = new MutableLiveData<>(DEFAULT_NAME_VALUE);
        }

        for (int i=0; i<11; i++){
            scores[i] = new MutableLiveData<>(DEFAULT_SCORE_VALUE);
        }
        namesValues = scoreRepository.loadNames();
        scoresValues = scoreRepository.loadScores();

        for (int i =0; i<11; i++){
            names[i].setValue(namesValues[i]);
        }

        for (int i = 0; i<11; i++){
            scores[i].setValue(scoresValues[i]);
        }
    }

    public void saveScore(String name, int score, int pos){
        scoreRepository.saveScore(name, score, pos);
    }


    public static String DEFAULT_NAME_VALUE = ScoreBackEnd.DEFAULT_NAME_VALUE;
    public static int DEFAULT_SCORE_VALUE = ScoreBackEnd.DEFAULT_SCORE_VALUE;

}
