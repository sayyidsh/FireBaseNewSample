package pro.nanosystems.firebasenewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    private EditText idEdit, nameEdit, emailEdit;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idEdit = findViewById(R.id.idEdit);
        nameEdit = findViewById(R.id.nameEdit);
        emailEdit = findViewById(R.id.emailEdit);
        saveBtn = findViewById(R.id.saveBtn);

        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myRef = database.getReference("Info");

        myRef.keepSynced(true);

        saveBtn.setOnClickListener(this);
myRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String id = dataSnapshot.child("Id").getValue(String.class);
        String name = dataSnapshot.child("Name").getValue(String.class);
        String email = dataSnapshot.child("Email").getValue(String.class);
        idEdit.setText(id);
        nameEdit.setText(name);
        emailEdit.setText(email);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

      /*  myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id = dataSnapshot.child("Id").getValue(String.class);
                String name = dataSnapshot.child("Name").getValue(String.class);
                String email = dataSnapshot.child("Email").getValue(String.class);
                idEdit.setText(id);
                nameEdit.setText(name);
                emailEdit.setText(email);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }

    @Override
    public void onClick(View v) {
        myRef.child("Id").setValue(idEdit.getText().toString());
        myRef.child("Name").setValue(nameEdit.getText().toString());
        myRef.child("Email").setValue(emailEdit.getText().toString());
    }


}
