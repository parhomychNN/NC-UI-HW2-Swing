package book;

public class Author {

    private String name;
    private String email = "";
    private char gender = 'm';

    public Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return email.equals(author.email) && (name.equals(author.name)) && gender == author.gender;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (int) gender;
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }

    public String getName() {
        return name;
    }
}