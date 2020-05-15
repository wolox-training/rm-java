package wolox.training.models.dto;

import java.util.List;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OpenLibraryBook {

    private String title;
    private String subtitle;

    private List<OpenLibraryBookAuthor> authors;

    private List<OpenLibraryBookSubjects> subjects;

    private List<OpenLibraryBookPublisher> publishers;

    private String publishDate;

    private OpenLibraryBookCover cover;

    private int numberOfPages;

    private String weight;

    public OpenLibraryBook() {

    }


    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }



    public OpenLibraryBookCover getCover() {
        return cover;
    }

    public void setCover(OpenLibraryBookCover cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<OpenLibraryBookAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<OpenLibraryBookAuthor> authors) {
        this.authors = authors;
    }

    public List<OpenLibraryBookSubjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<OpenLibraryBookSubjects> subjects) {
        this.subjects = subjects;
    }

    public List<OpenLibraryBookPublisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<OpenLibraryBookPublisher> publishers) {
        this.publishers = publishers;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
