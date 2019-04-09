package book;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Book {

    private String name;
    private Author[] authors;
    private Date date = new Date();
    private AgeRestriction ageRestriction;

    private double price;
    private Boolean isGift = false;

    public Book() {
    }

    public Book(String name, Author[] authors, Date date, AgeRestriction ageRestriction, double price, Boolean isGift) {
        this.name = name;
        this.authors = authors;
        this.date = date;
        this.ageRestriction = ageRestriction;
        this.price = price;
        this.isGift = isGift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getGift() {
        return isGift;
    }

    public void setGift(Boolean gift) {
        isGift = gift;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", authors=" + getAuthorNames() +
                ", date=" + date +
                ", ageRestriction=" + ageRestriction.getDescription() +
                ", price=" + price +
                ", isGift=" + isGift +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                name.equals(book.name) &&
                Arrays.equals(authors, book.authors) &&
                date.equals(book.date) &&
                ageRestriction == book.ageRestriction &&
                isGift.equals(book.isGift);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, date, ageRestriction, price, isGift);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }

    @JsonIgnore
    public String getAuthorNames() {
        String authorNames = "";
        for(int i = 0; i < authors.length; i++){
            if (i == 0){
                authorNames += authors[i].getName();
            }else {
                authorNames += (", " +authors[i].getName());
            }
        }

        return authorNames;
    }
}