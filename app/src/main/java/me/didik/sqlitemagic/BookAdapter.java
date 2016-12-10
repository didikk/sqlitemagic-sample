package me.didik.sqlitemagic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.didik.sqlitemagic.model.Author;
import me.didik.sqlitemagic.model.Book;

/**
 * Created by didik on 11/30/16.
 * B
 */

class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private List<Book> dataset;

    class BookHolder extends RecyclerView.ViewHolder {
        TextView title, published, author;

        BookHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            published = (TextView) itemView.findViewById(R.id.tv_published);
            author = (TextView) itemView.findViewById(R.id.tv_author);
        }
    }

    BookAdapter(List<Book> dataset) {
        this.dataset = dataset;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_book, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        Book item = dataset.get(position);
        Author author = item.author();

        holder.title.setText(item.title());
        holder.published.setText(item.published());
        holder.author.setText(author.firstName() + " " + author.lastName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    void updateList(List<Book> list) {
        // Allow recyclerview animations to complete normally if we already know about data changes
        if (list.size() != this.dataset.size() || !this.dataset.containsAll(list)) {
            this.dataset = list;
            notifyDataSetChanged();
        }
    }

    Book getItem(int position) {
        return dataset.get(position);
    }

    void remove(int position) {
        dataset.remove(position);
        notifyItemRemoved(position);
    }

}
