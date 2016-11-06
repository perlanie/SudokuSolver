import java.io.*;
import java.util.*;

public class SudokuSolver{
	ArrayList<ArrayList<ArrayList<Integer>>> sudokuBoard;
	ArrayList<ArrayList<Integer>> executionQueue;
	LinkedList<ArrayList<Integer>> inferences;
	/*===========================
		SudokuSolver Constructor 
		============================*/
		public SudokuSolver(){
			this.sudokuBoard = new ArrayList<ArrayList<ArrayList<Integer>>>(9);
			this.executionQueue= new ArrayList<ArrayList<Integer>>();
			this.inferences=new LinkedList<ArrayList<Integer>>();

		}
	/*===============================================================
		copySudokuSolver: create a copy of the sudoku object
		================================================================*/
		public SudokuSolver copySudokuSolver(){
			SudokuSolver sudokuCopy = new SudokuSolver();

			for (int row = 0; row < 9; row++) {
				ArrayList<ArrayList<Integer>> sudokuRow =new ArrayList<ArrayList<Integer>>(9);
				for (int col = 0; col < 9; col++) {
				//System.out.println(this.getDomain(row,col));
					sudokuRow.add(this.getDomain(row,col));
				}
				sudokuCopy.sudokuBoard.add(sudokuRow);
			}


			for(int i=0;i<this.executionQueue.size();i++){
				System.out.println(this.executionQueue.get(i));
				if(this.executionQueue.size()>0){
					int r=this.executionQueue.get(i).get(0);
					int c=this.executionQueue.get(i).get(1);
					int val=this.executionQueue.get(i).get(2);
					sudokuCopy.executionQueue.add(new ArrayList<Integer>(3){{add(r);add(c);add(val);}});
				}

			}

			for(int j=0;j<this.inferences.size();j++){
				if(this.inferences.size()>0){
					int r=this.inferences.get(j).get(0);
					int c=this.inferences.get(j).get(1);
					int val=this.inferences.get(j).get(2);
					sudokuCopy.inferences.push(new ArrayList<Integer>(3){{add(r);add(c);add(val);}});
				}

			}

			System.out.println("\nOld Inferences");
			for(int l=0;l<sudokuCopy.inferences.size();l++){
				System.out.println(sudokuCopy.inferences.get(l));

			}

			return sudokuCopy;
		}

		public ArrayList<Integer> getDomain(int row, int col){
			return this.sudokuBoard.get(row).get(col);

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

							this.executionQueue.add(new ArrayList<Integer>(3){{add(row);add(col);add(value);}});

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

		// System.out.println("\nExecution Queue");
		// for(int i=0;i<executionQueue.size();i++){
		// 	System.out.println(executionQueue.get(i));

		// }
		// System.out.println("\nInitial SudokuSolver Board");
		// for(int j=0;j<9;j++){
		// 	System.out.println(this.sudokuBoard.get(j));

		// }

		}

	/*==============================================================
		ArcConsistency3: takes a value given and takes that value out of the 
		domain of the respectable variables.
		================================================================*/
		public boolean ArcConsistency3(){

			if(this.sudokuBoard.isEmpty()){
				System.out.println("Error: Please initialize the sudoku board with values.");
				return false;
			}
			int index=0;
			while (!this.executionQueue.isEmpty()){
				ArrayList<Integer> currentVariable=this.executionQueue.get(0);
				int row=currentVariable.get(0);
				int column=currentVariable.get(1);
				int value=currentVariable.get(2);
				ArrayList<Integer> currentVarDoms;

				if(this.getDomain(row,column).size()==0){
					return false;
				}
			//Takes out the chosen number out of the domain of the variables in the same row by checking each column in the row
				for (int c=0;c<9;c++){
				/*takes numbers out of the domain of the variables as long as its not in the same 
				row of the number*/
				if(c!=column){
					currentVarDoms=this.sudokuBoard.get(row).get(c);
					if(currentVarDoms.contains(value)&&currentVarDoms.size()!=1){
						
						currentVarDoms.remove(currentVarDoms.indexOf(value));
						int newC=c;
						if(currentVarDoms.size()==1){
							int newVar=currentVarDoms.get(0);
							if(newVar!=value){
								this.executionQueue.add(new ArrayList<Integer>(3){{add(row);add(newC);add(newVar);}});
							}
							else{
								return false;
							}
						}
					}
					else if(currentVarDoms.get(0)==value){
						return false;
					}
				}
			}

			
			//Takes out the chosen number out of the domain of the variables in the same column by checking each row in each column
			for (int r=0;r<9;r++){
				if(r!=row){
					currentVarDoms= this.sudokuBoard.get(r).get(column);
					if(currentVarDoms.contains(value)&&currentVarDoms.size()!=1){
						currentVarDoms.remove(currentVarDoms.indexOf(value));
						int newR=r;
						if(currentVarDoms.size()==1){
							int newVar=currentVarDoms.get(0);
							if(newVar!=value){
								this.executionQueue.add(new ArrayList<Integer>(3){{add(newR);add(column);add(newVar);}});
							}
							else{
								return false;
							}

							
						}
					}
					else if(currentVarDoms.get(0)==value){
						return false;
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
						currentVarDoms= this.sudokuBoard.get(x).get(y);
						if(currentVarDoms.contains(value)&&currentVarDoms.size()!=1){
							currentVarDoms.remove(currentVarDoms.indexOf(value));
							if(currentVarDoms.size()==1){
								int newVar=currentVarDoms.get(0);
								int newX=x;
								int newY=y;
								if(newVar!=value){
									this.executionQueue.add(new ArrayList<Integer>(3){{add(newX);add(newY);add(newVar);}});
								}
								else{
									return false;
								}


							}
						}
						else if(currentVarDoms.get(0)==value){
							return false;
						}	

					}

				}
			}
			this.executionQueue.remove(0);
			index++;
		}

		System.out.println("\nSudokuSolver Board After AC3");
		for(int j=0;j<9;j++){
			System.out.println(this.sudokuBoard.get(j));

		}
		return true;

	}

	// public boolean isSolved(){
	// 	for (int r=0;r<9;r++){
	// 		for(int c=0;c<9;c++){
	// 			if(this.sudokuBoard.get(r).get(c).size()>1){
	// 				return false;
	// 			}
	// 		}
	// 	}
	// 	return true;
	// }

	public ArrayList<Integer> selectUnassignedVar(SudokuSolver copy){
		for(int r=0; r<9;r++){
			for(int c=0;c<9;c++){
				int row=r;
				int col=c;
				if(copy.getDomain(r,c).size()>1){
					return (new ArrayList<Integer>(2){{add(row);add(col);}});
				}
			}
		}
		return null;
	}
	public ArrayList<Integer> orderDomainsValues(ArrayList<Integer> unassigned){
		return this.sudokuBoard.get(unassigned.get(0)).get(unassigned.get(1));
	}

	public SudokuSolver backTrackingSearch(SudokuSolver copy){
		SudokuSolver possibleSolution=backTrack(copy);
		return possibleSolution;
	}
	/*===================================================================
		backTracking: executing the AC3 algorithm on all the variables
		execution queue.
		====================================================================*/
		public SudokuSolver backTrack(SudokuSolver copy){
			boolean solution=false;

			// if(this.isSolved()){
			// 	solution=true;
			// 	return copy;
			// }

		ArrayList<Integer> unassigned = copy.selectUnassignedVar(copy);//index of a var with domain.size>1
		ArrayList<Integer> domainVals= copy.orderDomainsValues(unassigned);
		for(Integer val : domainVals){
			copy.inferences.push(new ArrayList<Integer>(){{add(unassigned.get(0));add(unassigned.get(1));add(val);}});
		}
		if(copy.inferences.isEmpty()||unassigned.get(0)!=(copy.inferences.get(0).get(0))&&unassigned.get(1)==(copy.inferences.get(0).get(1))){
			copy.inferences.pop();
			copy.inferences.pop();
			copy.inferences.pop();
			
		}
		
		
		

			SudokuSolver oldCopy=copy.copySudokuSolver();
			ArrayList<Integer> inferenceToApply=copy.inferences.pop();
			System.out.println(inferenceToApply);
			copy.sudokuBoard.get(inferenceToApply.get(0)).set(inferenceToApply.get(1),new ArrayList<Integer>(){{add(inferenceToApply.get(2));}});
			copy.executionQueue.add(inferenceToApply);
			SudokuSolver sudokuResult=null;
			System.out.println("copy inferences: "+copy.inferences);
			boolean ac3=copy.ArcConsistency3();
			if(ac3 && copy.validState()){
				sudokuResult=backTrack(copy);
			}
			else{
				System.out.println("old inferences: "+oldCopy.inferences);

				ArrayList<Integer> ignore=oldCopy.inferences.pop();
				sudokuResult=backTrack(oldCopy);
			}
			
			if(sudokuResult!=null){
				return sudokuResult;
			}


		
		return null;
		

	}


	public boolean validState(){
		//row check
		for(int row=0;row<9;row++){
			int[] values=new int[9];
			for(int col=0;col<9;col++){
				if(this.sudokuBoard.get(row).get(col).size()==1){
					values[col]=this.sudokuBoard.get(row).get(col).get(0);
				}
				else{
					values[col]=0;
				}
			}

			Arrays.sort(values);
			for(int i=1;i<9;i++){
				if (values[i] != 0 && values[i - 1] != 0 && values[i] == values[i - 1]) {
					return false;
				}
			}
		}

		//column check
		for(int col=0;col<9;col++){
			int[] values=new int[9];
			for(int row=0;row<9;row++){
				if(this.sudokuBoard.get(row).get(col).size()==1){
					values[col]=this.sudokuBoard.get(row).get(col).get(0);
				}
				else{
					values[col]=0;
				}
			}

			Arrays.sort(values);
			for(int i=1;i<9;i++){
				if (values[i] != 0 && values[i - 1] != 0 && values[i] == values[i - 1]) {
					return false;
				}
			}
		}


		//unit check
		for(int unitR=0; unitR<3;unitR++){
			for(int unitC=0;unitC<3;unitC++){
				int[] values=new int[9];
				int startR=unitR*3;
				int startC=unitC*3;
				int endR=unitR+3;
				int endC=unitC+3;
				int index=0;

				for(int r=startR;r<endR;r++){	
					for(int c=startC;c<endC;c++){
						if(this.sudokuBoard.get(r).get(c).size()==1){
							values[index]=this.sudokuBoard.get(r).get(c).get(0);
						}
						else{
							values[index]=0;
						}
						index++;
							
					}	
				}
				Arrays.sort(values);
				for(int i=1;i<index;i++){
					if (values[i] != 0 && values[i - 1] != 0 && values[i] == values[i - 1]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/*===========================
				main
				============================*/
				public static void main(String[] args){
					SudokuSolver currentSudokuSolver= new SudokuSolver();
					String path="/Users/Perlanie/Documents/Sudoku/JavaApp/sudoku.txt";
					currentSudokuSolver.inputInitialValues(path);
					currentSudokuSolver.ArcConsistency3();
					SudokuSolver copy=currentSudokuSolver.copySudokuSolver();
					SudokuSolver result=currentSudokuSolver.backTrackingSearch(copy);

				}







			}

