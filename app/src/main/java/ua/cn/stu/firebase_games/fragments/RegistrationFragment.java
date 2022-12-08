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

public class RegistrationFragment extends BaseFragment {

    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;

    public static RegistrationFragment newInstance() {
        Bundle args = new Bundle();
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.emailRegisterEditText);
        password = view.findViewById(R.id.passwordRegisterEditText);
        Button btnRegistration = view.findViewById(R.id.registerRegButton);

        mAuth = FirebaseAuth.getInstance();

        btnRegistration.setOnClickListener(view12 -> {
            String login = email.getText().toString();
            String pass = password.getText().toString();

            if (checkLogin(login) && checkPassword(pass)) {
                mAuth.createUserWithEmailAndPassword(login, pass);
                getNavigator().launchAuthFragment();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container,false);
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
