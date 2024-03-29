package fr.mastersid.meghasli.alienslayer2184.factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import fr.mastersid.meghasli.alienslayer2184.backend.ScoreSharedPreferencesBackEnd;
import fr.mastersid.meghasli.alienslayer2184.repository.ScoreRepository;
import fr.mastersid.meghasli.alienslayer2184.viewModels.SoloGameOverFragmentModel;

public class SoloGameOverModelFactory implements ViewModelProvider.Factory {
    Context context;

    public SoloGameOverModelFactory(Context context) {
        this.context = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SoloGameOverFragmentModel.class)) {
            ScoreSharedPreferencesBackEnd backEnd = new ScoreSharedPreferencesBackEnd(context);
            ScoreRepository repository = new ScoreRepository(backEnd);
            return (T) new SoloGameOverFragmentModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}