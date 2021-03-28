package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.content.SharedPreferences;

public class OptionsSharedPreferencesBackEnd implements OptionsBackEnd {

    Context context;
    SharedPreferences sharedPreferences;
    public  OptionsSharedPreferencesBackEnd(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public void saveSettings(boolean musicStateFav, boolean soundStateFav, int skinIDFav){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MUSIC_FAV_VALUE,musicStateFav);
        editor.putBoolean(SOUND_FAV_VALUE,soundStateFav);
        editor.putInt(SKIN_ID_FAV_VALUE,skinIDFav);
        editor.apply();
        //editor.commit();
        }

     @Override
    public boolean loadMusicState(){
         return sharedPreferences.getBoolean(MUSIC_FAV_VALUE, OptionsBackEnd.DEFAULT_MUSIC_STATE_VALUE);
     }

    @Override
     public boolean loadSoundState(){
         return sharedPreferences.getBoolean(SOUND_FAV_VALUE, OptionsBackEnd.DEFAULT_SOUND_STATE_VALUE);
     }

    @Override
    public int loadSkinID(){
        return sharedPreferences.getInt(SKIN_ID_FAV_VALUE, OptionsBackEnd.DEFAULT_SKIN_ID_VALUE);
    }


    static String FILE_NAME = "options_shared_preferences_file";
    static String MUSIC_FAV_VALUE = "music_fav_value";
    static String SOUND_FAV_VALUE = "sound_fav_value";
    static String SKIN_ID_FAV_VALUE = "skin_id_fav_value";
}
