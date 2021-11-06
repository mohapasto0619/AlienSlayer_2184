package fr.mastersid.meghasli.alienslayer2184.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.mastersid.meghasli.alienslayer2184.repository.OptionsRepository;

public class SoloFragmentModel extends ViewModel {
    OptionsRepository optionsRepository;
    public MutableLiveData<Boolean> soloSoundState = new MutableLiveData<>(DEFAULT_VALUE_SOUND);
    public MutableLiveData<Boolean> soloMusicState = new MutableLiveData<>(DEFAULT_VALUE_MUSIC);
    public MutableLiveData<Integer> soloSkinID = new MutableLiveData<>(DEFAULT_VALUE_SKIN_ID);
    public MutableLiveData<Boolean> isItGameOver = new MutableLiveData<>(false);

    public SoloFragmentModel(OptionsRepository optionsRepository){
        this.optionsRepository = optionsRepository;
        soloSoundState.setValue(optionsRepository.loadSoundState());
        soloMusicState.setValue(optionsRepository.loadMusicState());
        soloSkinID.setValue(optionsRepository.loadSkinID());
    }


   public static boolean DEFAULT_VALUE_SOUND = OptionsRepository.DEFAULT_SOUND_STATE_VALUE;
   public static boolean DEFAULT_VALUE_MUSIC = OptionsRepository.DEFAULT_MUSIC_STATE_VALUE;
   public static int DEFAULT_VALUE_SKIN_ID = OptionsRepository.DEFAULT_SKIN_ID_VALUE;
}
