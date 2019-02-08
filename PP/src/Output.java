
public class Output {
	private Integer productId;
	private Double finalPrice;
	private String timeStamp;
	private Double Q;
	private String rivalName;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	@Override
	public String toString() {
		return "\nOutput [productId=" + productId + ", finalPrice=" + finalPrice + ", timeStamp=" + timeStamp + ", CheapestPriceAmongAllRivals=" + Q
				+ ", rivalName=" + rivalName + "] ";
	}

	public void setFinalPrice(Double d) {
		this.finalPrice = d;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getQ() {
		return Q;
	}

	public void setQ(Double double1) {
		Q = double1;
	}

	public String getRivalName() {
		return rivalName;
	}

	public void setRivalName(String rivalName) {
		this.rivalName = rivalName;
	}

}
