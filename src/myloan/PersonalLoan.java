package myloan;

/**
 *
 * PersoanLoan is the child class of Loan.<br>
 *
 * @version 1.5
 * @since 1.0
 */
public class PersonalLoan extends Loan {

    /**
     * System generated personal loan number starting from 200
     */
    private static int loanNo = 200;

    /**
     * Constructor
     *
     * @param lastName Customer last name
     * @param loanAmount	Cannot exceed $250,000
     * @param loanTerm	1, 3 or 5 years (default is 1 year)
     * @param primeIntRate	Base lending rate
     * @param loanType	P for personal loan
     */
    public PersonalLoan(String lastName, double loanAmount, int loanTerm, double primeIntRate, String loanType) {
        super(loanNo, lastName, loanAmount, loanTerm);
        super.setInterestRate(2 + primeIntRate);
        super.setLoanType(loanType);
        loanNo++;
    }
}
