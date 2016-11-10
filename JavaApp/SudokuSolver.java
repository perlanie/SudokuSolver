import java.io.*;
import java.util.*;

public class SudokuSolver{
	public static LinkedList<Arc> executionQueue=new LinkedList<Arc>();

	public static class Arc{
		int varR;
		int varC;
		int otherVarR;
		int otherVarC;
		int val;

		public Arc(int varR, int varC, int otherVarR, int otherVarC,int val){
			this.varR=varR;
			this.varC=varC;
			this.otherVarR=otherVarR;
			this.otherVarC=otherVarC;
			this.val=val;
		}
	}

	public static void createArcs(ArrayList<Integer> sudokuVar,Sudoku sudoku){
		int row=sudokuVar.get(0);
		int col=sudokuVar.get(1);
		int val=sudokuVar.get(2);


		ArrayList<Integer> currentVarDoms;
		for (int c=0;c<9;c++){
			/*takes numbers out of the domain of the variables as long as its not in the same 
			row of the number*/
				if(c!=col){
					executionQueue.add(new Arc(row,col,row,c,val));
				}
			}

			
			//Takes out the chosen number out of the domain of the variables in the same column by checking each row in each column
			for (int r=0;r<9;r++){
				if(r!=row){
					executionQueue.add(new Arc(row,col,r,col,val));
				}
			}

			//Takes out the chosen number out of the domain of the variables in the same unit
			int startRow = (row / 3) * 3;
			int startColumn = (col / 3) * 3;
			int endRow = startRow + 3;
			int endColumn = startColumn + 3;
			
			for(int x=startRow;x<endRow;x++){
				for(int y=startColumn;y<endColumn;y++){
					
					if(x!=row && y!=col){
						executionQueue.add(new Arc(row,col,x,y,val));
					}
				}
			}

	}
	/*===============================================================
		populateQueue: adds proper variable pairs to the 
		execution queue.
	================================================================*/
	public static void populateQueue(Sudoku sudoku){

		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				ArrayList<Integer> varDom= sudoku.getDomain(i,j);
				if(varDom.size()==1){
					int row=i;
					int col=j;
					ArrayList<Integer> var=new ArrayList<Integer>(3){{add(row);add(col);add(varDom.get(0));}};
					createArcs(var, sudoku);

				}
				else{
					int row=i;
					int col=j;
					ArrayList<Integer> var=new ArrayList<Integer>(3){{add(row);add(col);add(0);}};
					createArcs(var, sudoku);
				}
			}
		}
	}
	/*==============================================================
	ArcConsistency3: takes a value given and takes that value out of the 
	domain of the respectable variables.
	--------------------------------------------------------------------
	sudoku: the sudoku board you would apply AC3 on
	================================================================*/
	public static void ArcConsistency3(Sudoku sudoku){

		if(sudoku.sudokuBoard.isEmpty()){
			System.out.println("Error: Please initialize the sudoku board with values.");
		}
		
		while (!executionQueue.isEmpty()){
			System.out.println("Number of Arcs in the queue: "+ executionQueue.size());
			Arc curArc=executionQueue.get(0);
			int row=curArc.varR;
			int col=curArc.varC;
			int otherRow=curArc.otherVarR;
			int otherCol=curArc.otherVarC;
			int value=curArc.val;

			ArrayList<Integer> otherDom=sudoku.getDomain(otherRow,otherCol);

			if(otherDom.size()!=1&&otherDom.contains(value)){
				sudoku.removeValFromDomain(otherRow,otherCol,value);
				if(otherDom.size()==1){
					ArrayList<Integer> var=new ArrayList<Integer>(3){{add(otherRow);add(otherCol);add(otherDom.get(0));}};
					createArcs(var,sudoku);
				}
			}
			executionQueue.remove(0);

		}
	}

	/*=====================================================================
	allVariablesFilled: checks if all the variables in the sudoku board 
	are filled with just one value in the domain
	-----------------------------------------------------------------------
	sudoku: the sudoku board you would like to check if all vars are filled
	======================================================================*/
	public static boolean allVariablesFilled(Sudoku sudoku){
		for (int r=0;r<9;r++){
			for(int c=0;c<9;c++){
				if(sudoku.getDomain(r,c).size()>1){
					return false;
				}
			}
		}
		return true;
	}

	/*===================================================================
	selectUnassignedVar: selects a variable(a box) without a set value
	--------------------------------------------------------------------
	sudoku: the sudoku board you would like to grab a unassigned var
	====================================================================*/
	public static ArrayList<Integer> selectUnassignedVar(Sudoku sudoku){
		for(int r=0; r<9;r++){
			for(int c=0;c<9;c++){
				int row=r;
				int col=c;
				if(sudoku.getDomain(r,c).size()>1){
					return (new ArrayList<Integer>(2){{add(row);add(col);}});
				}
			}
		}
		return null;
	}

	/*===================================================================
	orderDomainsValues: gets domain values of a given variable 
	--------------------------------------------------------------------
	sudoku: the sudoku board you would like to get the domain from
	unassigned: the coordinates of an unassigned variable
	====================================================================*/
	public static ArrayList<Integer> orderDomainsValues(ArrayList<Integer> unassigned, Sudoku sudoku){
		ArrayList<Integer> domains=sudoku.getDomain(unassigned.get(0),unassigned.get(1));
		return domains;
	}

	/*===================================================================
	backTrackingSearch: calls the backTrack function
	--------------------------------------------------------------------
	sudoku: the sudoku board you would like to apply the backTracking
	====================================================================*/
	public static Sudoku backTrackingSearch(Sudoku sudoku){
		System.out.println("\n====================================================");
		System.out.println("BACK TRACKING TO TRY AND FULLY SOLVE SUDOKU PUZZLE");
		System.out.println("====================================================\n");
		Sudoku possibleSolution=backTrack(sudoku);
		return possibleSolution;
	}
	/*===================================================================
	backTrack: executing the AC3 algorithm on all the variables
	execution queue.
	--------------------------------------------------------------------
	sudoku: the sudoku board you would like to apply the backTrack on.
	====================================================================*/
	public static Sudoku backTrack(Sudoku sudoku){

		if(allVariablesFilled(sudoku)){
			return sudoku;
		}
		ArrayList<Integer> unassigned = selectUnassignedVar(sudoku);//index of a var with domain.size>1
		ArrayList<Integer> domainVals = orderDomainsValues(unassigned, sudoku);

		for(Integer val :domainVals){
			System.out.println("\nBack tracking with following variable: ["+unassigned.get(0)+", "+unassigned.get(1)+", "+val+"]");
			Sudoku copy = sudoku.sudokuCopy();
			copy.setDomain(unassigned.get(0),unassigned.get(1),new ArrayList<Integer>(){{add(val);}});
			ArrayList<Integer> var=new ArrayList<Integer>(3){{add(unassigned.get(0));add(unassigned.get(1));add(val);}};
			createArcs(var,sudoku);

			ArcConsistency3(copy);//uses AC3 as an inference

			if(isValidState(copy)){
				Sudoku result=backTrack(copy);
				if (result != null) {
						return result;
				}
			}
		}

		
		return null;
	}

	/*===================================================================
	isValidState: checks if the sudoku is in a valid state, where all 
	variables satisfy the constraint.
	--------------------------------------------------------------------
	sudoku: the sudoku board you would like to check is in a valid state
	====================================================================*/
	public static boolean isValidState(Sudoku sudoku){
		//unit check
		for (int unitRow = 0; unitRow < 3; unitRow++) {
			for (int unitCol = 0; unitCol < 3; unitCol++) {
				int[] values = new int[9];
				int startRow = unitRow * 3;
				int startCol = unitCol * 3;
				int endRow = startRow + 3;
				int endCol = startCol + 3;
				int at = 0;
				for (int row = startRow; row < endRow; row++) {
					for (int col = startCol; col < endCol; col++) {
						values[at++] = sudoku.getValue(row, col);
					}
				}
				Arrays.sort(values);
				for (int i = 1; i < at; i++) {
					if (values[i] != 0 && values[i - 1] != 0 && values[i] == values[i - 1]) {
						return false;
					}
				}
			}
		}
		
		//row check
		for (int row = 0; row < 9; row++) {
			int[] values = new int[9];
			for (int col = 0; col < 9; col++) {
				values[col] = sudoku.getValue(row, col);
			}
			Arrays.sort(values);
			for (int i = 1; i < values.length; i++) {
				if (values[i] != 0 && values[i - 1] != 0 && values[i] == values[i - 1]) {
					return false;
				}
			}
		}

		//column check
		for (int col = 0; col < 9; col++) {
			int[] values = new int[9];
			for (int row = 0; row < 9; row++) {
				values[row] =sudoku.getValue(row, col);
				
			}
			Arrays.sort(values);
			for (int i = 1; i < values.length; i++) {
				if (values[i] != 0 && values[i - 1] != 0 && values[i] == values[i - 1]) {
					return false;
				}
			}
		}
			return true;
	}

	

	/*===========================
				main
	============================*/
	public static void main(String[] args){
		Sudoku currentSudoku= new Sudoku ();
		String path="/Users/Perlanie/Documents/Projects/Sudoku/JavaApp/sudoku.txt";
		currentSudoku.initSudoku(path);
		currentSudoku.printSudoku();
		populateQueue(currentSudoku);

		ArcConsistency3(currentSudoku);

		if(isValidState(currentSudoku)&&allVariablesFilled(currentSudoku)){
			System.out.println("\nSudoku Solver has successfully solved the puzzle using AC3.\n");
			currentSudoku.printSudoku();
		}
		else{
			System.out.println("\nSudoku After AC3");
			for(int i=0;i<9;i++){
				System.out.println(currentSudoku.sudokuBoard.get(i));
			}
			Sudoku copy=currentSudoku.sudokuCopy();
			Sudoku result=backTrackingSearch(copy);
			
			if(result!=null){
				if(isValidState(result)){
					System.out.println("\nSudoku Solver has successfully solved the puzzle using AC3 and Back Tracking.\n");
					result.printSudoku();
				}
				else{
					System.out.println("\nERROR: Sudoku Solver has reached an invalid state. No Solution.");
				}
			}
			else{
				System.out.println("\nERROR: Sudoku Solver has reached a null state. No Solution.");
			}
		
		}
		

	
	}







}
