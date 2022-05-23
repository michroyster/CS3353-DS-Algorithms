public class Book {
    int bookID;
    String title;
    String author;
    float average_rating;
    String isbn;
    double isbn13;
    String language_code;
    int num_pages;
    int ratings_count;
    int text_reviews_count;
    String publication_date;
    String publisher;
   
    // setter methods
    private void setBookID (int bookID) { this.bookID = bookID; }
    private void setTitle(String title) { this.title = title; }
    private void setAuthor(String author) { this.author = author; }
    private void setAverageRating(float avgRating) { this.average_rating = avgRating; }
    private void setISBN(String isbn) { this.isbn = isbn;}
    private void setISBN13(double isbn13) { this.isbn13 = isbn13; }
    private void setLanguageCode(String languageCode) { this.language_code = languageCode; }
    private void setNumPages(int numPages) { this.num_pages = numPages; }
    private void setRatingsCount(int ratingsCount) { this.ratings_count = ratingsCount; }
    private void setTextReviewsCount(int trc) { this.text_reviews_count = trc; }
    private void setPublicationDate(String publicationDate) { this.publication_date = publicationDate; }
    private void setPublisher(String publisher) { this.publisher = publisher; }

    // getter methods
    public int getBookID () { return bookID; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public float getAverageRating() {  return average_rating; }
    public String getISBN() { return isbn; }
    public double getISBN13() { return isbn13; }
    public String getLanguageCode() { return language_code; }
    public int getNumPages() { return num_pages; }
    public int getRatingsCount() { return ratings_count; }
    public int getTextReviewsCount() { return text_reviews_count; }
    public String getPublicationDate() { return publication_date; }
    public String getPublisher() { return publisher; }

    // blank constructor
    public Book() {  }

    // parameterized constructor
    public Book(String[] parameters)
    {
        this.setBookID(Integer.parseInt(parameters[0]));
        this.setTitle(parameters[1]);
        this.setAuthor(parameters[2]);
        this.setAverageRating(Float.parseFloat(parameters[3]));
        this.setISBN(parameters[4]);
        this.setISBN13(Double.parseDouble(parameters[5]));
        this.setLanguageCode(parameters[6]);
        this.setNumPages(Integer.parseInt(parameters[7]));
        this.setRatingsCount(Integer.parseInt(parameters[8]));
        this.setTextReviewsCount(Integer.parseInt(parameters[9]));
        this.setPublicationDate(parameters[10]);
        this.setPublisher(parameters[11]);
    }

    // Print the details fo the book
    public void printDetails()
    {
        System.out.println("bookID: \t\t" + bookID + "\n" +
                            "title: \t\t\t" + title + "\n" +
                            "author: \t\t" + author + "\n" +
                            "average_rating: \t" + average_rating + "\n" +
                            "isbn: \t\t\t" + isbn + "\n" +
                            "isbn13: \t\t" + isbn13 + "\n" +
                            "language_code: \t\t" + language_code + "\n" +
                            "num_pages: \t\t" + num_pages + "\n" +
                            "ratings_count: \t\t" + ratings_count + "\n" +
                            "text_reviews_count: \t" + text_reviews_count + "\n" +
                            "publication_date: \t" + publication_date + "\n" +
                            "publisher: \t\t" + publisher);
    }
}
