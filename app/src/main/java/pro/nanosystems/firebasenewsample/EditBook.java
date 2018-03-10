package pro.nanosystems.firebasenewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBook extends AppCompatActivity implements View.OnClickListener {
    private EditText titleEd, authorEd;
    private Button savEd, delEd;
    private String mR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        savEd = findViewById(R.id.saveEd);
        savEd.setOnClickListener(this);
        delEd = findViewById(R.id.delEd);
        delEd.setOnClickListener(this);
        titleEd = findViewById(R.id.titleEd);
        authorEd = findViewById(R.id.authorEd);
        Intent o = getIntent();
        titleEd.setText(o.getStringExtra("title"));
        authorEd.setText(o.getStringExtra("author"));
        mR = o.getStringExtra("ref");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveEd:
                Books book = new Books(titleEd.getText().toString(), authorEd.getText().toString());
                AllBooksActivity.mRef.child(mR).setValue(book);
                finish();
                break;
            case R.id.delEd:
                AllBooksActivity.mRef.child(mR).removeValue();
                finish();
                break;
        }
    }
}
