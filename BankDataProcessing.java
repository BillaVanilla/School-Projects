package Lab8_DataProcess;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
public class BankDataProcessing {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
	static ArrayList<BankAccount> Accounts = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			Path path = Paths.get("src/AccountData.csv");
			readAccounts(path, true);

			path = Paths.get("src/BankData.csv");
			readTransactions(path, true, Accounts);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void readAccounts(Path Xpath, boolean bHead) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Xpath);
		try{
			String line;
			if(bHead) {
				line=bufferedReader.readLine();
			}
			while ((line = bufferedReader.readLine()) != null) {
				
				String[] acctArray=line.split(","); //name,acctnum
				BankAccount tempAcct = new BankAccount(acctArray[0], Integer.parseInt(acctArray[1]));
			    Accounts.add(tempAcct);
			}
		}
		catch (IOException ioe) {
			bufferedReader.close();
			ioe.printStackTrace();
		}
		finally {
			bufferedReader.close();
		}
	}
	private static void readTransactions(Path Xpath, boolean bHead, ArrayList<BankAccount> Xaccounts) throws IOException {
		
		try{
			String line;
			String transtype="";
			double balance=0.0;

			for(BankAccount b:Xaccounts) {
				BufferedReader bufferedReader = Files.newBufferedReader(Xpath);
				if(bHead) {
					line=bufferedReader.readLine();
				}
				System.out.println("Account for "+b.getAcctname()+", "+b.getAcctnum());
				while ((line = bufferedReader.readLine()) != null) {
					
					String[] acctArray=line.split(","); // acctnum,date,transtype,amount

				    if(b.getAcctnum()==Integer.parseInt(acctArray[0])) {
				    	if(acctArray[2].toLowerCase().equals("c")) {
				    		transtype="Credit";
				    		balance += Double.parseDouble(acctArray[3]);
				    	}
				    	else {
				    		transtype="Debit";
				    		balance -= Double.parseDouble(acctArray[3]);
				    	}
				    	System.out.println(" Transaction date: "+acctArray[1]+", "+transtype+"= "+acctArray[3]);
				    }
				}
				b.setBalance(balance);
				System.out.println(b);
				System.out.println("------------------------------------------------------------");
				balance=0.0;
				bufferedReader.close();
			}
		}
		catch (IOException ioe) {

			ioe.printStackTrace();
		}
	}
	
}


