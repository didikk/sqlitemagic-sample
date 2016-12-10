package me.didik.sqlitemagic.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.siimkinks.sqlitemagic.annotation.Id;
import com.siimkinks.sqlitemagic.annotation.IgnoreColumn;
import com.siimkinks.sqlitemagic.annotation.Table;

/**
 * Created by didik on 11/30/16.
 * A
 */

@Table(persistAll = true)
@AutoValue
public abstract class Author implements Parcelable {
    @Id @Nullable
    public abstract Long id();
    public abstract String firstName();
    public abstract String lastName();

    @IgnoreColumn
    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder firstName(String firstName);

        public abstract Builder lastName(String lastName);

        public abstract Author build();
    }


    public static Builder builder() {
        return new AutoValue_Author.Builder();
    }
}
