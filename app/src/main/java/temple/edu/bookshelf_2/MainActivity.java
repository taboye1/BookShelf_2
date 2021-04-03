package temple.edu.bookshelf_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookSelectedInterface {
    FragmentManager fm;

    boolean twoPane;
    BookDetailsFragment bookDetailsFragment;
    Book selectedBook;
    private final String KEY_SELECTED_BOOK = "selectedBook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            selectedBook = savedInstanceState.getParcelable(KEY_SELECTED_BOOK);

        twoPane = findViewById(R.id.container2) != null;
        fm = getSupportFragmentManager();

        Fragment fragment1;
        fragment1 = fm.findFragmentById(R.id.container1);

        if (fragment1 instanceof BookDetailsFragment) {
            fm.popBackStack();
        } else if (!(fragment1 instanceof BookListFragment))
            fm.beginTransaction()
                    .add(R.id.container1, BookListFragment.newInstance(getTestBooks()))
                    .commit();

        bookDetailsFragment = (selectedBook == null) ? new BookDetailsFragment() : BookDetailsFragment.newInstance(selectedBook);
        if (twoPane) {
            fm.beginTransaction()
                    .replace(R.id.container2, bookDetailsFragment)
                    .commit();
        } else if (selectedBook != null) {
            fm.beginTransaction()
                    .replace(R.id.container1, bookDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
    private BookList getTestBooks(){
        BookList books = new BookList();
        Book book;
        for(int i = 1; i <=10; i++){
            books.add(book = new Book("Book" + i, "Author" + i ));
    }
        return  books;
};
    @Override
    public void bookSelected(int index){
        selectedBook = getTestBooks().get(index);
        if (twoPane)
            bookDetailsFragment.displayBook(selectedBook);
        else{
            fm.beginTransaction()
                    .replace(R.id.container1, BookDetailsFragment.newInstance(selectedBook))
                    .addToBackStack(null)
                    .commit();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_SELECTED_BOOK, selectedBook);
    }
    @Override
    public void onBackPressed(){
        selectedBook = null;
        super.onBackPressed();
    }

}