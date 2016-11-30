package me.didik.sqlitemagic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import me.didik.sqlitemagic.model.Author;
import me.didik.sqlitemagic.model.Book;

public class AddBookActivity extends AppCompatActivity {
    public static final String EXTRAS_DATA = "data";
    private EditText etTitle, etPublished, etFirstName, etLastName;
    private Book book;

    public static Intent newInstance(Context context, Book book) {
        Intent intent = new Intent(context, AddBookActivity.class);
        intent.putExtra(EXTRAS_DATA, book);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etTitle = (EditText) findViewById(R.id.et_title);
        etPublished = (EditText) findViewById(R.id.et_published);
        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);

        book = getIntent().getParcelableExtra(EXTRAS_DATA);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        if (book != null) {
            initData();
            actionBar.setTitle(book.getTitle());
        }
    }

    private void initData() {
        etTitle.setText(book.getTitle());
        etPublished.setText(book.getPublished());
        etFirstName.setText(book.getAuthor().getFirstName());
        etLastName.setText(book.getAuthor().getLastName());
    }

    public void submit(View view) {
        String title = etTitle.getText().toString();
        String published = etPublished.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();

        if (book != null) {
            book.setTitle(title);
            book.setPublished(published);
            book.getAuthor().setFirstName(firstName);
            book.getAuthor().setLastName(lastName);
        } else {
            Author author = new Author(firstName, lastName);
            book = new Book(title, published, author);
        }

        book.persist().execute();

        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (book != null) {
            getMenuInflater().inflate(R.menu.menu_add, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_delete:
                book.delete().execute();
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
