import greenfoot.*;     // gives GreenfootImage and Actor
import java.awt.*;      // gives colors 
import java.awt.geom.*; // allows generation of circular shapes

/**
 *  CircleMeter is a class that builds a "status" meter in a semi-circle. Pressing left or right 
 *     arrow keys will visually fill/reduce the status
 *
 * YOU PROBABLY SHOULD ***NOT*** MODIFY THIS CODE, although you can if you want to.
 *
 * @author Stephen Blythe
 * @version 1/2016
 */
public class CircleMeter extends Actor
{
    private int degFull;    // how full is the meter? from 0 to maxDegree
    private int radius;     // how big is the "sweeping" radius of this meter?
    private int maxDegree;  // what is the maximum number of degrees in this meter?
    
    /**
     * constructs a 180 degree (half circle) meter with specified radius
     * 
     *   @param radius the size of the semicircle's radius
     */
    public CircleMeter(int radius)
    {   
        degFull=0;           // starts out empty
        this.radius=radius;  // use specified radius
        maxDegree=180;       // by default, a half circle
        redraw();            // redraws the circle image, based on above. 
    }
    
    
    /**
     * constructs a semicircle meter with specified radius and maximum degree. WARNING: this is really
     *      intended to work with only multiples of 90 degrees. 
     * 
     *   @param radius the size of the semicircle's radius
     *   @param the sweeping range of the meter. Intended to work in multiples of 90 degrees. 
     */
    public CircleMeter(int radius, int maxDegree)
    {
        degFull=0;                        // starts out empty
        this.radius=radius;               // use specifed radius
        // use specifed sweeping range (carefully)
        this.maxDegree=Math.min(180, Math.max(maxDegree, 10));
        redraw();                         // redraws the meter, based on above
    }
    
    //
    // redraw is a private method that will rebuild the image for a CircleMeter
    private void redraw()
    {
        // get a new (empty) image of the needed size.         
        int imgWidth = radius + (maxDegree>90?radius:0);
        imgWidth = Math.max(imgWidth, radius);
        GreenfootImage img = new GreenfootImage(  imgWidth , radius);
        
        // get underlying java awt image (need direct java awt rendering)
        java.awt.image.BufferedImage awtVersion = img.getAwtImage();
        java.awt.Graphics2D g = (java.awt.Graphics2D) awtVersion.getGraphics();
        
        // draw the blue filled backgound, indicating "unfilled" portion
        Shape arc = new Arc2D.Float(-(180-maxDegree)/90*radius, 0, 2*radius-1, 2*radius-1, 0, maxDegree, Arc2D.PIE);
        g.setPaint(java.awt.Color.BLUE);
        g.fill(arc);
       
        // draw the orange filled foregound, indicating "filled" portion
        arc = new Arc2D.Float(-(180-maxDegree)/90*radius, 0, 2*radius-1, 2*radius-1, 0, degFull, Arc2D.PIE);
        g.setPaint(java.awt.Color.ORANGE);
        g.fill(arc);        
       
        // now that we have built the image, use it as this Actor's image.
        setImage(img);      
    }
    
    /**
     * getAngle specifies (in radians) the current fill value of the meter
     * 
     * @return the angle value in radians of the meter. 
     */
    public double getAngle() {return Math.toRadians(degFull);}
    
    /**
     * getDegrees specifies the current fill value of the meter in degrees
     * @return the angle value in degrees
     */
    public int getDegrees() {return degFull;}
    
    
    /**
     * getArcX gives the x value of the perimeter of the circle where the meter is currently filled to
     * 
     * @return the x location of the filled portion of the meter
     */
    public int getArcX()
    {
        // find the "origin" x value of the meter
        int originX = getX() - (maxDegree<=90?radius/2:0);
        //we'll be an appropriate offset from that origin. 
        return (int) (originX+Math.cos(Math.toRadians(degFull))*radius);
    }
    
    /**
     * getArcY gives the y value of the perimeter of the circle where the meter is currently filled to
     * 
     * @return the y location of the filled portion of the meter
     */
    public int getArcY()
    {
        // find the "origin" y value of the meter
        int originY = getY()+getImage().getHeight()/2;
        
        //we'll be an appropriate offset from that origin. 
        return (int) (originY-Math.sin(Math.toRadians(degFull))*radius);
    }
    
    /**
     * Act - do whatever the CircleMeter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // if the left arrow key is being pressed and we can still grow, 
        if (Greenfoot.isKeyDown("Left") && degFull<maxDegree)
        {
            degFull++; // a bit more is full
            redraw();  // rebuild image to note the updates fill amount
        }
        
        // if the right arrow key is being pressed and we could still shrink our fill ...
        if (Greenfoot.isKeyDown("Right") && degFull>0)
        {
            degFull--;  // a bit less is full
            redraw();   // rebuild image to reflect the new fill amount
        }
        
    }    
}
