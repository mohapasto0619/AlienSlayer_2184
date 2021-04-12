package fr.mastersid.meghasli.alienslayer2184;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
        SoloGameOverFragmentModel soloGameOverFragmentModel;
        SoloGameOverModelFactory soloGameOverModelFactory = new SoloGameOverModelFactory(getActivity());
        soloGameOverFragmentModel = new ViewModelProvider(this,soloGameOverModelFactory).get(SoloGameOverFragmentModel.class);
        soloGameOverFragmentModel.playerName.setValue(SoloGameOverFragmentArgs.fromBundle(getArguments()).getPlayerName());
        soloGameOverFragmentModel.playerScore.setValue(SoloGameOverFragmentArgs.fromBundle(getArguments()).getPlayerScore());

        binding.nameText.setText(getString(R.string.board_names,soloGameOverFragmentModel.playerName.getValue()));
        binding.scoreValue.setText(getString(R.string.board_scores,soloGameOverFragmentModel.playerScore.getValue()));
        soloGameOverFragmentModel.updateBoard();

        binding.backMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_soloGameOverFragment_to_menuFragment);
            }
        });
    }


}
