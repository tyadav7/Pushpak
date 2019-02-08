import java.util.Arrays;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class CompetitorData implements PI {
	private Integer productId;
	private Double price;
	private String saleEvent;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSaleEvent() {
		return saleEvent;
	}

	public void setSaleEvent(String saleEvent) {
		this.saleEvent = saleEvent;
	}

	public String getRivalName() {
		return rivalName;
	}

	public void setRivalName(String rivalName) {
		this.rivalName = rivalName;
	}

	@Override
	public String toString() {
		return "CompetitorData [productId=" + productId + ", price=" + price + ", saleEvent=" + saleEvent
				+ ", rivalName=" + rivalName + ", fetchTS=" + fetchTS + ", processors=" + Arrays.toString(processors)
				+ "]";
	}

	public String getFetchTS() {
		return fetchTS;
	}

	public void setFetchTS(String fetchTS) {
		this.fetchTS = fetchTS;
	}

	private String rivalName;
	private String fetchTS;

	public CellProcessor[] processors = new CellProcessor[] { 
			new ParseInt(), 
			new ParseDouble(), // author
			new NotNull(), // title
			new NotNull(), 
			new NotNull() };

	@Override
	public CellProcessor[] getCellProcessors() {
		return processors;
	}
}
