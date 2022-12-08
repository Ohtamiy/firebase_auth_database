package ua.cn.stu.firebase_games.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

import ua.cn.stu.firebase_games.R;
import ua.cn.stu.firebase_games.fragments.DetailsFragment;

public class GameAdapter extends FirebaseRecyclerAdapter<Game, GameAdapter.GameViewHolder> implements Serializable {

    private final FragmentManager fragmentManager;

    public GameAdapter(@NonNull FirebaseRecyclerOptions<Game> options, FragmentManager fragmentManager) {
        super(options);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.GameViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Game model) {
        holder.name.setText(model.getName());
        holder.genre.setText(model.getGenre());

        holder.more.setOnClickListener(v1 -> {
            int pos = holder.getBindingAdapterPosition();
            DatabaseReference item = getRef(pos);
            item.removeValue();
        });

        holder.itemView.setOnClickListener(v2 -> {
            int pos = holder.getBindingAdapterPosition();
            DatabaseReference item = getRef(pos);
            DetailsFragment fragment = DetailsFragment.newInstance(model, item.getKey());
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        });
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_game, parent, false));
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {

        TextView name, genre;
        ImageView more;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.gameNameTextView);
            genre = itemView.findViewById(R.id.genreTextView);
            more = itemView.findViewById(R.id.removeImageView);
        }
    }

    @Override
    public void updateOptions(@NonNull FirebaseRecyclerOptions<Game> options) {
        super.updateOptions(options);
    }

    @NonNull
    @Override
    public DatabaseReference getRef(int position) {
        return super.getRef(position);
    }
}