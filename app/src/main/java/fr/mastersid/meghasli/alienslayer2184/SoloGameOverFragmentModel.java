package fr.mastersid.meghasli.alienslayer2184;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SoloGameOverFragmentModel extends ViewModel {
    MutableLiveData<String> playerName = new MutableLiveData<>(DEFAULT_PLAYER_NAME);
    MutableLiveData<Integer> playerScore = new MutableLiveData<>(DEFAULT_PLAYER_SCORE);


    static String DEFAULT_PLAYER_NAME = "DEFAULT";
    static Integer DEFAULT_PLAYER_SCORE = 0;

}
