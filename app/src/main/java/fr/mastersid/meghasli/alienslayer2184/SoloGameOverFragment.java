package fr.mastersid.meghasli.alienslayer2184;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentSoloGameOverBinding;


public class SoloGameOverFragment extends Fragment {
    private FragmentSoloGameOverBinding binding;


    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentSoloGameOverBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SoloGameOverFragmentModel soloGameOverFragmentModel = new SoloGameOverFragmentModel();
        soloGameOverFragmentModel.playerName.setValue(SoloGameOverFragmentArgs.fromBundle(getArguments()).getPlayerName());
        soloGameOverFragmentModel.playerScore.setValue(SoloGameOverFragmentArgs.fromBundle(getArguments()).getPlayerScore());

        binding.nameText.setText(soloGameOverFragmentModel.playerName.getValue());
        binding.scoreValue.setText(soloGameOverFragmentModel.playerScore.getValue().toString());
    }
}
