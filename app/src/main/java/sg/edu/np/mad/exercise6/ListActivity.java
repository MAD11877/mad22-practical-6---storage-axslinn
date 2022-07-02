package sg.edu.np.mad.exercise6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    static ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler db = new DBHandler(this, null, null, 1);


        userList = db.getUsers();
//        new ArrayList<>();
//        for(int i=0; i<20; i++)
//        {
//            User u = new User();
//            u.name = "Name" + new Random().nextInt();
//            u.description = "Description " + new Random().nextInt();
//            u.followed = new Random().nextInt()%2 == 0;
//            userList.add(u);
//        }

        RecyclerView rv = findViewById(R.id.rv);
        myAdapter Adapter = new myAdapter(userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(Adapter);
    }
}