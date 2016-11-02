//package network;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ShannonsPanel extends JPanel implements Observer {
	
	public ShannonsPanel(ShannonsController ctl, String inputType) {
		this.inputType = inputType;
		setController(ctl);
		initGui();
	}
	
	public JLabel getMaxDataRateLBL() {
		return maxDataRateLBL;
	}
	
	public void setMaxDataRateLBL(JLabel mdrlbl) {
		maxDataRateLBL = mdrlbl;
		
	}
	
	public void setController (ShannonsController ctl) {
		controller = ctl;
	}
	
	private void initGui() {
		setLayout(new GridLayout(3, 1, 5, 5));
		setMaxDataRateLBL(new JLabel("Bandwidth: ???. SignalToNoise: ???. The Maximum Data Rate is: ???"));
		JLabel label = getMaxDataRateLBL();
		add(label);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(createBandwidthPanel());
		add(createSignalToNoisePanel());
	}
	
	private JPanel createSignalToNoisePanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Signal To Noise (in DB): ");
		panel.add(label);

		int min = 0;
		int max = 100;
		int interval = 10;
		
		if (inputType.equals("slider")) {
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
			JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, interval);
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(interval);
			
			Hashtable labelTable = new Hashtable();
			labelTable.put(min, new JLabel(Integer.toString(min)));
			labelTable.put((max/2), new JLabel(Integer.toString(max/2)));
			labelTable.put(max, new JLabel(Integer.toString(max)));
			slider.setLabelTable(labelTable);
			slider.setPaintLabels(true);
			
			panel.add(slider);
			slider.addChangeListener(e-> {
				controller.setSignalToNoise(slider.getValue());
			});
		} else if (inputType.equals("spinner")) {
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			interval = 1;
			JSpinner spinner = new JSpinner(new SpinnerNumberModel(max/2, min, max, interval));
			panel.add(spinner);			
			spinner.addChangeListener(e-> {
				controller.setSignalToNoise((Integer) spinner.getValue());
			});
		} else {
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			JTextField textField = new JTextField(27);		
			panel.add(textField);			
			textField.addActionListener(e-> {
				controller.setSignalToNoise(Double.parseDouble(textField.getText()));
			});			
		}
		
		return panel;
	}
	
	private JPanel createBandwidthPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Bandwidth (in hertz): ");
		panel.add(label);

		int min = 0;
		int max = 5000;
		int interval = 400;
		
		if (inputType.equals("slider")) {
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
			JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, interval);
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(interval);
			
			Hashtable labelTable = new Hashtable();
			labelTable.put(min, new JLabel(Integer.toString(min)));
			labelTable.put((max/2), new JLabel(Integer.toString(max/2)));
			labelTable.put(max, new JLabel(Integer.toString(max)));
			slider.setLabelTable(labelTable);
			slider.setPaintLabels(true);
			
			panel.add(slider);
			slider.addChangeListener(e-> {
				controller.setBandwidth(slider.getValue());
			});
		} else if (inputType.equals("spinner")) {
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			interval = 1;
			JSpinner spinner = new JSpinner(new SpinnerNumberModel(max/2, min, max, interval));
			panel.add(spinner);			
			spinner.addChangeListener(e-> {
				controller.setBandwidth((Integer) spinner.getValue());
			});
		} else {
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			JTextField textField = new JTextField(27);
			panel.add(textField);
			textField.addActionListener(e-> {
				controller.setBandwidth(Double.parseDouble(textField.getText()));
			});			
		}
		
		return panel;
	}
	
	public void update(Observable o, Object arg) {
		getMaxDataRateLBL().setText(arg.toString());
	}
	
	private JLabel maxDataRateLBL;
	
	private ShannonsController controller;
	
	private String inputType;


}
