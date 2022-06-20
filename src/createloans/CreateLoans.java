package createloans;

import myloan.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/**
 * Loan calculator.<br>
 * Different interest rates charged for Business and Personal loans.
 *
 * @version 1.5
 * @since 1.0
 */
public class CreateLoans {

    /**
     * Main class
     *
     * @param args Not applicable
     */
    public static void main(String[] args) {

        boolean errPrimeIntRate = true;
        Scanner sc = new Scanner(System.in);
        String scIntRate = "";
        while (errPrimeIntRate) {
            try {
                double primeIntRate = 0;
                System.out.print("Please enter the prime interest rate: ");
                scIntRate = sc.next();
                System.out.println();
                primeIntRate = Double.valueOf(scIntRate);
                System.out.println("Please enter five loans : ");
                int loanCount = 1;
                double reqLoanAmt = 0;
                String loanType = "";
                String lastName = "";
                int loanTerm = 0;
                var loansList = new ArrayList<Loan>();
                errPrimeIntRate = false;
                while (loanCount <= 5) {
                    try {
                        System.out.println("Loan " + loanCount + ":");
                        System.out.print("\tPlease enter required loan amount: ");
                        reqLoanAmt = Double.valueOf(sc.next());
                        System.out.println();
                        if (reqLoanAmt > Loan.getMaxLoan()) {
                            System.out.println("\tRequired loan amount exceeded the permitted loan amount $" + String.format("%.0f", (double) LoanConstants.MAX_LOAN));
                            System.out.println();
                            continue;
                        }
                        System.out.print("\tPlease enter loan type 'B' for business or 'P' for personal: ");
                        loanType = sc.next();
                        if (!loanType.equals("B") && !loanType.equals("P")) {
                            System.out.println("\tLoan type must be a character B or P only");
                            System.out.println();
                            continue;
                        }
                        System.out.println();
                        System.out.print("\tPlease enter customer's last name: ");
                        lastName = sc.next();
                        System.out.println();
                        System.out.print("\tPlease enter loan term in number of years: ");
                        loanTerm = Integer.valueOf(sc.next());
                        System.out.println();
                        if (loanType.equals("B")) {
                            Loan newLoan = new BusinessLoan(lastName, reqLoanAmt, loanTerm, primeIntRate, loanType);
                            newLoan.setLoanTerm();
                            loansList.add(newLoan);
                        } else {
                            Loan newLoan = new PersonalLoan(lastName, reqLoanAmt, loanTerm, primeIntRate, loanType);
                            newLoan.setLoanTerm();
                            loansList.add(newLoan);
                        }

                        loanCount++;
                    } catch (Exception ex) {
                        System.out.println("\tInvalid input --> " + ex);
                        System.out.println();
                    }
                }
                storeDisplayLoans(loansList);

            } catch (Exception ex) {
                System.out.println("Invalid prime interest rate value " + "\"" + scIntRate + "\".");
                System.out.println("Try again");
                System.out.println();
            }
        }
    }

    /**
     * Store ArrayList<Loan> to binary file and display loan objects using
     * toString() method
     *
     * @param {@literal<Loan>}	Loan typed element
     */
    private static void storeDisplayLoans(ArrayList<Loan> loans) {
        var loansVector = new Vector<Loan>();
        for (var loan : loans) {
            loansVector.add(loan);
        }
        System.out.println("Writing to binary file: loans.bin");
        try {
            FileOutputStream fileOut = new FileOutputStream("loans.bin");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(loansVector);
            fileOut.close();
            System.out.println("Write completed - loans.bin");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println();

        System.out.println("Reading from binary file: loans.bin");
        var vIn = new Vector();
        try {
            FileInputStream fileIn = new FileInputStream("loans.bin");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            vIn = (Vector) objectIn.readObject();
            fileIn.close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
        System.out.println();

        for (int i = 0; i < vIn.size(); i++) {
            var loanObj = (Loan) vIn.elementAt(i);
            System.out.println(loanObj);
        }
    }
}
