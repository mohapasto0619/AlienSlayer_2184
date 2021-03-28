package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class OptionsFragmentModel extends ViewModel {
    OptionsRepository optionsRepository;
    MutableLiveData<Boolean> gameSoundState =  new MutableLiveData<>(DEFAULT_VALUE_SOUND);
    MutableLiveData<Boolean> gameMusicState =  new MutableLiveData<>(DEFAULT_VALUE_MUSIC);
    MutableLiveData<Integer> skinID = new MutableLiveData<>(DEFAULT_VALUE_SKIN_ID);

    public OptionsFragmentModel(OptionsRepository optionsRepository){
        this.optionsRepository = optionsRepository;
        gameSoundState.setValue(optionsRepository.loadSoundState());
        gameMusicState.setValue(optionsRepository.loadMusicState());
        skinID.setValue(optionsRepository.loadSkinID());
    }

    /*public OptionsFragmentModel(SavedStateHandle state){
        gameSoundState = state.getLiveData(SAVED_SOUND_STATE,DEFAULT_VALUE_SOUND);
        gameMusicState = state.getLiveData(SAVED_MUSIC_STATE,DEFAULT_VALUE_MUSIC);
        skinID = state.getLiveData(SAVED_SKIN_ID,DEFAULT_VALUE_SKIN_ID);
    }*/


    public void turnOnGameSound(){
        gameSoundState.setValue(true);
    }

    public void turnOffGameSound(){
        gameSoundState.setValue(false);
    }

    public void turnOnGameMusic(){
        gameMusicState.setValue(true);
    }

    public void turnOffGameMusic(){
        gameMusicState.setValue(false);
    }

    public void changeSkinRight(){
        if(skinID.getValue() <5){
            skinID.setValue(skinID.getValue()+1);
        }
    }

    public void changeSkinLeft(){
        if(skinID.getValue() > 1){
            skinID.setValue(skinID.getValue()-1);
        }
    }

    public void saveOptionsSettings(){
        optionsRepository.saveSettings(gameMusicState.getValue(),gameSoundState.getValue(),skinID.getValue());
    }


    static boolean DEFAULT_VALUE_SOUND = OptionsBackEnd.DEFAULT_SOUND_STATE_VALUE;
    static boolean DEFAULT_VALUE_MUSIC = OptionsBackEnd.DEFAULT_MUSIC_STATE_VALUE;
    static int DEFAULT_VALUE_SKIN_ID = OptionsBackEnd.DEFAULT_SKIN_ID_VALUE;

    /*static String SAVED_SOUND_STATE = "SOUND_KEY";
    static String SAVED_MUSIC_STATE = "MUSIC_KEY";
    static String SAVED_SKIN_ID = "SKIN_KEY";*/
}
