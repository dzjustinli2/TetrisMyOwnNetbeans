/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismyown;

import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author justin
 */
public class BoardTest {

    Board board;
    Piece pyramid;
    Piece pyramid2;
    Piece square;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        board = new Board(3, 6);
        pyramid = new Piece(Piece.PYRAMID_STRING);
        //should I make "computeNextRotation()" method in "Piece" class private? 
        pyramid2 = pyramid.computeNextRotation();
        square = new Piece(Piece.SQUARE_STRING);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getWIDTH method, of class Board.
     */
    @Test
    public void testGetWIDTH() {
        System.out.println("getWIDTH");
        int expResult = 3;
        int result = board.getWIDTH();
        assertEquals(expResult, result);
        assertThat(result, is(equalTo(3)));

    }

    /**
     * Test of getHEIGHT method, of class Board.
     */
    @Test
    public void testGetHEIGHT() {
        System.out.println("getHEIGHT");
        int expResult = 6;
        int result = board.getHEIGHT();
        assertEquals(expResult, result);
        assertThat(result, is(equalTo(6)));
    }

    /**
     * Test of getGrid method, of class Board.
     */
    @Test
    public void testGetGrid() {
        System.out.println("getGrid");

        board.place(pyramid, 0, 0);

        boolean[][] expResult = {
            {true, false, false, false, false, false},
            {true, true, false, false, false, false},
            {true, false, false, false, false, false}
        };
        boolean[][] result = board.getGrid();
//        assertTrue(Arrays.deepEquals(expResult, result));
        assertArrayEquals(expResult, result);
    }

//    /**
//     * Test of getWidthOfRows method, of class Board.
//     */
//    @Test
//    public void testGetWidthOfRows() {
//        System.out.println("getWidthOfRows");
//        Board instance = null;
//        int[] expResult = null;
//        int[] result = instance.getWidthOfRows();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHeightOfColumes method, of class Board.
//     */
//    @Test
//    public void testGetHeightOfColumes() {
//        System.out.println("getHeightOfColumes");
//        Board instance = null;
//        int[] expResult = null;
//        int[] result = instance.getHeightOfColumes();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMaxHeight method, of class Board.
//     */
//    @Test
//    public void testGetMaxHeight() {
//        System.out.println("getMaxHeight");
//        Board instance = null;
//        int expResult = 0;
//        int result = instance.getMaxHeight();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of place method, of class Board.
//     */
//    @Test
//    public void testPlace() {
//        System.out.println("place");
//        Piece placedPiece = null;
//        int xCoordinateOnBoard = 0;
//        int yCoordinateOnBoard = 0;
//        Board instance = null;
//        int expResult = 0;
//        int result = instance.place(placedPiece, xCoordinateOnBoard, yCoordinateOnBoard);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of clearRow method, of class Board.
//     */
//    @Test
//    public void testClearRow() {
//        System.out.println("clearRow");
//        Board instance = null;
//        instance.clearRow();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of dropHeight method, of class Board.
//     */
//    @Test
//    public void testDropHeight() {
//        System.out.println("dropHeight");
//        Piece piece = null;
//        int xCoordinateOfLowerLeftCornerOfThePieceOnBoard = 0;
//        Board instance = null;
//        int expResult = 0;
//        int result = instance.dropHeight(piece, xCoordinateOfLowerLeftCornerOfThePieceOnBoard);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of undo method, of class Board.
//     */
//    @Test
//    public void testUndo() {
//        System.out.println("undo");
//        Board instance = null;
//        instance.undo();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
