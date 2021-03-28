package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class OptionsFragmentModel extends ViewModel {

    MutableLiveData<Boolean> gameSoundState =  new MutableLiveData<>(DEFAULT_VALUE_SOUND);
    MutableLiveData<Boolean> gameMusicState =  new MutableLiveData<>(DEFAULT_VALUE_MUSIC);
    MutableLiveData<Integer> skinID = new MutableLiveData<>(DEFAULT_VALUE_SKIN_ID);
    Context context;

    public OptionsFragmentModel(Context context){
        this.context = context;
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
        int next = skinID.getValue() + 1;
        if(next <= 5) {
            skinID.setValue(next);
        }
    }

    public void changeSkinLeft(){
        int next = skinID.getValue() - 1;
        if(next <= 1){
            skinID.setValue(next);
        }
    }


    static boolean DEFAULT_VALUE_SOUND = true;
    static boolean DEFAULT_VALUE_MUSIC = true;
    static int DEFAULT_VALUE_SKIN_ID = 1;

    /*static String SAVED_SOUND_STATE = "SOUND_KEY";
    static String SAVED_MUSIC_STATE = "MUSIC_KEY";
    static String SAVED_SKIN_ID = "SKIN_KEY";*/
}
