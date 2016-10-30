
//package network;

import java.util.Observer;

import javax.naming.InitialContext;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Shannon's Theorem for Maximum Data Rate of a channel.
 * @author    Victoria Sawyer
 * @version   1.0.1 2016-09-13
 */
public class ShannonsTheorem implements ShannonsController {

	/**
	 * The default constructor. 
	 * It takes no parameters and returns nothing.
	 * Instantiates the field model.
	 */

	public ShannonsTheorem() {
		controller = new ShannonsController() {
			
			@Override
			public void setSignalToNoise(double signalToNoiseRatio) {
				this.setSignalToNoise(signalToNoiseRatio);
			}
			
			@Override
			public void setBandwidth(double bandwidth) {
				setBandwidth(bandwidth);
			}
			
			@Override
			public void addObserver(Observer o) {
				addObserver(o);	
			}
		};
		model = new ShannonsModel();
		panel = new ShannonsPanel(this);
		model.addObserver(panel);
		
		panel2 = new ShannonsPanel(this);
		model.addObserver(panel2);
	}


	/**
	 * Get an instance of ShannonsModel.
	 * 
	 * @return	ShannonsModel
	 */
	public ShannonsModel getModel() {
		return model;
	}

	/**
	 * Get the bandwidth.
	 * 
	 * @return	The bandwidth in Hertz
	 */
	public double getBandwidth() {
		return getModel().getBandwidth();
	}
	
	/**
	 * Get the maximum data rate.
	 * Bandwidth and SignalToNoise must be set.
	 * 
	 * @return	The maximum data rate
	 */
	public double maximumDataRate() {
		return getModel().getMaximumDataRate();
	}

	/**
	 * Get the signal-to-noise ratio.
	 * 
	 * @return	The signal-to-noise ratio in decibels
	 */
	public double getSignaltoNoise() {
		return getModel().getSignaltoNoise();
	}

	/**
	 * Set the signal-to-noise ratio in decibels.
	 * 
	 * @param	snr		The signal-to-noise ratio in decibels
	 */
	public void setSignalToNoise(double snr) {
		getModel().setSignalToNoise(snr);
	}


	/**
	 * Set the ShannonsModel object.
	 * 
	 * @param	model	ShannonsModel
	 */
	public void setModel(ShannonsModel model) {
		this.model = model;
	}

	/**
	 * Set the bandwidth.
	 * 
	 * @param	bandwidth	The bandwidth in Hertz
	 */
	public void setBandwidth(double bandwidth) {
		getModel().setBandwidth(bandwidth);
		
	}
	
	/**
	 * Initialize the GUI.
	 */
	public void initGui() {
		JFrame frame = new JFrame("Shannons Theorem MVC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ShannonsPanel panel1 = new ShannonsPanel(this);
		JPanel container = new JPanel();
		container.add(panel);
		frame.getContentPane().add(container);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/**
	 * 
	 * 
	 * @param	o	Observer
	 */
	public void addObserver(Observer o) {
		controller = (ShannonsController) o;
	}

	public static void main(String[] args) {		
		ShannonsTheorem theorem = new ShannonsTheorem();
 		theorem.initGui();
	}

	/**
	 * Field holding an instance of ShannonsNodel.
	 */
	private static ShannonsModel model;

	/**
	 * Field holding an instance of ShannonsController.
	 */
	private static ShannonsController controller;
	
	private ShannonsPanel panel; 
	private ShannonsPanel panel2; 

}	/*	End of CLASS:	ShannonsTheorem.java			*/


