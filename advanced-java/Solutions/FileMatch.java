/**********************************************************
   This file contains the author's solution to exercise
   14.9.  Please note that our assigned version of the
   problem was slightly different.  Since you were to
   turn in only a single source code file, you would
   have had to include your main() method (the
   author creates a separate test driver, as usual).

   Also, the author uses separate classes for the
   record layouts (included in the chapter) but again,
   you should include the record layout fields in your
   single class file solution.

   Aside from those minor details, this solution demo-
   nstrates the sequencing logic that some students had
   difficulty mastering.
**********************************************************/

// Exercise 14.9 Solution: FileMatch.java
// Combine an account file with a transactions file into a
// new account file.
import java.io.File;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.IllegalFormatException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileMatch
{
   private static Scanner inOldMaster;
   private static Scanner inTransaction;
   private static Formatter outNewMaster;
   private static Formatter logFile;
   private static TransactionRecord transaction;
   private static AccountRecord account;

   public FileMatch()
   {
      transaction = new TransactionRecord();
      account = new AccountRecord();
   } // end FileMatch constructor

   public void openFiles()
   {
      try
      {
         // file streams for input and output files
         inOldMaster = new Scanner( new File( "oldmast.txt" ) );
         inTransaction = new Scanner( new File( "trans.txt" ) );
         outNewMaster = new Formatter( "newmast.txt" );
         logFile = new Formatter( "log.txt" );
      } // end try
      catch ( Exception exception )
      {
         System.err.println( "Error opening the files." );
      } // end catch
   } // end method openFiles

   public void processFiles()
   {
      int transactionAccountNumber;
      int accountNumber;
      
      try // block for reading/writing all records
      {
         // get a transaction record and its account number
         transaction = getTransactionRecord();
            
         // if the transaction is null, we are done
         if ( transaction == null )
            return;
            
         transactionAccountNumber = transaction.getAccount();
               
         // get an account record and its account number
         account = getAccountRecord();
            
         // if the account is null, we are done
         if ( account == null )
            return;
               
         accountNumber = account.getAccount();
           
         while ( accountNumber != 0 )
         {
            while ( accountNumber < transactionAccountNumber )
            {   
               // there is no transaction for this account
               outNewMaster.format( "%d %s %s %.2f\n",
                  account.getAccount(), account.getFirstName(),
                  account.getLastName(), account.getBalance() );
               
               account = getAccountRecord(); // get a new account

               if ( account == null )
                  return;
               
               accountNumber = account.getAccount();
            } // end while
            
            // if there is a transaction for this account
            if ( accountNumber == transactionAccountNumber )
            {
               while ( accountNumber == transactionAccountNumber )
               {
                  // combine the records
                  account.combine( transaction );

                  // get a new transaction
                  transaction = getTransactionRecord();
   
                  if ( transaction == null )
                     return;
             
                  transactionAccountNumber = transaction.getAccount();
               } // end while

               // write them to the master file
               outNewMaster.format( "%d %s %s %.2f\n",
                  account.getAccount(), account.getFirstName(),
                  account.getLastName(), account.getBalance() );
               
               // get a new account
               account = getAccountRecord();

               if ( account == null )
                  return;
            
               accountNumber = account.getAccount();
            } // end if
         
            while ( transactionAccountNumber < accountNumber )
            {
               // there is no account for this transaction
               logFile.format( "%s %d\n", 
                  "Unmatched transaction record for account number", 
                  transactionAccountNumber );
                  
               // get a new transaction
               transaction = getTransactionRecord();

               if ( transaction == null )
                  return;
               
               transactionAccountNumber = transaction.getAccount();
            } // end while
         } // end outer while
      } // end try
      catch ( FormatterClosedException closedException )
      {
         System.err.println(
            "Error writing to file - file has been closed." );
         System.exit( 1 );
      } // end catch
      catch ( IllegalFormatException formatException )
      {
         System.err.println( "Error with output." );
         System.exit( 1 );
      } // end catch
   } // end method processFiles

   public void closeFiles()
   {
      try // close the files
      {
         if ( inTransaction != null )
            inTransaction.close();

         if ( outNewMaster != null )
            outNewMaster.close();

         if ( inOldMaster != null )
            inOldMaster.close();

         if ( logFile != null )
            logFile.close();
      } // end try
      catch ( Exception exception )
      {
         System.err.println( "Error closing the files." );
         System.exit( 1 );
      } // end catch
   } // end method closeFiles

   // get a transaction record
   private TransactionRecord getTransactionRecord()
   {  
      // try to read the record
      try
      {
         if ( inTransaction.hasNext() )
         {
            transaction.setAccount( inTransaction.nextInt() );
            transaction.setAmount( inTransaction.nextDouble() );

            return transaction;
         } // end if
         else // we have hit end of transaction file
         {
            // these remaining accounts have
            while ( inOldMaster.hasNext() )
            {
               account.setAccount( inOldMaster.nextInt() );
               account.setFirstName( inOldMaster.next() );
               account.setLastName( inOldMaster.next() );
               account.setBalance( inOldMaster.nextDouble() );

               // store in new master
               outNewMaster.format( "%d %s %s %.2f\n",
                  account.getAccount(), account.getFirstName(),
                  account.getLastName(), account.getBalance() );
            } // end while
         } // end else
      } // end try
      catch ( FormatterClosedException closedException )
      {
         System.err.println(
            "Error writing to file - file has been closed." );
         System.exit( 1 );
      } // end catch
      catch ( IllegalFormatException formatException )
      {
         System.err.println( "Error with output." );
         System.exit( 1 );
      } // end catch
      catch ( NoSuchElementException elementException )
      {
         System.err.println( "Invalid input from file." );
      } // end catch
 
      // return null - no more records
      return null;
   } // end method getTransactionRecord

   // get an account record
   private AccountRecord getAccountRecord()
   {
      try // try to read an account record
      {
         if ( inOldMaster.hasNext() )
         {
            account.setAccount( inOldMaster.nextInt() );
            account.setFirstName( inOldMaster.next() );
            account.setLastName( inOldMaster.next() );
            account.setBalance( inOldMaster.nextDouble() );

            return account;
         } // end if
         else // we have hit end of old master file
         {
            logFile.format( "%s %d\n", 
               "Unmatched transaction record for account number", 
               transaction.getAccount() );

            // these records are transactions without accounts
            while ( inTransaction.hasNext() )
            {
               transaction.setAccount( inTransaction.nextInt() );
               transaction.setAmount( inTransaction.nextDouble() );
            } // end while
         } // end else
      } // end try
      catch ( FormatterClosedException closedException )
      {
         System.err.println(
            "Error writing to file - file has been closed." );
         System.exit( 1 );
      } // end catch
      catch ( IllegalFormatException formatException )
      {
         System.err.println( "Error with output." );
         System.exit( 1 );
      } // end catch
      catch ( NoSuchElementException elementException )
      {
         System.err.println( "Invalid input from file." );
      } // end catch

      return null;
   } // end method getAccountRecord
} // end class FileMatch

/*************************************************************************
* (C) Copyright 1992-2005 by Deitel & Associates, Inc. and               *
* Pearson Education, Inc. All Rights Reserved.                           *
*                                                                        *
* DISCLAIMER: The authors and publisher of this book have used their     *
* best efforts in preparing the book. These efforts include the          *
* development, research, and testing of the theories and programs        *
* to determine their effectiveness. The authors and publisher make       *
* no warranty of any kind, expressed or implied, with regard to these    *
* programs or to the documentation contained in these books. The authors *
* and publisher shall not be liable in any event for incidental or       *
* consequential damages in connection with, or arising out of, the       *
* furnishing, performance, or use of these programs.                     *
*************************************************************************/
