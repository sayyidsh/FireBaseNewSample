package pro.nanosystems.firebasenewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BooksActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mTitle, mAuthor;
    private Button mSave;
   // private String mKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        mTitle = findViewById(R.id.title);
        mAuthor = findViewById(R.id.author);
        mSave = findViewById(R.id.save);

        mSave.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String mKey;
        Books newBook = new Books(mTitle.getText().toString(),
                mAuthor.getText().toString());
        mKey= AllBooksActivity.mRef.push().getKey();
        AllBooksActivity.mRef.child(mKey).setValue(newBook);
        mTitle.setText("");
        mAuthor.setText("");
        mTitle.requestFocus();
    }
}
