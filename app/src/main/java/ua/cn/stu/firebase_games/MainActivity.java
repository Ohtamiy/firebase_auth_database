package ua.cn.stu.firebase_games;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import ua.cn.stu.firebase_games.fragments.AddFragment;
import ua.cn.stu.firebase_games.fragments.AuthFragment;
import ua.cn.stu.firebase_games.fragments.EditFragment;
import ua.cn.stu.firebase_games.fragments.GamesFragment;
import ua.cn.stu.firebase_games.fragments.RegistrationFragment;
import ua.cn.stu.firebase_games.model.Game;
import ua.cn.stu.firebase_games.navigator.Navigator;

public class MainActivity extends AppCompatActivity implements Navigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (savedInstanceState == null) {
            launchAuthFragment();
        }
    }

    @Override
    public void launchAuthFragment() {
        AuthFragment authFragment = AuthFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, authFragment)
                .commit();
    }

    @Override
    public void launchRegistrationFragment() {
        RegistrationFragment registrationFragment = RegistrationFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, registrationFragment)
                .commit();
    }

    @Override
    public void launchGamesFragment() {
        GamesFragment gamesFragment = GamesFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, gamesFragment)
                .commit();
    }

    @Override
    public void launchAddingFragment() {
        AddFragment addFragment = AddFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, addFragment)
                .commit();
    }

    @Override
    public void launchEditingFragment(Game game, String game_id) {
        EditFragment editFragment = EditFragment.newInstance(game, game_id);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, editFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        launchAuthFragment();
        return true;
    }
}