package sg.edu.np.mad.exercise6;

import static android.media.CamcorderProfile.get;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHandler db = new DBHandler(this, null, null, 1);
    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent rec = getIntent();
        int value = rec.getIntExtra("id",0);
        u = ListActivity.userList.get(value);

        TextView name = findViewById(R.id.txtName);
        name.setText(u.name);
        TextView description = findViewById(R.id.txtDescription);
        description.setText(u.description);
        setFollowBtn();
    }

    private void setFollowBtn() {
        Button b = findViewById(R.id.btnFollow);
        if(u.followed) {
            b.setText("Unfollow");
            u.setFollowed(true);
        } else {
            b.setText("Follow");
            u.setFollowed(false);
        }
        db.updateUser(u);
    }
    public void onFollowClick(View v) {
        u.followed = !u.followed;
        if(u.followed)
            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Unfollowed", Toast.LENGTH_SHORT).show();
        setFollowBtn();

        DBHandler db = new DBHandler(this,null,null,1);
        db.updateUser(u);
    }
}