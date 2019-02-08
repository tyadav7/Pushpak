import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class P1 {

	private static final CsvPreference PIPE_DELIMITED = new CsvPreference.Builder('"', '|', "\n").build();

	public static void main(String args[]) {
		PI pi = new CompetitorData();
		List<PI> ecompdata = readDataFromFile("data\\ecom\\ecom_competitor_data.txt",
				CompetitorData.class, pi.getCellProcessors());
		pi = new InternalProductData();
		List<PI> internalProdData = readDataFromFile(
				"data\\ecom\\internal_product_data.txt", InternalProductData.class,
				pi.getCellProcessors());

		pi = new SellerData();
		List<PI> sellerData = readDataFromFile(
				"data\\ecom\\seller_data.txt", SellerData.class,
				pi.getCellProcessors());

		Map<Integer , SellerData> sellerDataMap = sellerData.stream().map(SellerData.class::cast).collect(Collectors.toMap(t-> t.getSellerID() , t -> t));
		
		Comparator<CompetitorData> comparator = new Comparator<CompetitorData>() {

			@Override
			public int compare(CompetitorData o1, CompetitorData o2) {
				if (o1.getPrice() > o2.getPrice()) {
					return 1;
				} else if (o1.getPrice() < o2.getPrice()) {
					return -1;
				} else {
					if (o1.getSaleEvent().equals("Special")) {
						return -1;
					} else {
						return 1;
					}
				}
			}

		};

		 Map<Integer, CompetitorData> competitorData = ecompdata.stream().map(CompetitorData.class::cast)
				.collect(Collectors.groupingBy(CompetitorData::getProductId, Collectors.minBy(comparator))).entrySet()
				.stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));
				
		
		
		
		Function<InternalProductData, Output> mapper = new Function<InternalProductData, Output>() {

			@Override
			public Output apply(InternalProductData t) {
				
				CompetitorData cd = competitorData.get(t.getProductId());
				Output output = new Output();
				output.setProductId(t.getProductId());
				output.setTimeStamp(t.getLastModified());
				output.setRivalName(cd.getRivalName());
				output.setQ(cd.getPrice());
				
				if(t.getProcuredValue() + t.getMaxMargin() < cd.getPrice())
				{
					output.setFinalPrice(t.getProcuredValue() + t.getMaxMargin());
				}
				else if(t.getProcuredValue() + t.getMinMargin() < cd.getPrice())
				{
					output.setFinalPrice(cd.getPrice());
				}
				else if((t.getProcuredValue() < cd.getPrice()) && cd.getSaleEvent().equals("Special"))
				{
					output.setFinalPrice(cd.getPrice());
				}
				else if(cd.getPrice() < t.getProcuredValue() && cd.getSaleEvent().equals("Special") && sellerDataMap.get(t.getSellerID()).getNetValue().equals("VeryHigh"))
				{
					output.setFinalPrice(t.getProcuredValue()*0.9);
				}
				else
				{
					output.setFinalPrice(t.getProcuredValue());
				}
				
				return output;
			
			}

		};
		
		List<Output> list = internalProdData.stream().map(InternalProductData.class::cast)
		.filter(ipdata -> competitorData.entrySet().stream()
		.anyMatch(cd -> cd.getKey().equals(ipdata.getProductId())))
		.map(mapper)
		.collect(Collectors.toList());
		
		System.out.println(list);
		

	}

	public static <R, A, T> R collect(Optional<T> option, Collector<? super T, A, R> collector) {
		final A container = collector.supplier().get();
		option.ifPresent(v -> collector.accumulator().accept(container, v));
		return collector.finisher().apply(container);
	}

	public static <T> List<T> toList(Optional<T> option) {
		if (option.isPresent())
			return Collections.singletonList(option.get());
		else
			return Collections.emptyList();
	}

	private static List<PI> readDataFromFile(String path, Class<?> class1, CellProcessor[] cp) {
		List<PI> rows = new ArrayList<>();
		try {

			ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(path), PIPE_DELIMITED);
			String[] header = beanReader.getHeader(true);
			PI bookBean;
			while ((bookBean = (PI) beanReader.read(class1, header, cp)) != null) {
				rows.add(bookBean);
			}

			beanReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}
}
