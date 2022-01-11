import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;      // gives colors 
import java.awt.geom.*; 
import java.lang.Math;
/**
 * Virus class discribes how the virus class will interact and move throughout the world
 * 
 * @author Belle Erhardt
 * @version 4/2020
 */
public class ViralLoad extends Actor
{
    private boolean vLaunch;
    private double xspeed;
    private double yspeed;
    private int xlocation;
    private int ylocation;
    private CircleMeter circlemeter;
    MyWorld World  = (MyWorld) getWorld();
     /** 
     * Ignitializes launch as false
     * 
     */ 
    public ViralLoad(CircleMeter cm)
    {
     vLaunch = false;
     circlemeter = cm;
    }
     /**
     * Sets location of VL to the angle of the perimeter on the cm 
     */
    protected void addedToWorld(World world)
    {
    setLocation(circlemeter.getArcX(), circlemeter.getArcY());
    }
     /**
     * Act - 
     * if the not launched the viral load,continue to set the location to the arch of a cm
     * onced launched, check to see if touching bird class, if not move (location + speed)
     * if touching wall, add new viral load to cm, and remove this vl.
     */
    public void act() 
    {
      RectangleMeter rm =  getWorld().getObjects(RectangleMeter.class).get(0);
      Bird bd = (Bird) getOneIntersectingObject(Bird.class);
        if (vLaunch != true)
       {
        xlocation = this.getX();
        ylocation = this.getY();
        setLocation(circlemeter.getArcX(),circlemeter.getArcY());
        
        if(Greenfoot.isKeyDown("space"))
          {
            vLaunch= true;
            xspeed = (rm.getFilledRatio()*15) *Math.cos(circlemeter.getAngle()); 
            yspeed = (rm.getFilledRatio()*15) *Math.sin(circlemeter.getAngle()); 
           }
        }
       if(vLaunch== true)
        {
            
            if (isTouching(Bird.class))
            {
            bd.addInfection(50);
            World.addViralLoad();
            World.removeObject(this);// add new VL to circle meter  
            }
            else 
            {
            yspeed+= .01; //add 0.1 to  VL y speed
            xlocation+= xspeed; //add x speed to x location
            ylocation+= -1*yspeed;//add VL y speed to ylocation
            setLocation(xlocation , ylocation );
            }
            
            if(isAtEdge())
            {
                //remove object from world
               // World.addViralLoad(); 
                vLaunch = false;
                World.removeObject(this);
                //World.addObject(this, circlemeter.getArcX(),circlemeter.getArcY());  
            }     
        }           
       
       //** if(World.getObjects(ViralLoad.class).size()==0)//check for Viral Load in world
       //{;}
       
    }    
}
