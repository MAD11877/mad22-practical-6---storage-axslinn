package sg.edu.np.mad.exercise6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.exercise6.User;

public class myAdapter extends RecyclerView.Adapter<myViewHolder>{
    ArrayList<User> data;

    public myAdapter(ArrayList<User> input){
        data = input;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        if(viewType == 7) {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.reviewitem2,
                    parent,
                    false
            );
        } else {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.reviewitem,
                    parent,
                    false
            );
        }
        return new myViewHolder(item);
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(data.get(position).name.substring(data.get(position).name.length()-1));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        User u = data.get(position);
        holder.txtTitle.setText(u.name);
        holder.txtDesc.setText(u.description);


        holder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Debug", "Image clicked");

                new AlertDialog.Builder(holder.img.getContext())
                        .setTitle("Profile")
                        .setMessage(u.name)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent viewProfile = new Intent(holder.img.getContext(), MainActivity.class);
                                viewProfile.putExtra("id", position);
                                holder.img.getContext().startActivity(viewProfile);
                            }
                        })
                        .setNegativeButton("Close", null)
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
