package pro.nanosystems.firebasenewsample;

/**
 * Created by sayyid on 10/03/2018.
 */

public class Books {
    String mTitle;
    String mAuthor;

    public Books(String mTitle, String mAuthor) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public Books() {
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

}
