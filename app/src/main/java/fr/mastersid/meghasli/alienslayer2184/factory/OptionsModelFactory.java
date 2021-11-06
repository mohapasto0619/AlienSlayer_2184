package fr.mastersid.meghasli.alienslayer2184.factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import fr.mastersid.meghasli.alienslayer2184.backend.OptionsSharedPreferencesBackEnd;
import fr.mastersid.meghasli.alienslayer2184.repository.OptionsRepository;
import fr.mastersid.meghasli.alienslayer2184.viewModels.OptionsFragmentModel;

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
