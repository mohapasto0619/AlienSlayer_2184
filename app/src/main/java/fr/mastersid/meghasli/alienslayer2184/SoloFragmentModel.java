package fr.mastersid.meghasli.alienslayer2184;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SoloFragmentModel extends ViewModel {
    OptionsRepository optionsRepository;
    MutableLiveData<Boolean> soloSoundState = new MutableLiveData<>(DEFAULT_VALUE_SOUND);
    MutableLiveData<Boolean> soloMusicState = new MutableLiveData<>(DEFAULT_VALUE_MUSIC);
    MutableLiveData<Integer> soloSkinID = new MutableLiveData<>(DEFAULT_VALUE_SKIN_ID);

    public SoloFragmentModel(OptionsRepository optionsRepository){
        this.optionsRepository = optionsRepository;
        soloSoundState.setValue(optionsRepository.loadSoundState());
        soloMusicState.setValue(optionsRepository.loadMusicState());
        soloSkinID.setValue(optionsRepository.loadSkinID());
    }


   static boolean DEFAULT_VALUE_SOUND = OptionsRepository.DEFAULT_SOUND_STATE_VALUE;
   static boolean DEFAULT_VALUE_MUSIC = OptionsRepository.DEFAULT_MUSIC_STATE_VALUE;
   static int DEFAULT_VALUE_SKIN_ID = OptionsRepository.DEFAULT_SKIN_ID_VALUE;
}
