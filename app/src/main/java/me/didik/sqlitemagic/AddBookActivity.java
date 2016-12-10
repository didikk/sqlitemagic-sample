package me.didik.sqlitemagic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.didik.sqlitemagic.model.Author;
import me.didik.sqlitemagic.model.Book;
import timber.log.Timber;

public class AddBookActivity extends AppCompatActivity {
    public static final String EXTRAS_DATA = "data";
    @BindView(R.id.et_title) EditText etTitle;
    @BindView(R.id.et_published) EditText etPublished;
    @BindView(R.id.et_first_name) EditText etFirstName;
    @BindView(R.id.et_last_name) EditText etLastName;

    private Book book;
    private Unbinder unbinder;

    public static Intent newInstance(Context context, Book book) {
        Intent intent = new Intent(context, AddBookActivity.class);
        intent.putExtra(EXTRAS_DATA, book);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        unbinder = ButterKnife.bind(this);

        book = getIntent().getParcelableExtra(EXTRAS_DATA);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        if (book != null) {
            initData();
            actionBar.setTitle(book.title());
        }
    }

    private void initData() {
        etTitle.setText(book.title());
        etPublished.setText(book.published());
        etFirstName.setText(book.author().firstName());
        etLastName.setText(book.author().lastName());
    }

    public void submit(View view) {
        String title = etTitle.getText().toString();
        String published = etPublished.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();

        if (book != null) {
            Author author = book.author().toBuilder()
                    .id(book.author().id())
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            book = book.toBuilder()
                    .id(book.id())
                    .title(title)
                    .published(published)
                    .author(author)
                    .build();
        } else {
            Author author = Author.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            book = Book.builder()
                    .title(title)
                    .published(published)
                    .author(author)
                    .build();
        }

        Timber.d(book.toString());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
