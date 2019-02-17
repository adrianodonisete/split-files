import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int numberFilesSplit = 10, numberLinesPerFile = 0;
		int linesCount = 0, nameCount = 1, totalLines = 0;
		
		System.out.println("Total files: ");
		numberFilesSplit = sc.nextInt();

		String path = "c:\\nuvem\\googledriver\\ti\\java\\files\\update_email_mailing.txt";
//		String path = "c:\\nuvem\\googledriver\\ti\\java\\files\\out.txt";

		File sourceFile = new File(path);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		
		try (BufferedReader br1 = new BufferedReader(new FileReader(path))) {
			totalLines = (int) br1.lines().count();		
			System.out.println("Total lines: "+totalLines);
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		System.out.printf("%.3f", (double) ((double) totalLines / (double) numberFilesSplit));
		System.out.println();
		
		numberLinesPerFile = (int) Math.ceil((double) totalLines / (double) numberFilesSplit);
		System.out.println("Lines per file: "+numberLinesPerFile+"\n");
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			
			while (line != null) {

				String targetFileStr = sourceFolderStr + "\\out\\summary_"+nameCount+".csv";
				
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr, true))) {
					bw.write(line);
					bw.newLine();
					
				} catch (IOException e) {
					System.out.println("Error writing file: " + e.getMessage());
				}				
				
				line = br.readLine();
				linesCount ++;
				
				if (linesCount % numberLinesPerFile == 0) {
					System.out.println(linesCount);
					nameCount++;
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		System.out.print("End");
		sc.close();	
	}

}
