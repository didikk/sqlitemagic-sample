package me.didik.sqlitemagic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.siimkinks.sqlitemagic.Select;
import com.siimkinks.sqlitemagic.annotation.Table;

import java.util.List;

import static com.siimkinks.sqlitemagic.BookTable.BOOK;

/**
 * Created by didik on 11/30/16.
 * Book
 */
@Table(persistAll = true)
public class Book implements Parcelable {
    String title;
    String published;
    Author author;

    public Book() {
    }

    public Book(String title, String released, Author author) {
        this.title = title;
        this.published = released;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public static List<Book> get() {
        return Select
                .all()
                .from(BOOK)
                .orderBy(BOOK._ID.desc())
                .queryDeep()
                .execute();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", published='" + published + '\'' +
                ", author=" + author +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.published);
        dest.writeParcelable(this.author, flags);
        dest.writeLong(this.id);
    }

    protected Book(Parcel in) {
        this.title = in.readString();
        this.published = in.readString();
        this.author = in.readParcelable(Author.class.getClassLoader());
        this.id = in.readLong();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
