package ua.cn.stu.firebase_games.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

import ua.cn.stu.firebase_games.R;

public class AuthFragment extends BaseFragment {

    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.emailAuthEditText);
        password = view.findViewById(R.id.passwordAuthEditText);
        Button authBtn = view.findViewById(R.id.authAuthButton);
        Button regBtn = view.findViewById(R.id.registerAuthButton);

        mAuth = FirebaseAuth.getInstance();

        authBtn.setOnClickListener(v1 -> {
            String login = email.getText().toString();
            String pass = password.getText().toString();

            if (checkLogin(login) && checkPassword(pass)) {
                mAuth.signInWithEmailAndPassword(login, pass)
                        .addOnCompleteListener(requireActivity(), task -> {
                            if (task.isSuccessful()) {
                                getNavigator().launchGamesFragment();
                            }
                        });
            }
        });

        regBtn.setOnClickListener(v2 -> getNavigator().launchRegistrationFragment());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    private boolean checkLogin(String login) {
        if (login.isEmpty()) {
            email.setError("Email is empty");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String pass) {
        if (pass.isEmpty()) {
            password.setError("Password is empty");
            return false;
        }
        return true;
    }
}
