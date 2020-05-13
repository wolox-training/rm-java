package wolox.training.models.dto;

public class OpenLibraryBookClasification {
	private String[] lc_classifications;
	private String[] dewey_decimal_class;

	public String[] getLc_classifications() {
		return lc_classifications;
	}

	public void setLc_classifications(String[] lc_classifications) {
		this.lc_classifications = lc_classifications;
	}

	public String[] getDewey_decimal_class() {
		return dewey_decimal_class;
	}

	public void setDewey_decimal_class(String[] dewey_decimal_class) {
		this.dewey_decimal_class = dewey_decimal_class;
	}

	public OpenLibraryBookClasification() {
		super();
	}

}
