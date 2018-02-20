package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
import java.util.Random;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void rectangleReflection()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  int height = (int) ((int)pixels.length * .8);
	  int width = 40;
	  
	  for (int row = 0; row < height; row++)
	  {
		  for (int col = 0; col < width; col++)
		  {
			  leftPixel = pixels[row][col];
			  rightPixel = pixels[row][width - 1 - col];
			  rightPixel.setColor(leftPixel.getColor());
		  }
	  }
	  
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorSwan()
  {
    int mirrorPoint = 250;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 65; row < 280; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 200; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  public void glitchFilterShift()
  {
	  Pixel [][] pixels = this.getPixels2D();
	  int shiftAmount = (int) (.33 * pixels[0].length);
	  int width = pixels[0].length;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  Color [] currentColors = new Color[pixels[0].length];
				  
		  for (int col = 0; col < pixels[row].length; col++)
		  {
			  currentColors[col] = pixels[row][col].getColor();
		  }
		  
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setColor(currentColors[(col + shiftAmount) % width]);
		  }
	  }
	  
	  
  }
  
  public void glitchFilterChunk()
  {
	  Pixel [][] pixels = this.getPixels2D();
	  
	  Random randomNum = new Random();
	  
	  int firstBoxStartRow = 0;
	  int firstBoxStartCol = 0;
	  int firstBoxEndRow = 0;
	  int firstBoxEndCol = 0;
	  
	  int secondBoxStartRow = 0;
	  int secondBoxStartCol = 0;
	  
	  Color currentColor = null;
	  int currentColorRed = -1;
	  int currentColorBlue = -1;
	  
	  int pictureHeight = pixels.length;
	  int pictureWidth = pixels[0].length;
	  
	  boolean rowPass = false;
	  boolean colPass = false;
	  
	  boolean rowPass2 = false;
	  boolean colPass2 = false;
	  
	  firstBoxStartRow = 0 + randomNum.nextInt(pictureHeight - 1);
	  firstBoxStartCol = 0 + randomNum.nextInt(pictureWidth - 1);
	  
	  firstBoxEndRow = firstBoxStartRow + 100;
	  firstBoxEndCol = firstBoxStartCol + 100;
	  
	  while (rowPass == false)
	  {
		  if (firstBoxEndRow >= pictureHeight)
		  {
			  firstBoxStartRow = firstBoxStartRow - 5;
			  firstBoxEndRow = firstBoxEndRow - 5;
		  }
		  else
		  {
			  rowPass = true;
		  } 
	  }
	  
	  while (colPass == false)
	  {
		  if (firstBoxEndCol >= pictureWidth)
		  {
			  firstBoxStartCol = firstBoxStartCol - 5;
			  firstBoxEndCol = firstBoxEndCol - 5;
		  }
		  else
		  {
			  colPass = true;
		  } 
	  }
	  
	  secondBoxStartRow = 0 + randomNum.nextInt(pictureHeight - 1);
	  secondBoxStartCol = 0 + randomNum.nextInt(pictureWidth - 1);
	  
	  while (rowPass2 == false)
	  {
		  if (secondBoxStartRow + 100 >= pictureHeight)
		  {
			  secondBoxStartRow = secondBoxStartRow - 5;
		  }
		  else
		  {
			  rowPass2 = true;
		  } 
	  }
	  
	  while (colPass2 == false)
	  {
		  if (secondBoxStartCol + 100 >= pictureWidth)
		  {
			  secondBoxStartCol = firstBoxStartCol - 5;
		  }
		  else
		  {
			  colPass2 = true;
		  } 
	  }
	  
	  
	  
	  for (int firstBoxCurrentRow = firstBoxStartRow, secondBoxCurrentRow = secondBoxStartRow; firstBoxCurrentRow < firstBoxEndRow; firstBoxCurrentRow++, secondBoxCurrentRow++)
	  {
		  for (int firstBoxCurrentCol = firstBoxStartCol, secondBoxCurrentCol = secondBoxStartCol; firstBoxCurrentCol < firstBoxEndCol; firstBoxCurrentCol++, secondBoxCurrentCol++)
		  {
			  currentColor = pixels[firstBoxCurrentRow][firstBoxCurrentCol].getColor();
			  currentColorBlue = currentColor.getBlue();
			  currentColorRed = currentColor.getRed();
			  pixels[secondBoxCurrentRow][secondBoxCurrentCol].setBlue(currentColorBlue);
			  pixels[secondBoxCurrentRow][secondBoxCurrentCol].setRed(currentColorRed);
		  }
	  }
  }
  
  public void randomRectangles()
  {
	  Pixel [][]pixels = this.getPixels2D();
	  
	  Random randomNum = new Random();
	  
	  int randomColorBlue = -1;
	  int randomColorRed = -1;
	  int randomColorGreen = -1;
	  
	  int pictureHeight = pixels.length;
	  int pictureWidth = pixels[0].length;
	  
	  int recStartRow = 0;
	  int recStartCol = 0;
	  int recEndRow = 0;
	  int recEndCol = 0;
	  
	  int recMaxHeight = 250;
	  int recMaxWidth = 150;
	  
	  int recMinHeight = 20;
	  int recMinWidth = 20;
	  
	  boolean rowPass = false;
	  boolean colPass = false;
	  
	  for (int index = 0; index < 4; index++)
	  {
		  recStartRow = 0;
		  recStartCol = 0;
		  recEndRow = 0;
		  recEndCol = 0;
		  rowPass = false;
		  colPass = false;
		  
		  recStartRow = 0 + randomNum.nextInt(pictureHeight - 1);
		  recStartCol = 0 + randomNum.nextInt(pictureWidth - 1);
		  
		  recEndRow = recStartRow + recMinHeight + randomNum.nextInt(recMaxHeight) - 1;
		  recEndCol = recStartCol + recMinWidth + randomNum.nextInt(recMaxWidth) - 1;
		  
		  while (rowPass == false)
		  {
			  if (recEndRow >= pictureHeight - 1)
			  {
				  recStartRow = recStartRow - 1;
				  recEndRow = recEndRow - 1;
			  }
			  else
			  {
				  rowPass = true;
			  } 
		  }
		  
		  while (colPass == false)
		  {
			  if (recEndCol >= pictureWidth - 1)
			  {
				  recStartCol = recStartCol - 1;
				  recEndCol = recEndCol - 1;
			  }
			  else
			  {
				  colPass = true;
			  } 
		  }
		  
		  for (int currentRow = recStartRow; currentRow < recEndRow; currentRow++)
		  {
			  for (int currentCol = recStartCol; currentCol < recEndCol; currentCol++)
			  {
				  randomColorRed = 0 + randomNum.nextInt(255);
				  randomColorGreen = 0 + randomNum.nextInt(255);
				  randomColorBlue = 0 + randomNum.nextInt(255);
				  Color randomColor = new Color(randomColorRed, randomColorGreen, randomColorBlue);
				  pixels[currentRow][currentCol].setColor(randomColor);
			  }
		  }
	  }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  public void kirbyFilter(int startRow, int startCol)
  {
	  Pixel fromPixel = null;
	  Pixel toPixel = null;
	  Picture kirby = new Picture("KirbyPlease.png");
	  Pixel [][] toPixels = this.getPixels2D();
	  Pixel [][] fromPixels = kirby.getPixels2D();
	  
	  int fromRow = 0;
	  for (int toRow = startRow; toRow < toPixels.length && fromRow < fromPixels.length; toRow++ )
	  {
		  int fromCol = 0;
		  for (int toCol = startCol; toCol < toPixels[0].length && fromCol < fromPixels[0].length; toCol++)
		  {
			  fromPixel = fromPixels[fromRow][fromCol];
			  toPixel = toPixels[toRow][toCol];
			  if (!fromPixel.isTransparent())
			  {
				  toPixel.setRed(fromPixel.getRed());
				  toPixel.setBlue(fromPixel.getBlue());
				  toPixel.setGreen(fromPixel.getGreen());
			  }
			  fromCol++;
			  
		  }
		  fromRow++;
		  
	  }
  }
  
} // this } is the end of class Picture, put all new methods before this
