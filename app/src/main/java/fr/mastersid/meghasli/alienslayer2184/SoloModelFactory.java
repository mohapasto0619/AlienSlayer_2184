package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SoloModelFactory implements ViewModelProvider.Factory{
    Context context;

    public SoloModelFactory(Context context){
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SoloFragmentModel.class)) {
            OptionsSharedPreferencesBackEnd backEnd = new OptionsSharedPreferencesBackEnd(context);
            OptionsRepository repository = new OptionsRepository(backEnd);
            return (T) new SoloFragmentModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
