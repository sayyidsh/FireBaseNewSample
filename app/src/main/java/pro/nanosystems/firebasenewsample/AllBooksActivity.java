package pro.nanosystems.firebasenewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllBooksActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mBooksList;
    private Button addNewBooks, delAll;
    public static FirebaseDatabase mDataBase;
    public static DatabaseReference mRef;



    FirebaseListAdapter<Books> mListAdapter;
    private Intent o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        mBooksList = findViewById(R.id.booksListView);
        addNewBooks = findViewById(R.id.addNewBooks);
        addNewBooks.setOnClickListener(this);
        delAll = findViewById(R.id.delAll);
        delAll.setOnClickListener(this);

        mDataBase = FirebaseDatabase.getInstance();
        mDataBase.setPersistenceEnabled(true);
        mRef = mDataBase.getReference("Books");
        mRef.keepSynced(true);


        mListAdapter = new FirebaseListAdapter<Books>(this, Books.class, R.layout.books_row, mRef) {
            @Override
            protected void populateView(View v, Books model, int position) {
                TextView title = v.findViewById(R.id.bookTitle);
                TextView author = v.findViewById(R.id.bookAuthor);
                title.setText(model.getmTitle());
                author.setText(model.getmAuthor());

            }
        };
        o = new Intent(this, EditBook.class);
        mBooksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference ref = mListAdapter.getRef(position);

                TextView title = view.findViewById(R.id.bookTitle);
                TextView author = view.findViewById(R.id.bookAuthor);
                o.putExtra("ref", ref.getKey());
                o.putExtra("title", title.getText().toString());
                o.putExtra("author", author.getText().toString());
                startActivity(o);

            }
        });
        mBooksList.setAdapter(mListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addNewBooks:
                Intent i = new Intent(this, BooksActivity.class);
                startActivity(i);
                break;



            case R.id.delAll:
                 mRef.removeValue();
                break;
        }

    }
}
