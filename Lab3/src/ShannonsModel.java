
//package network;

/* import statements */
import java.text.DecimalFormat;
import java.util.Observable;

/**
 * Shannon's Model for use with ShannonsTheorem.
 * 
 * @author    Victoria Sawyer
 * @version   1.0.0 2016-09-13
 */

public class ShannonsModel extends Observable {

	/**
	 * The default constructor. It takes no parameters and returns nothing.
	 */	
	public ShannonsModel() {
	} /* End of CONSTRUCTOR:	ShannonsModel() */

	/**
	 * Get method that returns the bandwidth (hertz).
	 * 
	 * @return	bandwidth	double that holds the bandwidth (hertz)
	 */
	public double getBandwidth() {
		return bandwidth;
	} /* End of METHOD:	getBandwidth() */

	/**
	 * Get method that returns the signal-to-noise ratio (decibels).
	 * 
	 * @return	signalToNoise	double that holds the signal-to-noise ratio (decibels)
	 */
	public double getSignalToNoise() {
		return signalToNoise;
	} /* End of METHOD:	getSignalToNoise() */

	/**
	 * Get method that returns the maximum data rate using the private 
	 * maximumDataRate() method. The bandwidth and signalToNoise fields
	 * must already be set.
	 * 
	 * @return	maximumDataRate()	private method to set maximum data rate
	 */
	public double getMaximumDataRate() {
		return maximumDataRate(bandwidth, signalToNoise);
	} /* End of METHOD:	getMaximumDataRate() */

	/**
	 * Set method for the bandwidth (hertz).
	 * Notify the observer that the bandwidth has changed.
	 * 
	 * @param	hertz	double that sets the bandwidth (hertz)
	 */
	public void setBandwidth(double hertz) {
		bandwidth = hertz;
		setChanged();
		notifyObservers(this);
	} /* End of METHOD:	setBandwidth() */

	/**
	 * Set method for the signal-to-noise ratio (decibels).
	 * Notify the observer that the signal-to-noise ratio has changed.
	 * 
	 * @param	snr		double that sets the signal-to-nois ratio (decibels)
	 */
	public void setSignalToNoise(double snr) {
		signalToNoise = snr;
		setChanged();
		notifyObservers(this);
	} /* End of METHOD:	setSignalToNoise() */

	/**
	 * toString method to return the result of 
	 * calculating the maximum data rate. It also shows 
	 * the bandwidth (hertz) and signal-to-noise (decibels).
	 * 
	 * @return	String containing the bandwidth(hertz), signal-to-noise (decibels), and maximum data rate
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return "Bandwidth: " + getBandwidth() + ". SignalToNoise: " + getSignalToNoise() + ". The Maximum Data Rate is: "+df.format(getMaximumDataRate());
	} /* End of METHOD:	toString() */
	
	/**
	 * Private method to set the maximum data rate.
	 * 
	 * @param	hertz	double holding the bandwidth (hertz)
	 * @param	snr		double hoding the signal-to-noise ratio (decibels)
	 * @return	mdr		double holding the maximum data rate 
	 */
	private static double maximumDataRate(double hertz, double snr) {
		/* Formula to calculate maximum data rate. */
		double mdr = (hertz*(Math.log(1+Math.pow(10, snr/10))/Math.log(2)));
		return mdr;
	} /* End of METHOD:	maximumDataRate() */

	/** Double for the bandwidth (hertz). */
	private double bandwidth;
	
	/** Double for the signal-to-noise-ratio (decibels). */
	private double signalToNoise;

} /* End of CLASS:	ShannonsModel.java */
