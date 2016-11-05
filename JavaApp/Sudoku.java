import java.io.*;
import java.util.*;

public class Sudoku{
	private ArrayList<ArrayList<ArrayList<Integer>>> sudokuBoard;
	private ArrayList<ArrayList<Integer>> executionQueue;
	/*===========================
		Sudoku Constructor 
	============================*/
	public Sudoku(){
		sudokuBoard = new ArrayList<ArrayList<ArrayList<Integer>>>(9);
		executionQueue= new ArrayList<ArrayList<Integer>>();
		
	}
	/*===========================
		Sudoku Copy Constructor 
	============================*/
	public Sudoku(Sudoku copy){
		sudokuBoard=copy.sudokuBoard;
		executionQueue=copy.executionQueue;
	}
	
	/*==============================================================
		inputInitialValues: takes in a file to input the value used
		for the sudoku board.
	----------------------------------------------------------------
		filePath: The path of the txt file with the information for 
		the sudoku.
	================================================================*/
	public void inputInitialValues(String filePath){
		String line = null;
		BufferedReader reader=null;
		try{
			FileReader file = new FileReader(filePath);
			reader = new BufferedReader(file);
		}
		catch(FileNotFoundException f){
			System.out.println("Error: File not found");
		}
		 
		try{
			int rowIndex=0;

			while((line = reader.readLine()) != null){
				String[] sudokuRow=line.split(" ");
				ArrayList<ArrayList<Integer>> boardRow=new ArrayList<ArrayList<Integer>>(9);

				for(int i=0;i<9;i++){
					int value=Integer.parseInt(sudokuRow[i]);
					if(value==0){
						boardRow.add(new ArrayList<Integer>() {{add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);}});
					}
					else{
						int row=rowIndex;
						int col=i;

						executionQueue.add(new ArrayList<Integer>(3){{add(row);add(col);add(value);}});
						
						boardRow.add(new ArrayList<Integer>(1){{add(value);}});
					}
					
				}
				sudokuBoard.add(boardRow);
				
				rowIndex++;
			}
			reader.close();
		}
		catch (IOException e){
			System.out.println("Error: Cannot readfile");
		}

		System.out.println("\nExecution Queue");
		for(int i=0;i<executionQueue.size();i++){
			System.out.println(executionQueue.get(i));

		}
		System.out.println("\nInitial Sudoku Board");
		for(int j=0;j<9;j++){
			System.out.println(sudokuBoard.get(j));

		}
		
	}

	/*==============================================================
		ArcConsistency3: takes a value given and takes that value out of the 
		domain of the respectable variables.
	================================================================*/
	public void ArcConsistency3(){

		if(sudokuBoard.isEmpty()){
			System.out.println("Error: Please initialize the sudoku board with values.");
			return;
		}
		int index=0;
		while (!executionQueue.isEmpty()){
			ArrayList<Integer> currentVariable=executionQueue.get(0);
			int row=currentVariable.get(0);
			int column=currentVariable.get(1);
			int value=currentVariable.get(2);
			ArrayList<Integer> currentVarDoms;
			
			//Takes out the chosen number out of the domain of the variables in the same row by checking each column in the row
			for (int c=0;c<9;c++){
				/*takes numbers out of the domain of the variables as long as its not in the same 
				row of the number*/
				if(c!=column){
					currentVarDoms=sudokuBoard.get(row).get(c);
					if(currentVarDoms.contains(value)&&currentVarDoms.size()!=1){
						
						currentVarDoms.remove(currentVarDoms.indexOf(value));
						int newC=c;
						if(currentVarDoms.size()==1){
							int newVar=currentVarDoms.get(0);
							executionQueue.add(new ArrayList<Integer>(3){{add(row);add(newC);add(newVar);}});
							
						}
					}
				}
			}

			
			//Takes out the chosen number out of the domain of the variables in the same column by checking each row in each column
			for (int r=0;r<9;r++){
				if(r!=row){
					currentVarDoms= sudokuBoard.get(r).get(column);
					if(currentVarDoms.contains(value)&&currentVarDoms.size()!=1){
						currentVarDoms.remove(currentVarDoms.indexOf(value));
						int newR=r;
						if(currentVarDoms.size()==1){
							int newVar=currentVarDoms.get(0);
							executionQueue.add(new ArrayList<Integer>(3){{add(newR);add(column);add(newVar);}});
							
						}
					}
				}
		
			}

			//Takes out the chosen number out of the domain of the variables in the same unit
			int startRow = (row / 3) * 3;
			int startColumn = (column / 3) * 3;
			int endRow = startRow + 3;
			int endColumn = startColumn + 3;
			
			for(int x=startRow;x<endRow;x++){
				for(int y=startColumn;y<endColumn;y++){
					
						if(x!=row && y!=column){
							currentVarDoms= sudokuBoard.get(x).get(y);
							if(currentVarDoms.contains(value)&&currentVarDoms.size()!=1){
								currentVarDoms.remove(currentVarDoms.indexOf(value));
					
							}	
				
						}
						
				}
			}
			executionQueue.remove(0);
			index++;
		}

		System.out.println("\nSudoku Board After AC3");
		for(int j=0;j<9;j++){
			System.out.println(sudokuBoard.get(j));

		}

	}

	/*===================================================================
		backTracking: executing the AC3 algorithm on all the variables
		execution queue.
	====================================================================*/
	public void backTracking(Sudoku copy){
		boolean solution=false;
		while(!solution){
		// 	for(ArrayList<Integer> domainValue: copy.sudokuBoard.get(i) )
		// 	copy.executionQueue.
		// 
		}
		

	}

	/*===========================
				main
	============================*/
	public static void main(String[] args){
		Sudoku currentSudoku= new Sudoku();
		String path="//Users/Perlanie/Documents/Sudoku/JavaApp/sudoku.txt";
		currentSudoku.inputInitialValues(path);
		currentSudoku.ArcConsistency3();
	}







}

