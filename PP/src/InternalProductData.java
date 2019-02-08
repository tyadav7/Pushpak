import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class InternalProductData implements PI {
	private Integer ProductId;
	private String category;
	private String subcategory;
	private Double procuredValue;
	private Double minMargin;
	private Double maxMargin;
	private Integer SellerID;
	private String lastModified;

	public Integer getProductId() {
		return ProductId;
	}

	public void setProductId(Integer productId) {
		ProductId = productId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Double getProcuredValue() {
		return procuredValue;
	}

	public void setProcuredValue(Double procuredValue) {
		this.procuredValue = procuredValue;
	}

	public Double getMinMargin() {
		return minMargin;
	}

	public void setMinMargin(Double minMargin) {
		this.minMargin = minMargin;
	}

	public Double getMaxMargin() {
		return maxMargin;
	}

	public void setMaxMargin(Double maxMargin) {
		this.maxMargin = maxMargin;
	}

	public Integer getSellerID() {
		return SellerID;
	}

	public void setSellerID(Integer sellerID) {
		SellerID = sellerID;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	CellProcessor[] processors = new CellProcessor[] { new ParseInt(), // ISBN
			new NotNull(), // title
			new NotNull(), // title
			new ParseDouble(), new ParseDouble(), // ISBN
			new ParseDouble(), // ISBN
			new ParseInt(), // ISBN
			new NotNull() // title
	};

	@Override
	public CellProcessor[] getCellProcessors() {
		return processors;
	}

}
