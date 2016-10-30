
//package network;

import java.text.DecimalFormat;
import java.util.Observable;

/**
 * Shannon's Model for use with ShannonsTheorem.
 * @author    Victoria Sawyer
 * @version   1.0.0 2016-09-13
 */

public class ShannonsModel extends Observable {

	/**
	 * The default constructor. It takes no parameters and returns nothing.
	 */	
	public ShannonsModel() {
	}

	/**
	 * Get the bandwidth.
	 * 
	 * @return	The bandwidth in Hertz
	 */
	public double getBandwidth() {
		return bandwidth;
	}

	/**
	 * Get the signal-to-noise ratio in decibels.
	 * 
	 * @return	The signal-to-noise ratio in decibels
	 */
	public double getSignaltoNoise() {
		return signalToNoise;
	}

	/**
	 * Get the maximum data rate after already setting bandwith and signal-to-noise ratio via modifier methods.
	 * Uses the private getMaximumDataRate(hertz, signalToNoise).
	 * 
	 * @return	The maximum data rate
	 */
	public double getMaximumDataRate() {
		return maximumDataRate(bandwidth, signalToNoise);
	}

	/**
	 * Set the bandwidth.
	 * 
	 * @param	hertz	The bandwidth in Hertz
	 */
	public void setBandwidth(double hertz) {
		bandwidth = hertz;
	}

	/**
	 * Set the signal-to-noise ratio in decibels.
	 * 
	 * @param	snr		The signal-to-noise ratio in decibels
	 */
	public void setSignalToNoise(double snr) {
		signalToNoise = snr;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Return a string with the result of the calculations.
	 * 
	 * @return	A string stating the maximum data rate
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return "The Maximum Data Rate is: "+df.format(getMaximumDataRate());
	}
	
	/**
	 * Get the maximum data rate, having given the methon the bandwidth and signal-to-noise ratio.
	 * 
	 * @param	hertz	The bandwidth in Hertz
	 * @param	snr	The signal-to-noise ratio in decibels
	 * @return	The maximum data rate
	 */
	private static double maximumDataRate(double hertz, double snr) {
		double mdr = (hertz*(Math.log(1+Math.pow(10, snr/10))/Math.log(2)));
		return mdr;
	}

	/**
	 * Field representing the bandwidth in Hertz.
	 */
	private double bandwidth;
	
	/**
	 * Field representing the signal-to-noise ratio in decibels.
	 */
	private double signalToNoise;

}
