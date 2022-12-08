package ua.cn.stu.firebase_games.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ua.cn.stu.firebase_games.navigator.Navigator;

public class BaseFragment extends Fragment {
    private Navigator navigator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = (Navigator) context;
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
