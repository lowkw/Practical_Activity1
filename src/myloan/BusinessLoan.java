package myloan;

/**
 *
 * BusinessLoan is the child class of Loan.<br>
 *
 * @version 1.5
 * @since 1.0
 */
public class BusinessLoan extends Loan {

    /**
     * System generated business loan number starting from 100
     */
    private static int loanNo = 100;

    /**
     * Constructor
     *
     * @param lastName Customer last name
     * @param loanAmount	Cannot exceed $250,000
     * @param loanTerm	1, 3 or 5 years (default is 1 year)
     * @param primeIntRate	Base lending rate
     * @param loanType	B for business loan
     */
    public BusinessLoan(String lastName, double loanAmount, int loanTerm, double primeIntRate, String loanType) {
        super(loanNo, lastName, loanAmount, loanTerm);
        super.setInterestRate(1 + primeIntRate);
        super.setLoanType(loanType);
        loanNo++;
    }
}
