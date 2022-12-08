package ua.cn.stu.firebase_games.navigator;

import ua.cn.stu.firebase_games.model.Game;

public interface Navigator {
    void launchAuthFragment();
    void launchRegistrationFragment();
    void launchGamesFragment();
    void launchAddingFragment();
    void launchEditingFragment(Game game, String game_id);
}
