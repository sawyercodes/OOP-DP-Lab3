/* import statements */
import java.util.Observer;

/**
 * This class is an interface that defines the methods 
 * available to the controller.
 *  
 * @author Victoria Sawyer
 * @version   1.0.0 2016-10-07
 */
public interface ShannonsController {

	/**
	 * Void method that adds the observer. 
	 * 
	 * @param o The observer to add
	 */
	public void addObserver(Observer o);
	
	/**
	 * Set method to set the bandwidth (hertz). 
	 * 
	 * @param bandwidth double that holds the bandwidth (hertz)
	 */
	public void setBandwidth(double bandwidth);
	
	/**
	 * Set method to set the signal-to-noise ratio. 
	 * 
	 * @param signalToNoiseRatio double that holds the signal-to-noise ratio (decibels)
	 */
	public void setSignalToNoise(double signalToNoiseRatio);

} /* End of CLASS:	ShannonsController.java */
