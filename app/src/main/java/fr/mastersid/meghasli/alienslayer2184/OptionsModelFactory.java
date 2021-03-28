package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class OptionsModelFactory implements ViewModelProvider.Factory {
    Context context;


    public OptionsModelFactory(Context context){
        this.context = context;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(OptionsFragmentModel.class)) {
            OptionsSharedPreferencesBackEnd backEnd = new OptionsSharedPreferencesBackEnd(context) ;
            OptionsRepository repository = new OptionsRepository(backEnd);
            return (T) new OptionsFragmentModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
