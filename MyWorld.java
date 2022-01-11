import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
/**
 * My World ignitalizes varibles needed to create
 * the first instance of the world
 * Dear Theresa,
 * A Note to my Grader: Null pointers are found when certain lines of code are commented out
 * so it compiles I exempted them from the reader, and I will be upfront about the parts of 
 * code I was not able to get to run right. 1, removing or adding the viral load from the 
 *world causes errors in the actor class so as soon as it touches anything it will cause an 
 *error. 2 creating a boolean statement to catch neighboring birds, in the if statment caused 
 *another null pointer. Finally 3, passing the social distancing radius from the world class 
 *the bird class causes another error. Despite this parts of this program works, sorry for
 * the incompleteness within this program. If circumstances were different, you would have 
 * seen me in office hours until I completed all this but as you know that was not quite an
 * option. That still is no excuse for not reaching out more, so with that, enjoy playing 
 * the 'COVID-19' game bird eddition again and again. -belle
 * 
 * @author Stephen Blythe, Belle Erhardt  
 * @version 4/2020
 */
public class MyWorld extends World
    {
        private int sdr;
        private int speed;
        private CircleMeter lefty;
        private CircleMeter cm;
        private Bird bird;
        private ViralLoad vl;
     /**
     * Constructor for objects of class MyWorld.
     * This adds in the CircleMeter RectangleMeter
     * a ViralLoad, and sets the size of the world
     * upon the opening the program.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);//size of world 
        lefty = new CircleMeter(50, 90);//creates the circlemeter
        addObject(lefty, 25, this.getHeight()-15);//add the circlemeter to the world
        CircleMeter cm =  this.getObjects(CircleMeter.class).get(0); //grabs the circlemeter in the world       
        RectangleMeter lefty2= new RectangleMeter(100);//creates rectanglemeter
        addObject(lefty2, 10, this.getHeight()-85);//adds rectanglemeter to world
        addObject(new ViralLoad(cm), 400, 400);
        //addViralLoad();//calls method from world class
    }
     /**
     * addViralLoad(int, int)
     * this adds an instance of a viral load to the world
     * and attaches it to the circle meter in the world
     */
    public void addViralLoad()
    {
      addObject(new ViralLoad(cm), 400, 400);
    }
     /**
     * getSDR()
     * returns social distancing radius
     * 
     */
    public int getSDR()
    {
        return sdr;
    }
    /**
     * contaisVL()
     * returns true if VL is in the world
     */
    public boolean containsVl()
    {
        return(!getObjects(ViralLoad.class).isEmpty());
    }
     /**
     * worldHeight()
     * Returns World height in an int
     */
    public int worldHeight()
    {
        return getHeight();
    }  
    /**
     * act - 
     * When the L key is pressed the GUI will import 
     * a file and attempt to read it 
     * ignoring lines that start with *
     * lines that start with Bird will create a new inscance of a bird
     * lines starting with 
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("L")) //is it  "l" or "L"
        {
        FileDialog fd = null;
        fd = new FileDialog(fd, "Title for the GUI", FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();  //what
        String path = fd.getDirectory(); //where
        String fullName = path + filename;
        File myFile = new File(fullName);
        Scanner reader;  // declare
        try  // attempt the following code
        {
            reader = new Scanner(myFile); 
        }
        
        catch(FileNotFoundException e)  
        {
            System.out.println("User error on read file!");
            System.out.println(e);
            return;
        }
        while(reader.hasNext())
            {
          String first = reader.next();
          if (first.charAt(0)=='*')//ignores lines with *
          {
           String ignoreMe = reader.nextLine();
          }
            else if (first.equals("bird"))
                {
                   //Bird bird = new Bird();
                    int speed = reader.nextInt(); 
                    int xPos = reader.nextInt();
                    int yPos = reader.nextInt();
                    addObject(new Bird(speed), xPos, yPos); //null pointer
                }
                else if (first.equals("distance"))
                {
                    int sdr = reader.nextInt();
                }
                else if(!first.equals("distance") || !first.equals("bird") || !first.equals("*"))
                {
                    System.out.println("Unexpected token: " + first);
                }
            }
    }    
  }
}

