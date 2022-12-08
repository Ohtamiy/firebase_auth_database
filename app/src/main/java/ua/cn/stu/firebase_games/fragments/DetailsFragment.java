package ua.cn.stu.firebase_games.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ua.cn.stu.firebase_games.R;
import ua.cn.stu.firebase_games.model.Game;

public class DetailsFragment extends BaseFragment {

    public static final String GAME = "GAME";
    public static final String GAME_ID = "GAME_ID";

    public static DetailsFragment newInstance(Game game, String game_id) {
        Bundle args = new Bundle();
        args.putSerializable(GAME, game);
        args.putString(GAME_ID, game_id);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        Game game = (Game) getArguments().getSerializable(GAME);
        String gameId = getArguments().getString(GAME_ID);

        TextView nameDetails = view.findViewById(R.id.gameNameDetailsTextView);
        TextView genreDetails = view.findViewById(R.id.genreDetailsTextView);
        TextView hoursDetails = view.findViewById(R.id.hoursDetailsTextView);
        TextView infoDetails = view.findViewById(R.id.infoDetailsTextView);
        Button linkDetails = view.findViewById(R.id.linkButton);
        Button editDetails = view.findViewById(R.id.editButton);

        nameDetails.setText(game.getName());
        genreDetails.setText(game.getGenre());
        hoursDetails.setText(game.getHours() + " hours to complete");
        infoDetails.setText(game.getInfo());

        linkDetails.setOnClickListener(v1 -> {
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(game.getUrl()));
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Invalid link", Toast.LENGTH_SHORT).show();
            }
        });

        editDetails.setOnClickListener(v2 -> getNavigator().launchEditingFragment(game, gameId));
    }
}
