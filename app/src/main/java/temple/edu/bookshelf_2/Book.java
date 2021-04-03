package temple.edu.bookshelf_2;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String title;
    private String author;
    private String coverUrl;
    private int id;

    public Book(String title, String author,int id,String coverUrl) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.coverUrl = coverUrl;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        id = in.readInt();
        coverUrl = in.readString();

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {

            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {

            return new Book[size];
        }
    };

    public Book(String s, String s1) {
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getAuthor() {

        return author;
    }
    public void setAuthor(String author) {

        this.author = author;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCoverUrl() {
        return coverUrl;
    }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
    @Override
    public int describeContents() {

        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(id);
        dest.writeString(coverUrl);
    }
}
