package temple.edu.bookshelf_2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class BookListFragment extends Fragment {

    private static final String BOOKS_KEY = "bookList";
    private BookList books;
    ListView listView;

    BookSelectedInterface parentActivity;

    public BookListFragment() {
        // Required empty public constructor
    }

    public static BookListFragment newInstance(BookList books) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putParcelable(BOOKS_KEY, books);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookSelectedInterface) {
            parentActivity = (BookSelectedInterface) context;
        } else {
            throw new RuntimeException("Please implement the required interfaces");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            books = getArguments().getParcelable(BOOKS_KEY);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListView listView = (ListView) inflater.inflate(R.layout.fragment_book_list, container, false);
        listView.setAdapter(new BooksAdapter(getContext(), books));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                parentActivity.bookSelected(i);
            }
        });
        return listView;
    }

    interface BookSelectedInterface {
        void bookSelected(int index);
    }
}
