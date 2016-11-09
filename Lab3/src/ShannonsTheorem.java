
//package network;

/* import statements */
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Shannon's Theorem for Maximum Data Rate of a channel. 
 * This class implements the ShannonsController interface.
 *  
 * @author    Victoria Sawyer
 * @version   1.0.2 2016-10-07
 */
public class ShannonsTheorem implements ShannonsController {

	/**
	 * The default constructor instantiates the field model 
	 * which is a ShannonsModel object.
	 */
	public ShannonsTheorem() {
		model = new ShannonsModel();
	} /* End of CONSTRUCTOR:	ShannonsTheorem() */


	/**
	 * Get method that returns an instance of ShannonsModel.
     * 
     * @return	model	ShannonsModel object
	 */
	private ShannonsModel getModel() {
		return model;
	} /* End of METHOD:	getModel() */

	/**
	 * Get method that returns the bandwidth (hertz) by calling 
	 * getBandwidth() through the ShannonsModel object. 
     * 
     * @return	double that is the bandwidth (hertz)
	 */
	public double getBandwidth() {
		return getModel().getBandwidth();
	} /* End of METHOD:	getBandwidth() */
	
	/**
	 * Get method to return the maximum data rate by calling 
	 * maximumDataRate() through the ShannonsModel object. 
     * 
     * @return	double that is the maximum data rate
	 */
	public double maximumDataRate() {
		return getModel().getMaximumDataRate();
	} /* End of METHOD:	maximumDataRate() */

	/**
	 * Get method that returns the the signal-to-noise ratio (decibel) 
	 * by calling getSignalToNoise() through the ShannonsModel object. 
     * 
     * @return	double that is the signal-to-noise ratio (decibels)
	 */
	public double getSignaltoNoise() {
		return getModel().getSignalToNoise();
	} /* End of METHOD:	getSignalToNoise() */

	/**
	 * Set method to set the signal-to-noise ratio (decibels) by 
	 * calling setSignalToNoise() through the ShannonsModel object. 
     * 
     * @param	snr		double that holds the signal-to-noise ratio (decibels)
	 */
	public void setSignalToNoise(double snr) {
		getModel().setSignalToNoise(snr);
	} /* End of METHOD:	setSignalToNoise() */


	/**
	 * Set method to set the ShannonsModel object.
     * 
     * @param	model	ShannonsModel object
	 */
	private void setModel(ShannonsModel model) {
		this.model = model;
	} /* End of METHOD:	setModel() */

	/**
	 * Set method to set the bandwidth by calling setBandwidth() 
	 * through the ShannonsModel object.
     * 
     * @param	bandwidth 	double that is the bandwidth (hertz)
	 */
	public void setBandwidth(double bandwidth) {
		getModel().setBandwidth(bandwidth);		
	} /* End of METHOD:	setBandwidth() */
	
	/**
	 * Void method that initialized the GUI. 
	 * This method adds three panels to a JFrame by 
	 * making calls to ShannonsPanel and adds the observer 
	 * to each instance of ShannonsPanel.
	 */
	public void initGui() {
		/* Create a frame to display the GUI. */
		JFrame frame = new JFrame("Shannons Theorem MVC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Create a JPanel to hold three instances of ShannonsPanel. */
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		/* Create three instances of ShannonsPanel with different inputs for each. */
		ShannonsPanel panel1 = new ShannonsPanel(this, "text");
		ShannonsPanel panel2 = new ShannonsPanel(this, "slider");
		ShannonsPanel panel3 = new ShannonsPanel(this, "spinner");

		/* Set the border for each ShannonsPanel to separate them in the GUI. */
		panel1.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), BorderFactory.createRaisedBevelBorder()));
		panel2.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), BorderFactory.createRaisedBevelBorder()));
		panel3.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), BorderFactory.createRaisedBevelBorder()));
		
		/* Add the three panels to the container. */
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		
		/* Add the observer to each instance of ShannonsPanel. */
		addObserver(panel1);
		addObserver(panel2);
		addObserver(panel3);
		
		frame.getContentPane().add(container);
		
		frame.pack();
		frame.setVisible(true);
	} /* End of METHOD:	initGui() */
	
	/**
	 * Void method that adds the observer to the ShannonsModel object. 
     * 
     * @param    o    Observer object
	 */
	public void addObserver(Observer o) {
		getModel().addObserver(o);
	} /* End of METHOD:	addObserver() */

	public static void main(String[] args) {		
		ShannonsTheorem theorem = new ShannonsTheorem();
 		theorem.initGui();
	} /* End of METHOD:	main() */

	/**
	 * Field holding an instance of ShannonsNodel.
	 */
	private static ShannonsModel model;

} /* End of CLASS:	ShannonsTheorem.java */


