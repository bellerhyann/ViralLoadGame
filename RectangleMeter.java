import greenfoot.*;    // for greenfoot classes (Actor, GreenfootImage, ...)

/**
 * Allows for an interactive fillable vertical rectangle. This is accomplished by pressing 
 *   the up and down arrow keys
 * 
 * YOU PROBABLY SHOULD ***NOT*** MODIFY THIS CODE, although you can if you want to.
 * 
 * @author Stephen Blythe
 * @version 1/2016
 */
public class RectangleMeter extends Actor
{
    private int currVal;   // the currently filled amount of the rectangle
    private int height;    // the maximum filled amount of the rectangle
    
    /**
     * Build a rectangle of specifed height (and 20 width). Initially, the rectangle is empty.
     * 
     * @param height - the requested height
     */
    public RectangleMeter(int height)
    {   
        currVal=0;           // initial filled amount is 0
        this.height=height;  // use specifed height

        redraw();            // build and use the rectangel image
    }
    
    //
    // redraw is a private method that will rebuild the image for a RectangleMeter
    private void redraw()
    {
        // build an appropriate (blank) image
        GreenfootImage img = new GreenfootImage(20,height); 

        // draw the unfilled rectangle
        img.setColor(Color.GRAY);
        img.fill();
        
        // draw a red outline around the rectangle
        img.setColor(Color.RED);
        img.drawRect(0,0,19,height-1);
        
        // fill in the currently filled portion of the rectangle
        img.setColor(Color.GREEN);
        img.fillRect(1,height-currVal+1,18,currVal-2);      
                       
        // use the newly generated image
        setImage(img);        
    }
    
    public int getPower()
    {
        return currVal;
    }
    
    /**
     * getFilledRatio returns a double representing the amount the meter is full as a ratio 
     *    (i.e. a value between 0 and 1 inclusively)
     *    
     *    @return the ratio
     */
    public double getFilledRatio() {return currVal/(double)height;}

    /**
     * getHeight returns the height of this RectangleMeter in pixels
     *    
     *    @return number of pixels high the meter image is
     */
    public int getHeight() {return height;}
    
    /**
     * getWidth returns the width of this RectangleMeter in pixels
     *    
     *    @return number of pixels wide the meter image is
     */
    public int getWidth() {return getImage().getWidth();}
    
    /**
     * act - check  user keypress status and modify meter accordingly 
     */
    public void act() 
    {
        // modify the filled amount based on user keypresses
        if (Greenfoot.isKeyDown("Up") && currVal<height)
        {
            currVal++;
            redraw();
        }
        else if (Greenfoot.isKeyDown("Down") && currVal>0)
        {
            currVal--;
            redraw();
        }
        
    } 
    
    
 
}
