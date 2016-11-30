package me.didik.sqlitemagic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.siimkinks.sqlitemagic.Select;
import com.siimkinks.sqlitemagic.annotation.Table;

import java.util.List;

import static com.siimkinks.sqlitemagic.AuthorTable.AUTHOR;

/**
 * Created by didik on 11/30/16.
 * A
 */

@Table(persistAll = true)
public class Author implements Parcelable {
    String firstName;
    String lastName;

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static List<Author> get() {
        return Select.all().from(AUTHOR).execute();
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeLong(this.id);
    }

    protected Author(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.id = in.readLong();
    }

    public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
