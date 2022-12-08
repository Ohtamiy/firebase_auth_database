package ua.cn.stu.firebase_games.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ua.cn.stu.firebase_games.R;
import ua.cn.stu.firebase_games.model.Game;

public class AddFragment extends BaseFragment {

    private EditText gameName;
    private EditText genre;
    private Spinner hours;
    private EditText link;
    private EditText info;

    private FirebaseUser user;
    private DatabaseReference databaseReference;

    public static AddFragment newInstance() {
        Bundle args = new Bundle();
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnAdd = view.findViewById(R.id.addOrEditButton);
        btnAdd.setText("Add");
        gameName = view.findViewById(R.id.gameNameAddEditText);
        genre = view.findViewById(R.id.genreAddEditText);
        hours = view.findViewById(R.id.hoursAddSpinner);
        link = view.findViewById(R.id.urlAddEditText);
        info = view.findViewById(R.id.infoAddEditText);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnAdd.setOnClickListener(v -> {
            if (!gameName.getText().toString().isEmpty() &&
                    !genre.getText().toString().isEmpty()) {
                String id = databaseReference.getKey();
                String name = gameName.getText().toString();
                String ganre = genre.getText().toString();
                String hou = hours.getSelectedItem().toString();
                String url = link.getText().toString();
                String description = info.getText().toString();

                Game game = new Game(id, name, ganre, description, hou, url);
                databaseReference
                        .child(user.getUid())
                        .child("Games").push().setValue(game)
                        .addOnCompleteListener(requireActivity(), task -> getNavigator().launchGamesFragment());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_edit, container, false);
    }

}
