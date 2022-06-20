package myloan;

import java.io.Serializable;

/**
 *
 * Loan is the abstract super class to create objects typed loan.<br>
 * Loan object is serializable.
 *
 * @version 1.5
 * @since 1.0
 */
public abstract class Loan implements LoanConstants, Serializable {

    /**
     * Private loan number
     */
    private int loanNo;
    /**
     * Private last name
     */
    private String lastName;
    /**
     * Private loan amount
     */
    private double loanAmount;
    /**
     * Private interest rate
     */
    private double interestRate;
    /**
     * Private loan term
     */
    private int loanTerm;
    /**
     * Private loan type
     */
    private String loanType;

    /**
     * Constructor
     *
     * @param loanNo System generated loan number
     * @param lastName Customer last name
     * @param loanAmount	Cannot exceed $250,000
     * @param loanTerm	1, 3 or 5 years (default is 1 year)
     */
    public Loan(int loanNo, String lastName, double loanAmount, int loanTerm) {
        this.loanNo = loanNo;
        this.lastName = lastName;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
    }

    /**
     * Return the maximum loan amount allowed
     *
     * @return	maxLoan
     */
    public static double getMaxLoan() {
        return MAX_LOAN;
    }

    /**
     * Set the default loan term to 1 year
     */
    public void setLoanTerm() {
        if (loanTerm != SHORT_TERM
                && loanTerm != MEDIUM_TERM
                && loanTerm != LONG_TERM) {
            loanTerm = SHORT_TERM;
        }
    }

    /**
     * Setter to set interestRate private field
     *
     * @param interestRate Prime interest rate + loan type interest rate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Setter to set loanType private field
     *
     * @param loanType B for business loan or P for personal loan
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
     * Override toString() method to print loan details
     *
     */
    @Override
    public String toString() {
        double primeIntRate = 0;
        if (loanType.equals("B")) {
            primeIntRate = interestRate - 1;
        } else {
            primeIntRate = interestRate - 2;
        }
        double loanDue = loanAmount + (loanAmount * loanTerm * (interestRate / 100));
        String line1 = LoanConstants.COMPANY + " loan details: ";
        String line2 = "Prime interest rate: " + String.format("%.2f", (double) primeIntRate);
        String line3 = (loanType.equals("B")) ? "Business loan" : "Personal loan";
        String line4 = "Loan number: " + loanNo;
        String line5 = "Customer last name: " + lastName;
        String line6 = "Loan amount: " + String.format("%.2f", (double) loanAmount);
        String line7 = "Loan term: " + loanTerm;
        String line8 = "Interest rate: " + interestRate;
        String line9 = "Total amount due: $" + String.format("%.2f", (double) loanDue);
        return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n"
                + line5 + "\n" + line6 + "\n" + line7 + "\n" + line8 + "\n" + line9 + "\n";
    }
}
