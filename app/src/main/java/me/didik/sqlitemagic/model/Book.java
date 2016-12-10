package me.didik.sqlitemagic.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.siimkinks.sqlitemagic.Select;
import com.siimkinks.sqlitemagic.annotation.Column;
import com.siimkinks.sqlitemagic.annotation.Id;
import com.siimkinks.sqlitemagic.annotation.IgnoreColumn;
import com.siimkinks.sqlitemagic.annotation.Table;

import java.util.List;

import static com.siimkinks.sqlitemagic.BookTable.BOOK;


/**
 * Created by didik on 11/30/16.
 * Book
 */
@Table(persistAll = true)
@AutoValue
public abstract class Book implements Parcelable {
    @Id @Nullable
    public abstract Long id();
    public abstract String title();
    public abstract String published();

    @Column(onDeleteCascade = true)
    public abstract Author author();

    @IgnoreColumn
    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder title(String title);

        public abstract Builder published(String published);

        public abstract Builder author(Author author);

        public abstract Book build();
    }


    public static Builder builder() {
        return new AutoValue_Book.Builder();
    }

    public static List<Book> get() {

        return Select
                .all()
                .from(BOOK)
                .orderBy(BOOK.ID.desc())
                .queryDeep()
                .execute();
    }
}
