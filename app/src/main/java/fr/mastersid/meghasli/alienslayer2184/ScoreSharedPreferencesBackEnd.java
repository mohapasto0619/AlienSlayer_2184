package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.content.SharedPreferences;


public class ScoreSharedPreferencesBackEnd implements ScoreBackEnd {
    Context context;
    SharedPreferences sharedPreferences;

    public ScoreSharedPreferencesBackEnd(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }
    @Override
    public void saveScore(String name, int score, int pos){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(pos == 0){
            editor.putString(NAME_1_VALUE,name);
            editor.putInt(SCORE_1_VALUE,score);
        }
        else if(pos == 1){
            editor.putString(NAME_2_VALUE,name);
            editor.putInt(SCORE_2_VALUE,score);
        }
        else if(pos == 2){
            editor.putString(NAME_3_VALUE,name);
            editor.putInt(SCORE_3_VALUE,score);
        }
        else if(pos == 3){
            editor.putString(NAME_4_VALUE,name);
            editor.putInt(SCORE_4_VALUE,score);
        }
        else if(pos == 4){
            editor.putString(NAME_5_VALUE,name);
            editor.putInt(SCORE_5_VALUE,score);
        }
        else if(pos == 5){
            editor.putString(NAME_6_VALUE,name);
            editor.putInt(SCORE_6_VALUE,score);
        }
        else if(pos == 6){
            editor.putString(NAME_7_VALUE,name);
            editor.putInt(SCORE_7_VALUE,score);
        }
        else if(pos == 7){
            editor.putString(NAME_8_VALUE,name);
            editor.putInt(SCORE_8_VALUE,score);
        }
        else if(pos == 8){
            editor.putString(NAME_9_VALUE,name);
            editor.putInt(SCORE_9_VALUE,score);
        }
        else if(pos == 9){
            editor.putString(NAME_10_VALUE,name);
            editor.putInt(SCORE_10_VALUE,score);
        }
        else if(pos == 10){
            editor.putString(NAME_11_VALUE,name);
            editor.putInt(SCORE_11_VALUE,score);
        }

        editor.apply();
    }
    @Override
    public String[] loadNames(){
        String[] names = new String[11];
        names[0] = sharedPreferences.getString(NAME_1_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[1] = sharedPreferences.getString(NAME_2_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[2] = sharedPreferences.getString(NAME_3_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[3] = sharedPreferences.getString(NAME_4_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[4] = sharedPreferences.getString(NAME_5_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[5] = sharedPreferences.getString(NAME_6_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[6] = sharedPreferences.getString(NAME_7_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[7] = sharedPreferences.getString(NAME_8_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[8] = sharedPreferences.getString(NAME_9_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[9] = sharedPreferences.getString(NAME_10_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);
        names[10] = sharedPreferences.getString(NAME_11_VALUE,ScoreBackEnd.DEFAULT_NAME_VALUE);

        return names;
    }
    @Override
    public Integer[] loadScores(){
        Integer[] scores = new Integer[11];
        scores[0] = sharedPreferences.getInt(SCORE_1_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[1] = sharedPreferences.getInt(SCORE_2_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[2] = sharedPreferences.getInt(SCORE_3_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[3] = sharedPreferences.getInt(SCORE_4_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[4] = sharedPreferences.getInt(SCORE_5_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[5] = sharedPreferences.getInt(SCORE_6_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[6] = sharedPreferences.getInt(SCORE_7_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[7] = sharedPreferences.getInt(SCORE_8_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[8] = sharedPreferences.getInt(SCORE_9_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[9] = sharedPreferences.getInt(SCORE_10_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        scores[10] = sharedPreferences.getInt(SCORE_11_VALUE, ScoreBackEnd.DEFAULT_SCORE_VALUE);
        return scores;
    }

    static String FILE_NAME = "scores_shared_preferences_file";
    static String NAME_1_VALUE = "name_1_value";
    static String NAME_2_VALUE = "name_2_value";
    static String NAME_3_VALUE = "name_3_value";
    static String NAME_4_VALUE = "name_4_value";
    static String NAME_5_VALUE = "name_5_value";
    static String NAME_6_VALUE = "name_6_value";
    static String NAME_7_VALUE = "name_7_value";
    static String NAME_8_VALUE = "name_8_value";
    static String NAME_9_VALUE = "name_9_value";
    static String NAME_10_VALUE = "name_10_value";
    static String NAME_11_VALUE = "name_11_value";
    static String SCORE_1_VALUE = "score_1_value";
    static String SCORE_2_VALUE = "score_2_value";
    static String SCORE_3_VALUE = "score_3_value";
    static String SCORE_4_VALUE = "score_4_value";
    static String SCORE_5_VALUE = "score_5_value";
    static String SCORE_6_VALUE = "score_6_value";
    static String SCORE_7_VALUE = "score_7_value";
    static String SCORE_8_VALUE = "score_8_value";
    static String SCORE_9_VALUE = "score_9_value";
    static String SCORE_10_VALUE = "score_10_value";
    static String SCORE_11_VALUE = "score_11_value";
}
