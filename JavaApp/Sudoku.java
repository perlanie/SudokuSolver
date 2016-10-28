import java.io.*;
import java.util.*;

public class Sudoku{
	
	public Sudoku(){
		int[] c1r1={1,2,3,4,5,6,7,8,9};
		int[] c1r2={1,2,3,4,5,6,7,8,9};
		int[] c1r3={1,2,3,4,5,6,7,8,9};
		int[] c1r4={1,2,3,4,5,6,7,8,9};
		int[] c1r5={1,2,3,4,5,6,7,8,9};
		int[] c1r6={1,2,3,4,5,6,7,8,9};
		int[] c1r7={1,2,3,4,5,6,7,8,9};
		int[] c1r8={1,2,3,4,5,6,7,8,9};
		int[] c1r9={1,2,3,4,5,6,7,8,9};

		int[] c2r1={1,2,3,4,5,6,7,8,9};
		int[] c2r2={1,2,3,4,5,6,7,8,9};
		int[] c2r3={1,2,3,4,5,6,7,8,9};
		int[] c2r4={1,2,3,4,5,6,7,8,9};
		int[] c2r5={1,2,3,4,5,6,7,8,9};
		int[] c2r6={1,2,3,4,5,6,7,8,9};
		int[] c2r7={1,2,3,4,5,6,7,8,9};
		int[] c2r8={1,2,3,4,5,6,7,8,9};
		int[] c2r9={1,2,3,4,5,6,7,8,9};

		int[] c3r1={1,2,3,4,5,6,7,8,9};
		int[] c3r2={1,2,3,4,5,6,7,8,9};
		int[] c3r3={1,2,3,4,5,6,7,8,9};
		int[] c3r4={1,2,3,4,5,6,7,8,9};
		int[] c3r5={1,2,3,4,5,6,7,8,9};
		int[] c3r6={1,2,3,4,5,6,7,8,9};
		int[] c3r7={1,2,3,4,5,6,7,8,9};
		int[] c3r8={1,2,3,4,5,6,7,8,9};
		int[] c3r9={1,2,3,4,5,6,7,8,9};

		int[] c4r1={1,2,3,4,5,6,7,8,9};
		int[] c4r2={1,2,3,4,5,6,7,8,9};
		int[] c4r3={1,2,3,4,5,6,7,8,9};
		int[] c4r4={1,2,3,4,5,6,7,8,9};
		int[] c4r5={1,2,3,4,5,6,7,8,9};
		int[] c4r6={1,2,3,4,5,6,7,8,9};
		int[] c4r7={1,2,3,4,5,6,7,8,9};
		int[] c4r8={1,2,3,4,5,6,7,8,9};
		int[] c4r9={1,2,3,4,5,6,7,8,9};

		int[] c5r1={1,2,3,4,5,6,7,8,9};
		int[] c5r2={1,2,3,4,5,6,7,8,9};
		int[] c5r3={1,2,3,4,5,6,7,8,9};
		int[] c5r4={1,2,3,4,5,6,7,8,9};
		int[] c5r5={1,2,3,4,5,6,7,8,9};
		int[] c5r6={1,2,3,4,5,6,7,8,9};
		int[] c5r7={1,2,3,4,5,6,7,8,9};
		int[] c5r8={1,2,3,4,5,6,7,8,9};
		int[] c5r9={1,2,3,4,5,6,7,8,9};

		int[] c6r1={1,2,3,4,5,6,7,8,9};
		int[] c6r2={1,2,3,4,5,6,7,8,9};
		int[] c6r3={1,2,3,4,5,6,7,8,9};
		int[] c6r4={1,2,3,4,5,6,7,8,9};
		int[] c6r5={1,2,3,4,5,6,7,8,9};
		int[] c6r6={1,2,3,4,5,6,7,8,9};
		int[] c6r7={1,2,3,4,5,6,7,8,9};
		int[] c6r8={1,2,3,4,5,6,7,8,9};
		int[] c6r9={1,2,3,4,5,6,7,8,9};

		int[] c7r1={1,2,3,4,5,6,7,8,9};
		int[] c7r2={1,2,3,4,5,6,7,8,9};
		int[] c7r3={1,2,3,4,5,6,7,8,9};
		int[] c7r4={1,2,3,4,5,6,7,8,9};
		int[] c7r5={1,2,3,4,5,6,7,8,9};
		int[] c7r6={1,2,3,4,5,6,7,8,9};
		int[] c7r7={1,2,3,4,5,6,7,8,9};
		int[] c7r8={1,2,3,4,5,6,7,8,9};
		int[] c7r9={1,2,3,4,5,6,7,8,9};

		int[] c8r1={1,2,3,4,5,6,7,8,9};
		int[] c8r2={1,2,3,4,5,6,7,8,9};
		int[] c8r3={1,2,3,4,5,6,7,8,9};
		int[] c8r4={1,2,3,4,5,6,7,8,9};
		int[] c8r5={1,2,3,4,5,6,7,8,9};
		int[] c8r6={1,2,3,4,5,6,7,8,9};
		int[] c8r7={1,2,3,4,5,6,7,8,9};
		int[] c8r8={1,2,3,4,5,6,7,8,9};
		int[] c8r9={1,2,3,4,5,6,7,8,9};

		int[] c9r1={1,2,3,4,5,6,7,8,9};
		int[] c9r2={1,2,3,4,5,6,7,8,9};
		int[] c9r3={1,2,3,4,5,6,7,8,9};
		int[] c9r4={1,2,3,4,5,6,7,8,9};
		int[] c9r5={1,2,3,4,5,6,7,8,9};
		int[] c9r6={1,2,3,4,5,6,7,8,9};
		int[] c9r7={1,2,3,4,5,6,7,8,9};
		int[] c9r8={1,2,3,4,5,6,7,8,9};
		int[] c9r9={1,2,3,4,5,6,7,8,9};

		int[][] board= new int [9][9];

	}
	private void inputInitialValues(String filePath){
		String line = null;
		FileReader file = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(file); 

		int index=0;
		while((line = reader.readLine()) != null){
			String row="";
			String[] sudokuRow=line.split(" ");
			for(int i=0;i<9;i++){
				this.board[index][i]=sudokuRow[i];
				row = row + sudokuRow[i];
			}
			System.out.println(row);
			index++;
		}
		reader.close();

	}

	public void changeDomains(int enteredValue, int row, int column){

	}

	public void main(String[] args){
		String path="/Users/Perlanie/Dropbox/Work/04_Fall2016/CP468-ArtifcialIntelligence/Assignments/A2/JavaApp/sudoku.txt";
		inputInitialValues(path);
	}







}

