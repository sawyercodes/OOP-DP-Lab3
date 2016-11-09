//package network;

/* import statements */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 * This is the view of the MVC design pattern. 
 * It creates the panel that holds user input fields,
 * which uses ShannonsModel to manipulate the data inputted.
 * It uses the Observer interface and inherits from JPanel.
 * 
 * @author    Victoria Sawyer
 * @version   1.0.0 2016-11-07
 */
public class ShannonsPanel extends JPanel implements Observer {
	
	/**
	 * Constructor that passes ShannonsController 
	 * and a string as parameters. The string determines
	 * the input type of the panel. The option are "slider",
	 * "spinner", or "text". This calls initGui() to create
	 * the panels of the GUI. 
	 * 
	 * @param 	ctl			ShannonsController
	 * DESIGN CHANGE: Additional parameter approved by email on 2016-11-07
	 * @param 	inputType	String	
	 */
	public ShannonsPanel(ShannonsController ctl, String inputType) {
		this.inputType = inputType;
		gp = new GraphPanel();
		setController(ctl);
		initGui();
	} /* End of CONSTRUCTOR:	ShannonsPanel() */
	
	/**
	 * Get method that returns a JLabel
	 * displaying the bandwidth, 
	 * signal to noise ratio, and maximum data rate.
	 * 
	 * @return	maxDataRateLBL	JLabel to display the maximum data rate
	 */
	public JLabel getMaxDataRateLBL() {
		return maxDataRateLBL;
	} /* End of METHOD:	getMaxDataRateLBL() */
	
	/**
	 * Set method for the maximum data rate JLabel.
	 * 
	 * @param 	mdrlbl	JLabel to dislay the maximum data rate
	 */
	public void setMaxDataRateLBL(JLabel mdrlbl) {
		maxDataRateLBL = mdrlbl;
	} /* End of METHOD:	setMaxDataRateLBL() */
	
	/**
	 * Set method for ShannonsController.
	 * 
	 * @param	ctl		ShannonsController
	 */
	public void setController (ShannonsController ctl) {
		controller = ctl;
	} /* End of METHOD:	setController() */
	
	/**
	 * Void method to set up the JPanel that holds user input.
	 * To get the user input fields this makes calls to
	 * createSignalToNoisePanel() and createBandwidthPanel().
	 * All panels contain a the maximum data rate JLabel.
	 * If inputType is "slider" a graph is added as well
	 * from the innerclass GraphPanel. 
	 */
	private void initGui() {
		/* Set the layout of this instance of ShannonsPanel. */
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		/* Set the maxDataRateLBL, add it to a panel, add that panel to this instance of ShannonsPanel. */
		setMaxDataRateLBL(new JLabel("Bandwidth: ???. SignalToNoise: ???. The Maximum Data Rate is: ???"));
		JPanel lp = new JPanel();
		lp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = getMaxDataRateLBL();
		lp.add(label);
		add(lp);
		label.setBorder(new EmptyBorder(5, 5, 5, 5));
		/* Create the bandwidth and signal-to-noise ratio input panels. */
		JPanel bp = createBandwidthPanel();
		JPanel snp = createSignalToNoisePanel();
		/* If the input type is "slider" add the GraphPanel. */
		if (inputType.equals("slider")) {
			JPanel container = new JPanel();
			JPanel inputContainer = new JPanel();
			inputContainer.setLayout(new BoxLayout(inputContainer, BoxLayout.Y_AXIS));
			inputContainer.add(bp);
			inputContainer.add(snp);
			gp.setPreferredSize(new Dimension(150, 80));
			container.add(gp);
			container.add(inputContainer);
			add(container);
			setPreferredSize(new Dimension(510, 150));
		} else {
			/* If the input type is not "slider" don't add the GraphPanel. */
			add(bp);
			add(snp);
			setPreferredSize(new Dimension(500, 110));
		}
	} /* End of METHOD:	initGui() */
	
	/**
	 * Method that returns a JPanel to get user input.
	 * This adds addChangeListener to the input field
	 * to set the SignalToNoise through ShannonsController.
	 * 
	 * @return	panel	JPanel that sets SignalToNoise (decibels)
	 */
	private JPanel createSignalToNoisePanel() {
		/* Instantiate the Jpanel and JLabel for this panel. */
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Signal To Noise (in DB): ");
		panel.add(label);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		int min = 0;
		int max = 100;
		int interval = 10;
		
		snrSliderInput = new JSlider(JSlider.HORIZONTAL, min, max, interval);
		snrSpinnerInput = new JSpinner(new SpinnerNumberModel(max/2, min, max, 1));
		snrTextInput = new JTextField(27);	
		
		/* Set up the panel to take input from a JSlider. */
		if (inputType.equals("slider")) {
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
			//snrSliderInput = new JSlider(JSlider.HORIZONTAL, min, max, interval);
			snrSliderInput.setPaintTicks(true);
			snrSliderInput.setMajorTickSpacing(interval);
			
			Hashtable labelTable = new Hashtable();
			labelTable.put(min, new JLabel(Integer.toString(min)));
			labelTable.put((max/2), new JLabel(Integer.toString(max/2)));
			labelTable.put(max, new JLabel(Integer.toString(max)));
			snrSliderInput.setLabelTable(labelTable);
			snrSliderInput.setPaintLabels(true);
			
			panel.add(snrSliderInput);
			snrSliderInput.addChangeListener(e-> {
				controller.setSignalToNoise(snrSliderInput.getValue());
			});
		/* Set up the panel to take input from a JSpinner. */
		} else if (inputType.equals("spinner")) {
			panel.add(snrSpinnerInput);			
			snrSpinnerInput.addChangeListener(e-> {
				controller.setSignalToNoise((Integer) snrSpinnerInput.getValue());
			});
		/* Set up the panel to take input from a JTextField. */
		} else {	
			panel.add(snrTextInput);			
			snrTextInput.addActionListener(e-> {
				controller.setSignalToNoise(Double.parseDouble(snrTextInput.getText()));
			});			
		}
		
		return panel;
	} /* End of METHOD:	createSignalToNoisePanel() */

	/**
	 * Method that returns a JPanel to get user input.
	 * This adds addChangeListener to the input field
	 * to set the Bandwidth through ShannonsController.
	 * 
	 * @return	panel	JPanel that sets Bandwidth (hertz)
	 */
	private JPanel createBandwidthPanel() {
		/* Instantiate the Jpanel and JLabel for this panel. */
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Bandwidth (in hertz): ");
		panel.add(label);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		int min = 0;
		int max = 5000;
		int interval = 400;

		bandwidthSliderInput = new JSlider(JSlider.HORIZONTAL, min, max, interval);
		bandwidthSpinnerInput = new JSpinner(new SpinnerNumberModel(max/2, min, max, 1));
		bandwidthTextInput = new JTextField(27);
		
		/* Set up the panel to take input from a JSlider. */
		if (inputType.equals("slider")) {
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
			bandwidthSliderInput.setPaintTicks(true);
			bandwidthSliderInput.setMajorTickSpacing(interval);
			
			Hashtable labelTable = new Hashtable();
			labelTable.put(min, new JLabel(Integer.toString(min)));
			labelTable.put((max/2), new JLabel(Integer.toString(max/2)));
			labelTable.put(max, new JLabel(Integer.toString(max)));
			bandwidthSliderInput.setLabelTable(labelTable);
			bandwidthSliderInput.setPaintLabels(true);
			
			panel.add(bandwidthSliderInput);
			bandwidthSliderInput.addChangeListener(e-> {
				controller.setBandwidth(bandwidthSliderInput.getValue());
			});
		/* Set up the panel to take input from a JSpinner. */
		} else if (inputType.equals("spinner")) {
			panel.add(bandwidthSpinnerInput);			
			bandwidthSpinnerInput.addChangeListener(e-> {
				controller.setBandwidth((Integer) bandwidthSpinnerInput.getValue());
			});
		/* Set up the panel to take input from a JTextField. */
		} else {
			panel.add(bandwidthTextInput);
			bandwidthTextInput.addActionListener(e-> {
				controller.setBandwidth(Double.parseDouble(bandwidthTextInput.getText()));
			});
		}
		
		return panel;
	} /* End of METHOD:	createBandwidthPanel() */
	
	/**
	 * Overriden update method called by the Observable object.
	 * This method sets the text of maxDataRateLbl and fills
	 * calls setFillAmt() of the GraphPanel innerclass to 
	 * fill the arc graphic.
	 * 
	 * @param	o		The observable object
	 * @param	args	Object holding an instance of ShannonsModel
	 */
	@Override
	public void update(Observable o, Object arg) {
		getMaxDataRateLBL().setText(arg.toString());
		String[] lblStr = arg.toString().split(" ");
		String bandwidth = lblStr[1].substring(0, lblStr[1].length()-1);
		String signalToNoise = lblStr[3].substring(0, lblStr[3].length()-1);
		String maxDataRate = lblStr[9].substring(0, lblStr[9].length()-1);
		int num;
		if (signalToNoise != null) {
			num = (int) Double.parseDouble(signalToNoise);
			snrSliderInput.setValue(num);
			snrSpinnerInput.setValue(num);
			snrTextInput.setText(signalToNoise);
		}
		if (bandwidth != null) {
			num = (int) Double.parseDouble(bandwidth);
			bandwidthSliderInput.setValue(num);
			bandwidthSpinnerInput.setValue(num);
			bandwidthTextInput.setText(bandwidth);
		}
		if (inputType.equals("slider")) {
			double fillAmt = (Double.parseDouble(maxDataRate)/166096.4)*180;
			gp.setFillAmt((int) fillAmt);
			gp.repaint();
		}
	} /* End of METHOD:	update() */
	
	/**
	 * Innerclass to create a graph using paintComponent.
	 * This inherits from JPanel.
	 * 
	 * DESIGN CHANGE: Inerclass approved by email on 2016-11-07
	 */
	public class GraphPanel extends JPanel {
		
		/**
		 * Overriden method that creates a graphic 
		 * giving a visual display of the maximum data rate.
		 * 
		 * @param	g	Graphic
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D graph = (Graphics2D) g;
			/* Initial arc as background for meter. */
			graph.fillArc((this.getWidth()/2)-50, 0, 100, 100, 180, -180);
			int fillAmt = getFillAmt();
			if (fillAmt<=45) {
				/* Set fill color to green up to 25%. */
				graph.setColor(Color.GREEN);
			} else if (fillAmt>45 && fillAmt<=90) {
				/* Set fill color to orange between 25% and 50%. */
				graph.setColor(Color.ORANGE);
			} else if (fillAmt>90) {
				/* Set fill color to red over 50%. */
				graph.setColor(Color.RED);
			}
			/* Fill meter according to maximum data rate. */
			graph.fillArc((this.getWidth()/2)-50, 0, 100, 100, 180, (fillAmt*-1));
			String drawStr = "Maximum Data Rate Meter";
			graph.setColor(Color.BLACK);
			graph.drawString(drawStr, (this.getWidth()/2)-(graph.getFontMetrics().stringWidth(drawStr)/2), 70);
		} /* End of METHOD:	paintComponent() */
		
		/**
		 * Get method to determine the amount to fill the graph.
		 * 
		 * @return	fillAmt		int that holds the amount to fill the arc
		 */
		public int getFillAmt() {
			return fillAmt;
		} /* End of METHOD:	getFillAmt() */
		
		/**
		 * Set method to set fillAmt.
		 * 
		 * @param 	fillAmt		int that holds the amount to fill the arc
		 */
		public void setFillAmt(int fillAmt) {
			this.fillAmt = fillAmt;
		} /* End of METHOD:	setFillAmt() */
		
		/** int to determine amount to fill arc. */
		int fillAmt = 0;
		
	} /* End of INNERCLASS:	GraphPanel */
	
	/** JLanel object to for the max data rate. */
	private JLabel maxDataRateLBL;
	
	/** ShannonsController object. */
	private ShannonsController controller;
	
	/** 
	 * String object for the input type. 
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private String inputType;

	/** 
	 * GraphPanel for the graphic from the innerclass.
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private GraphPanel gp;

	/** 
	 * JTextField object to hold the text input for bandwidth (hertz).
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private JTextField bandwidthTextInput;

	/** 
	 * JSlider object to hold the slider input for bandwidth (hertz).
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private JSlider bandwidthSliderInput;

	/** 
	 * JSpinner object to hold the spinner input for bandwidth (hertz).
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private JSpinner bandwidthSpinnerInput;

	/** 
	 * JTextField object to hold the text input for signal-to-noise ratio (decibels).
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private JTextField snrTextInput;

	/** 
	 * JSlider object to hold the slider input for signal-to-noise ratio (decibels).
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private JSlider snrSliderInput;

	/** 
	 * JSpinner object to hold the spinner input for signal-to-noise ratio (decibels).
	 * 
	 * DESIGN CHANGE: Additional field approved by email on 2016-11-07
	 */
	private JSpinner snrSpinnerInput;
	
} /* End of CLASS:	ShannonsPanel.java */
