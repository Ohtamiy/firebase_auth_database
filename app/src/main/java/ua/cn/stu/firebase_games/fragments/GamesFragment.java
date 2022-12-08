package ua.cn.stu.firebase_games.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ua.cn.stu.firebase_games.R;
import ua.cn.stu.firebase_games.model.Game;
import ua.cn.stu.firebase_games.model.GameAdapter;

public class GamesFragment extends BaseFragment {

    private GameAdapter adapter;

    public static GamesFragment newInstance() {
        Bundle args = new Bundle();
        GamesFragment fragment = new GamesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnAdd = view.findViewById(R.id.addButton);
        RecyclerView recyclerView = view.findViewById(R.id.gamesList);

        btnAdd.setOnClickListener(v -> getNavigator().launchAddingFragment());

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        assert user != null;
        FirebaseRecyclerOptions<Game> options = new FirebaseRecyclerOptions
                .Builder<Game>()
                .setQuery(databaseReference.child(user.getUid()).child("Games"), Game.class)
                .build();

        adapter = new GameAdapter(options, getParentFragmentManager());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
