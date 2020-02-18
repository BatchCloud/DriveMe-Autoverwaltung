package driveMe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import driveMe.vehicles.service.VehiclesService;


public class MainController {

public JFrame mainFrame=new JFrame("DriveMe");
	
	private Color primaryColor = new Color(105,157,217);
	private Color secondColor = new Color(238,238,238);
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void initialize() {

		//Setup Main frame
//		mainFrame.setType(Type.POPUP);
		mainFrame.setBackground(primaryColor);
		mainFrame.getContentPane().setMinimumSize(new Dimension(20, 16));
		mainFrame.setBounds(100, 100, 1106, 661);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		setUpHeader();
	}

	private void setUpHeader() 
	{
		//Setup header pannel
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(10, 90));
		headerPanel.setMinimumSize(new Dimension(10, 128));
		headerPanel.setBackground(SystemColor.windowBorder);
		mainFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		setUpHeaderTop(headerPanel);
		setUpHeaderBottom(headerPanel); //TODO
	}
	
	private void setUpHeaderTop(JPanel headerPanel) {
		//Setup header top to header pannel
		JPanel headerTop = new JPanel();
		headerPanel.add(headerTop, BorderLayout.NORTH);
		headerTop.setMaximumSize(new Dimension(32767, 0));
		headerTop.setMinimumSize(new Dimension(0, 0));
		headerTop.setPreferredSize(new Dimension(0, 35));
		headerTop.setBackground(primaryColor);
		
		//Set FlowLayout that buttons at headerTop are left justified
		FlowLayout headerTopFlowLayout = (FlowLayout) headerTop.getLayout();
		headerTopFlowLayout.setVgap(0);
		headerTopFlowLayout.setHgap(0);
		headerTopFlowLayout.setAlignment(FlowLayout.LEFT);
		
		//Add Buttons to header
		JButton customerButton = new JButton("Kunden");
		customerButton.setForeground(Color.WHITE);
		customerButton.setBorder(null);
		customerButton.setBackground(secondColor);
		customerButton.setMargin(new Insets(0, 0, 0, 0));
		customerButton.setPreferredSize(new Dimension(150, 35));
		
		JButton vehicleButton = new JButton("Fahrzeuge");
		vehicleButton.setBorder(null);
		vehicleButton.setBackground(primaryColor);
		vehicleButton.setMargin(new Insets(0, 0, 0, 0));
		vehicleButton.setPreferredSize(new Dimension(150, 35));
		headerTop.add(vehicleButton);
		
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane().add(customerContent(customerButton, vehicleButton)));
			}
		});
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane().add(vehicleContent(customerButton, vehicleButton)));
			}
		});
		headerTop.add(customerButton);
	}
	
	private void setUpHeaderBottom(JPanel headerPanel) 
	{
		//Set header bottom
		JPanel headerBottom = new JPanel();
		headerPanel.add(headerBottom, BorderLayout.CENTER);
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		//Set leftSidePanel to header bottom
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(200, 10));
		headerBottom.add(leftSidePanel, BorderLayout.WEST);

		//Set rightSidePanel to header bottom
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(200, 10));
		rightSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(rightSidePanel, BorderLayout.EAST);
		//Add compobox to rightSidePanel
		addComboBoxToPanel(rightSidePanel);
		
		//Set topSidePanel to header bottom
		JPanel topSidePanel = new JPanel();
		headerBottom.add(topSidePanel, BorderLayout.NORTH);

		//Set bottomSidePanel to header bottom		
		JPanel bottomSidePanel = new JPanel();
		headerBottom.add(bottomSidePanel, BorderLayout.SOUTH);
		
		//Set midSidePanel to header bottom
		JPanel midSidePanel = new JPanel();
		midSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(midSidePanel, BorderLayout.CENTER);
		//Set textField to midSidePanel
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(400, 25));
		textField.setSize(new Dimension(400, 24));
		midSidePanel.add(textField);
		textField.setColumns(50);
	}

	private JPanel vehicleContent(JButton customerButton, JButton vehicleButton) 
	{
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setPreferredSize(new Dimension(10, 90));
		vehiclePanel.setMinimumSize(new Dimension(10, 128));
		vehiclePanel.setBackground(SystemColor.GREEN);
		vehiclePanel.setLayout(new BorderLayout(0, 0));
		
		customerButton.setBackground(primaryColor);
		vehicleButton.setBackground(secondColor);
		
		return vehiclePanel;
	} 
	
	private JPanel customerContent(JButton customerButton, JButton vehicleButton) 
	{
		JPanel customerPanel = new JPanel();
		customerPanel.setPreferredSize(new Dimension(10, 90));
		customerPanel.setMinimumSize(new Dimension(10, 128));
		customerPanel.setBackground(SystemColor.RED);
		customerPanel.setLayout(new BorderLayout(0, 0));
		
		customerButton.setBackground(secondColor);
		vehicleButton.setBackground(primaryColor);
		
		return customerPanel;
	}
	
	private void addComboBoxToPanel(JPanel panel)
	{
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setForeground(Color.WHITE);
		comboBox.setBorder(null);
		comboBox.setBackground(primaryColor);
		comboBox.setPreferredSize(new Dimension(145, 25));
		comboBox.addItem("Filtern");
		comboBox.addItem("test");
		
		panel.add(comboBox);
	}
}
