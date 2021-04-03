package temple.edu.bookshelf_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BooksAdapter extends BaseAdapter {
    Context context;
    BookList books;

    public BooksAdapter (Context context, BookList books){
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount(){
        return books.size();
    }
    @Override
    public Object getItem(int position){
        return books.get(position);
    }
    @Override
    public long getItemId(int position){
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView titleTextView;
        TextView authorTextView;

        if (!(convertView instanceof LinearLayout)) {
            convertView = LayoutInflater.from(context).inflate(R.layout.books_adapter_layout, parent, false);
        }
        titleTextView = convertView.findViewById(R.id.titleTextView);
        authorTextView = convertView.findViewById(R.id.authorTextView);

        titleTextView.setText(((Book) getItem(position)).getTitle());
        authorTextView.setText(((Book) getItem(position)).getAuthor());
        return convertView;
    }
}
