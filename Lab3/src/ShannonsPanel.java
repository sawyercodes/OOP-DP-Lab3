//package network;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class ShannonsPanel extends JPanel implements Observer {
	
	public ShannonsPanel(ShannonsController ctl) {
		setController(ctl);
		initGui();
	}
	
	public JLabel getMaxDataRateLBL() {
		return maxDataRateLBL;
	}
	
	public void setMaxDataRateLBL(JLabel mdrlbl) {
		//maxDataRateLBL = new JLabel("Maximum data rate via Shannons Theorem = " + mdrlbl.getText());
		maxDataRateLBL = mdrlbl;
		
	}
	
	public void setController (ShannonsController ctl) {
		controller = ctl;
	}
	
	private void initGui() {
		//frame should be in ShannonsTheorem

		setLayout(new GridLayout(3, 1, 5, 5));
		setMaxDataRateLBL(new JLabel("Maximum data rate via Shannons Theorem = ???"));
		add(maxDataRateLBL);
		add(createSignalToNoisePanel());
		add(createBandwidthPanel());
		
	}
	
	private JPanel createSignalToNoisePanel() {
		JPanel panel = new JPanel();
		JTextField textField = new JTextField();
		//panel.setLayout(new GridLayout(0, 2, 0, 0));
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JLabel label = new JLabel("Signal To Noise (in DB): ");
		panel.add(label);
		
		panel.add(textField);
		textField.setColumns(20);
		
		textField.addActionListener(e-> {
			System.out.println("inside action performed");
			controller.setSignalToNoise(Double.parseDouble(textField.getText()));
		});
		
		return panel;
	}
	
	private JPanel createBandwidthPanel() {
		JPanel panel = new JPanel();
		JTextField textField = new JTextField();
		//panel.setLayout(new GridLayout(0, 2, 0, 0));
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel label = new JLabel("Bandwidth (in hertz): ");
		panel.add(label);
		
		panel.add(textField);
		textField.setColumns(20);

		textField.addActionListener(e-> {
			System.out.println("inside action performed");
			controller.setBandwidth(Double.parseDouble(textField.getText()));
			System.out.println(textField.getText());
		});
		
		return panel;
	}
	
	public void update(Observable o, Object arg) {
		System.out.println("invoking update");
		//System.out.println(arg);
		System.out.println(arg.toString());
		maxDataRateLBL.setText(arg.toString());
		System.out.println(getMaxDataRateLBL().getText());
		System.out.println("update complete");
	}
	
	private JLabel maxDataRateLBL;
	
	private ShannonsController controller;
	/*private ShannonsController controller = new ShannonsController() {
		
		@Override
		public void setSignalToNoise(double signalToNoiseRation) {
			this.setSignalToNoise(signalToNoiseRation);
		}
		
		@Override
		public void setBandwidth(double bandwidth) {
			this.setBandwidth(bandwidth);
		}
		
		@Override
		public void addObserver(Observer o) {
			this.addObserver(o);
		}
	};*/

}
