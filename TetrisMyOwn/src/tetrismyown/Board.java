/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismyown;

/**
 *
 * @author justin
 */
class Board {

    private final int WIDTH;
    private final int HEIGHT;
    private boolean[][] grid;
    private int[] widthOfRows;
    private int[] heightOfColumes;
    /*TODO: where to initalise "maxHeight", where it is declared or where it is needed, or in the constructor? */
    private int maxHeight;

    private final boolean DEBUG = false;

    private Board(int widthOfBoard, int heightOfBoard) {
        WIDTH = widthOfBoard;
        HEIGHT = heightOfBoard;
        grid = new boolean[widthOfBoard][heightOfBoard];

        widthOfRows = new int[heightOfBoard];
        heightOfColumes = new int[widthOfBoard];

    }

    private static final int PLACE_OK = 0;
    private static final int PLACE_ROW_FILLED = 1;
    private static final int PLACE_OUT_OF_BOUNDS = 2;
    private static final int PLACE_BAD = 3;

    int place(Piece placedPiece, int xCoordinateOnBoard, int yCoordinateOnBoard) {
        int result = PLACE_OK;
        int placedPieceXCoordinate;
        int placePieceYCoordinate;

        TPoint[] body = placedPiece.getBody();
        for (int i = 0; i < body.length; i++) {

            placedPieceXCoordinate = xCoordinateOnBoard + body[i].x;
            placePieceYCoordinate = yCoordinateOnBoard + body[i].y;

            if (placedPieceXCoordinate < 0 || placePieceYCoordinate < 0
                    || placedPieceXCoordinate >= WIDTH || placePieceYCoordinate >= HEIGHT) {
                result = PLACE_OUT_OF_BOUNDS;

                //TODO: what if instead of "break;", I used "return;" instead, does it make any difference? 
                break;
            }

            if (grid[placedPieceXCoordinate][placePieceYCoordinate]) {
                result = PLACE_BAD;

                //TODO: what if instead of "break;", I used "return;" instead, does it make any difference? 
                break;
            }

            grid[placedPieceXCoordinate][placePieceYCoordinate] = true;

            //"heightOfColume" is intialised here, because only after a piece has been place,
            //should we be able to calculate "heightOfColumes"
            if (heightOfColumes[placedPieceXCoordinate] < placePieceYCoordinate + 1) {
                heightOfColumes[placedPieceXCoordinate] = placePieceYCoordinate + 1;
            }

            widthOfRows[placePieceYCoordinate]++;

            if (widthOfRows[placePieceYCoordinate] == WIDTH) {
                result = PLACE_ROW_FILLED;
            }
        }

        return result;
    }
    
    void clearRow(){
        //"numberOfRowsCleared" is used recalculate "maxHeight" 
        int numberOfRowsCleared = 0;
        //is "hasFilledRow" redundant 
        boolean hasFilledRow = false;
        int rowTo, rowFrom;
  

        // when "rowFrom < maxHeight", simply fill all rows above "rowTo" to false 
        //note: "rowFrom" is index and starts from 0, "maxHeight" starts at 1
        for (rowTo = 0, rowFrom = 1; rowFrom < maxHeight; rowTo++, rowFrom++) {
            if(widthOfRows[rowTo] == WIDTH){
                //this if statment should only ever be executed once, i.e. when the first filled row
                //is detected, which in turn means that "hasFilledRow" should still be false 
                if(!hasFilledRow){
                    hasFilledRow = true;
                    numberOfRowsCleared++;
                }
                while (widthOfRows[rowFrom] == WIDTH) {
                    numberOfRowsCleared++;
                    rowFrom++;
                }
                if (hasFilledRow) {
                    copyRow(rowTo, rowFrom);
                }
            }
        }
        
        fillEmptyRows(rowTo, rowFrom);
        
        //TODO: need to recompute "maxHeight" here, can simply use "computeMaxHeight()" method
        //or come up with a quicker way to recompute "maxHeight" 
        computeMaxHeight();
    }

    private int computeMaxHeight(){
        //this is where "maxHeight" is initialised. 
        maxHeight = 0;
        for(int i = 0; i < heightOfColumes.length; i++){
            if(maxHeight < heightOfColumes[i]){
                maxHeight = heightOfColumes[i];
            }
        }
        return maxHeight;
    }
    
    private void copyRow(int rowTo, int rowFrom){
        for(int i = 0; i < WIDTH; i++){
            grid[i][rowTo] = grid[i][rowFrom]; 
        }
    }

    private void fillEmptyRows(int rowTo, int rowFrom) {
        //"rowTo += 1" here because the original "rowTo" is copied from the original "rowFrom",
        //therefore we need to add 1 to "rowTo" so we are filling rows all rows that are 
        //previously occupied but is empty now 
        for(rowTo += 1; rowTo <= rowFrom; rowTo++){
            for(int i = 0; i < WIDTH; i++){
                grid[i][rowTo] = false;
            }
        }
    }
    
    int dropHeight(Piece piece, int xCoordinateOfLowerLeftCornerOfThePieceOnBoard){
        
        int dropStopHeightInYCoordinate = 0;
        
        int[] skirtOfPiece = piece.getSkirt();
        for(int i = 0; i < skirtOfPiece.length; i++){
            int heightOfColumeAtXCoordinate = heightOfColumes[xCoordinateOfLowerLeftCornerOfThePieceOnBoard + i];
            int heightOfColumeAtXCoordinateAfterPieceDropped = heightOfColumeAtXCoordinate + 1;
            if(heightOfColumeAtXCoordinateAfterPieceDropped > dropStopHeightInYCoordinate){
                dropStopHeightInYCoordinate = heightOfColumeAtXCoordinateAfterPieceDropped;
            }
        }
        
        return dropStopHeightInYCoordinate;
    }
    
    
//Wrong logic, but interesting to know why    
//    int dropHeight(Piece piece, int xCoordinate){
//        int dropStopHeight = 0;
//        TPoint[] body = piece.getBody();
//        for(int i = 0; i < body.length; i++){
//            int xCoordinateOfTPointOnBoard = body[i].x + xCoordinate;
//            int heightOfColumeAtXCoordinateAfterDrop = heightOfColumes[xCoordinateOfTPointOnBoard] + 1;
//            if(heightOfColumeAtXCoordinateAfterDrop > dropStopHeight){
//                dropStopHeight = heightOfColumeAtXCoordinateAfterDrop;
//            }
//        }
//        return dropStopHeight;
//    }
//    

}
