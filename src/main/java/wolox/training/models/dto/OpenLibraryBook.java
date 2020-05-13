package wolox.training.models.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OpenLibraryBook {

	private String title;

	private String subtitle;

	private List<OpenLibraryBookAuthor> authors;

	private List<OpenLibraryBookClasification> clasifications;

	private List<OpenLibraryBookSubjects> subjects;

	private List<OpenLibraryBookPublisher> publishers;

	private List<OpenLibraryBookPublishPlace> publish_places;

	private String publish_date;

	private OpenLibraryBookCover cover;

	private List<OpenLibraryBookExcerpt> excerpts;

	private List<OpenLibraryBookLink> links;

	private List<OpenLibraryBookEbooks> ebooks;

	private int number_of_pages;

	private String weight;

	public OpenLibraryBook() {

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

	public List<OpenLibraryBookClasification> getClasifications() {
		return clasifications;
	}

	public void setClasifications(List<OpenLibraryBookClasification> clasifications) {
		this.clasifications = clasifications;
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

	public List<OpenLibraryBookPublishPlace> getPublish_places() {
		return publish_places;
	}

	public void setPublish_places(List<OpenLibraryBookPublishPlace> publish_places) {
		this.publish_places = publish_places;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	public List<OpenLibraryBookExcerpt> getExcerpts() {
		return excerpts;
	}

	public void setExcerpts(List<OpenLibraryBookExcerpt> excerpts) {
		this.excerpts = excerpts;
	}

	public List<OpenLibraryBookLink> getLinks() {
		return links;
	}

	public void setLinks(List<OpenLibraryBookLink> links) {
		this.links = links;
	}

	public List<OpenLibraryBookEbooks> getEbooks() {
		return ebooks;
	}

	public void setEbooks(List<OpenLibraryBookEbooks> ebooks) {
		this.ebooks = ebooks;
	}

	public int getNumber_of_pages() {
		return number_of_pages;
	}

	public void setNumber_of_pages(int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
