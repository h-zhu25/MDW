package usantatecla.movies.v23;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String name;
	
	private List<Rental> rentals;

	public Customer(String name) {
		this.name = name;
		rentals = new ArrayList<Rental>();
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		RentalSummary rentalTotal = getRentalSummary();
		String result = "Rental Record for " + this.getName() + "\n";
		result += rentalTotal.getAllStatement();
		result += "Amount owed is " + rentalTotal.getTotalCharge() + "\n";
		result += "You earned " + rentalTotal.getTotalFrequentRenterPoints() + " frequent renter points";

		return result;
	}

	private RentalSummary getRentalSummary() {
		int totalFrequentRenterPoints = 0;
		double totalCharge = 0.0;
		StringBuilder allStatement = new StringBuilder();

		for (Rental rental : this.rentals) {
			totalFrequentRenterPoints += rental.getFrequentRenterPoints();
			totalCharge +=	rental.getCharge();
			allStatement.append("\t")
					.append(rental.getMovieTitle())
					.append("\t")
					.append(rental.getCharge()).append("\n");
		}
		return new RentalSummary(totalCharge, totalFrequentRenterPoints,allStatement.toString());
	}
}

class RentalSummary {

	Double totalCharge;

	Integer totalFrequentRenterPoints;

	String allStatement;

	public RentalSummary(Double totalCharge, Integer totalFrequentRenterPoints, String allStatement) {
		this.totalCharge = totalCharge;
		this.totalFrequentRenterPoints = totalFrequentRenterPoints;
		this.allStatement = allStatement;
	}

	public Double getTotalCharge() {
		return totalCharge;
	}

	public Integer getTotalFrequentRenterPoints() {
		return totalFrequentRenterPoints;
	}

	public String getAllStatement() {
		return allStatement;
	}

}
