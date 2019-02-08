import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class SellerData implements PI {
	private Integer SellerID;
	private String type;
	private String netValue;
	private String since;
	private Double userRating;
	private String lastSold;
	private Integer category;

	public Integer getSellerID() {
		return SellerID;
	}

	public void setSellerID(Integer sellerID) {
		SellerID = sellerID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNetValue() {
		return netValue;
	}

	public void setNetValue(String netValue) {
		this.netValue = netValue;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public Double getUserRating() {
		return userRating;
	}

	public void setUserRating(Double userRating) {
		this.userRating = userRating;
	}

	public String getLastSold() {
		return lastSold;
	}

	public void setLastSold(String lastSold) {
		this.lastSold = lastSold;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	CellProcessor[] processors = new CellProcessor[] { new ParseInt(), // ISBN
			new NotNull(), // title
			new NotNull(), // author
			new NotNull(), // publisher
			new ParseDouble(), new NotNull(), // price
			new ParseInt() };

	@Override
	public CellProcessor[] getCellProcessors() {
		return processors;
	}
}
