package fr.mastersid.meghasli.alienslayer2184.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import fr.mastersid.meghasli.alienslayer2184.sound.Music;
import fr.mastersid.meghasli.alienslayer2184.R;
import fr.mastersid.meghasli.alienslayer2184.viewModels.ScoreFragmentModel;
import fr.mastersid.meghasli.alienslayer2184.factory.ScoreModelFactory;
import fr.mastersid.meghasli.alienslayer2184.databinding.FragmentScoreBinding;

public class ScoreFragment extends Fragment {

    private FragmentScoreBinding binding;
    private Music music;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentScoreBinding.inflate(inflater, container, false);
        music = new Music(getActivity());
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
        ScoreFragmentModel scoreFragmentModel;
        ScoreModelFactory scoreModelFactory = new ScoreModelFactory(getActivity());
        scoreFragmentModel = new ViewModelProvider(this, scoreModelFactory).get(ScoreFragmentModel.class);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_scoreFragment_to_menuFragment);
            }
        });

        scoreFragmentModel.names[0].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name1.getText()){
                binding.name1.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[1].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name2.getText()){
                binding.name2.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[2].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name3.getText()){
                binding.name3.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[3].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name4.getText()){
                binding.name4.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[4].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name5.getText()){
                binding.name5.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[5].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name6.getText()){
                binding.name6.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[6].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name7.getText()){
                binding.name7.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[7].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name8.getText()){
                binding.name8.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[8].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name9.getText()){
                binding.name9.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[9].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name10.getText()){
                binding.name10.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.names[10].observe(getViewLifecycleOwner(), value ->{
            if(value != binding.name11.getText()){
                binding.name11.setText(getString(R.string.board_names,value));
            }
        });

        scoreFragmentModel.scores[0].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score1.getText().toString())){
                binding.score1.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[1].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score2.getText().toString())){
                binding.score2.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[2].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score3.getText().toString())){
                binding.score3.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[3].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score4.getText().toString())){
                binding.score4.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[4].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score5.getText().toString())){
                binding.score5.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[5].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score6.getText().toString())){
                binding.score6.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[6].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score7.getText().toString())){
                binding.score7.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[7].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score8.getText().toString())){
                binding.score8.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[8].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score9.getText().toString())){
                binding.score9.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[9].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score10.getText().toString())){
                binding.score10.setText(getString(R.string.board_scores,value));
            }
        });

        scoreFragmentModel.scores[10].observe(getViewLifecycleOwner(), value ->{
            if(value != Integer.parseInt(binding.score11.getText().toString())){
                binding.score11.setText(getString(R.string.board_scores,value));
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        music.playScoreAmb();
    }
    @Override
    public void onPause() {
        super.onPause();
        music.stopScoreAmb();
    }


}

