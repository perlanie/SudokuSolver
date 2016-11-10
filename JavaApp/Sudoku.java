import java.io.*;
import java.util.*;

public class Sudoku{
	ArrayList<ArrayList<ArrayList<Integer>>>sudokuBoard;
	//LinkedList<ArrayList<Integer>> executionQueue;
	public Sudoku(){
		this.sudokuBoard = new ArrayList<ArrayList<ArrayList<Integer>>>(9);
		//this.executionQueue= new LinkedList<ArrayList<variable>>();
	}

	/*==============================================================
		initSudoku: takes in a file to input the value used
		for the sudoku board.
	----------------------------------------------------------------
		filePath: The path of the txt file with the information for 
		the sudoku.
	================================================================*/
	public void initSudoku(String filePath){
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

						//this.executionQueue.push(new Variable(row,col,new ArrayList<Integer>(1){{add(value);}}));
						//this.executionQueue.push(new ArrayList<Integer>(3){{add(row);add(col);add(value);}});

						boardRow.add(new ArrayList<Integer>(1){{add(value);}});
					}

				}
				this.sudokuBoard.add(boardRow);

				rowIndex++;
			}
			reader.close();
		}
		catch (IOException e){
			System.out.println("Error: Cannot readfile");
		}
	}
	

	/*===============================================================
		copySudokuSolver: create a copy of the sudoku object
	================================================================*/
	public Sudoku sudokuCopy(){
		Sudoku sudokuCopy = new Sudoku();

		for (int row = 0; row < 9; row++) {
			ArrayList<ArrayList<Integer>> sudokuRow =new ArrayList<ArrayList<Integer>>(9);
			for (int col = 0; col < 9; col++) {
				ArrayList<Integer> domain =new ArrayList<Integer>();
				ArrayList<Integer> oldDomain=this.getDomain(row,col);

				for(int d=0;d<oldDomain.size();d++){
					int val=oldDomain.get(d);
					domain.add(val);
				}

				sudokuRow.add(domain);
			}
			sudokuCopy.sudokuBoard.add(sudokuRow);
		}

		return sudokuCopy;
	}

	/*==============================================================
		getDomain: returns the domain of the variable at [row,col]
	----------------------------------------------------------------
		row: the x position of the variable
		col: the y position of the variable
	================================================================*/
	public ArrayList<Integer> getDomain(int row, int col){
		return this.sudokuBoard.get(row).get(col);

	}

	/*==============================================================
		setDomain: sets the domain of the variable at [row,col]
	----------------------------------------------------------------
		row: the x position of the variable
		col: the y position of the variable
	================================================================*/
	public void setDomain(int row, int col,ArrayList<Integer> domain){
		this.sudokuBoard.get(row).set(col,domain);

	}

	/*====================================================================
		removeValFromDomain: removes a value from the domain of a specified
		variable 
	----------------------------------------------------------------------
		row: the x position of the variable
		col: the y position of the variable
		value: value to be removed 
	=====================================================================*/
	public void removeValFromDomain(int row, int col, int value){
		ArrayList<Integer> domain=this.getDomain(row,col);
		if(domain.contains(value)&&domain.size()!=1){
			this.sudokuBoard.get(row).get(col).remove(domain.indexOf(value));

		}
	}

	/*====================================================================
		addValToDomain: removes a value from the domain of a specified
		variable 
	----------------------------------------------------------------------
		row: the x position of the variable
		col: the y position of the variable
		value: value to be removed 
	=====================================================================*/
	public void addValToDomain(int row, int col, int value){
		ArrayList<Integer> domain=this.getDomain(row,col);
		if(!domain.contains(value)){
			this.sudokuBoard.get(row).get(col).add(value);

		}
	}
	/*====================================================================
		getValue: gets the value of the domain. 0 if it is not assigned
	----------------------------------------------------------------------
		row: the x position of the variable
		col: the y position of the variable
	=====================================================================*/
	public int getValue(int row, int col){
		ArrayList<Integer>domain=this.getDomain(row,col);
		if(domain.size()==1){
			return (int)domain.get(0);
		}
		else{
			int val=0;
			return val;
		}
	}
	/*====================================================================
		printSudoku: prints the sudoku board
	=====================================================================*/
	public void printSudoku(){
		for(int r=0;r<9;r++){
			String row="";
			if((r%3==0)&&r!=8&&r!=0){
				System.out.println("----------------------------------");
			}
			for(int c=0;c<9;c++){
				if(this.getDomain(r,c).size()==1){
					row=row+this.getDomain(r,c);
				}
				else{
					row=row + "[ ]";
				}
				if(((c+1)%3==0)&&c!=8){
					row=row+" | ";
				}
			}
			System.out.println(row);
		}
		
	}


}