import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * Bird class gives the birds the characteristics of flying, spreading the virus,
 * and what to do when its life count becomes 0.
 * 
 * @author Belle Erhardt 
 * @version 4/2020
 */
public class Bird extends Actor
{
    private int lifeLeft;
    private int viralLoad;
    private int currentSpeed;
    private int vertSpeed;
    private int viralCount;
    private boolean alive;
    private int sdr; // might need to be in the world class
    private int counter;
    MyWorld World  = (MyWorld) getWorld();
    Random r = new Random();

     /**
     * Bird takes its speed
     * starts off with 50 life
     * 0 ViralLoad and is alive
     * and no vertical speed
     */
    public Bird(int speed) 
    {
     currentSpeed = speed; 
     lifeLeft = 50;
     viralLoad = 0;
     viralCount =0 ;
     alive = true;
     //sdr=World.getSDR();//null pointer
     counter=0;
     vertSpeed=0;
    } 
    /**
     * returns the current speed in the x direction of this Bird
     * @return the current x speed
     */
    public double getHorizontalSpeed()
    {
     double hSpeed = currentSpeed;
     return hSpeed;
    }
    
    // private method - so na Javadoc comment. 
    private void redraw()
    {
        // get an "empty" bird image
        setImage("bird.png");
        GreenfootImage pallette = getImage();

        // if bird is moving left, "flip" image so head and tail of bird
        //    appear in proper orientation
        if (getHorizontalSpeed()<0)
        {
            pallette.mirrorHorizontally();
        }
        
        // if the bird is carrying a viral Load ...
        if (getInfectionCount() > 0)
        {
            // add red viral load count in text to the right of the bird image
            pallette.setColor(Color.RED);
            pallette.drawString(""+ getInfectionCount(), 60, 25);
        } 
        
        // add life left count to the left of bird image in blue. 
        pallette.setColor(Color.BLUE);
        pallette.drawString(""+getLifeLeft() , 2, 25);
    }
    
    /**
     * returns this Bird's current viral load count
     * @return the viral load count
     */
    public int getInfectionCount()
    {
     return viralCount;
    }
    
    /**takes int of damage possible
     * returns this Bird's current viral load count
     * @return the viral load count
     */
    public void addInfection(int x)
    {
        if(viralCount+x <= 99)
        {
         viralCount += x;    
        }
        else if(viralCount+x >= 99)
        {
         viralCount = 99;       
        }
          
    }
        
    /**
     * returns this Bird's amount of life left
     * @return the amount of life left
     */
    public int getLifeLeft()
    {
        return lifeLeft;
    }
    
    /**
     * Act - 
     * if not alive, the bird goes no where
     * if its alive it moves horizonally, until its life left is 0 then it vertically moves
     * if at edge reverse speed
     * every 50 calls, if a bird has a Viral load. Decrease it by one.
     * every 10 calls, if a bird had Viral load larger than 50, and life left. decrease by one.
     * if bird has Viral load greater than 0, grab local birds in SDR. increase ViralLoad
     * 
     */
    public void act() 
    {
      counter++;
        if(!alive)
      {
        setLocation(this.getX(), this.getY());
      }
      else if (alive)
      {
        setLocation(this.getX()+currentSpeed, this.getY()+ (int) vertSpeed);
        redraw();
          if (this.isAtEdge())
        {
            currentSpeed= -1 * currentSpeed;
            redraw();
        } 
        if (counter%50==0 && viralLoad> 0)
        {
           viralLoad-=1;
           redraw();
        } 
        if (counter%10==0 && viralLoad>50 && lifeLeft>1)
        {
              lifeLeft--;
              redraw();
        }  
        if (viralLoad>0) //+ a condition to get the local birds more than two birds //that doesn't start errors
        {
          viralLoad++;
          redraw();
        }
        if(viralLoad==0)
        {
        double vertSpeed = .05;
        }
        //if (getY()==World.getHeight())
        //{alive = false;
        //if (getX()<150){setLocation(getX()+150, getY());}
        //World.addObject(new Bird(r.nextInt(3)+1), 400, r.nextInt(World.getHeight()*75));}
       }
        
    }    
}
