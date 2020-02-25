package driveMe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import driveMe.constants.DriveMeConstants;
import driveMe.controller.CustomerController;
import driveMe.controller.VehicleController;
import driveMe.util.DriveMeUtil;


public class MainRenderer {

	public JFrame mainFrame = new JFrame("DriveMe");
	private JLayeredPane bodyJLayeredPane;
	
	private VehicleController vehicleController = new VehicleController();
	private CustomerController customerController = new CustomerController();
	private DriveMeUtil driveMeUtil = new DriveMeUtil();
	private boolean vehiclePageActive = true;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void initialize() {

		//Setup Main frame
//		mainFrame.setType(Type.POPUP);
		mainFrame.setBackground(DriveMeConstants.Colour.primaryColor);
		mainFrame.getContentPane().setMinimumSize(new Dimension(20, 16));
		mainFrame.setBounds(100, 100, 1106, 661);
		mainFrame.setMinimumSize(new Dimension(1120, 600));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		bodyJLayeredPane = new JLayeredPane();
		
		//Main Body Content Panel for Vehicle
		JPanel bodyContentPanel = new JPanel();
		bodyJLayeredPane.setLayer(bodyContentPanel, 1);
		bodyContentPanel.setBackground(Color.WHITE);
		bodyContentPanel.setPreferredSize(new Dimension(26, 90));
		bodyContentPanel.setLayout(new BorderLayout(0, 0));
		
		setUpHeader(bodyContentPanel);
		
//		bodyContentPanel.add(vehicleController.getVehicleContent(), "name_47788877080200");
		bodyContentPanel.setLayout(new CardLayout(0, 0));
		bodyContentPanel.add(vehicleController.getVehicleContent(), "name_47788877080200");
		bodyContentPanel.setVisible(true);
		bodyContentPanel.setBounds(0, 0, (mainFrame.getWidth() + 100), (mainFrame.getHeight() - 130) );	
		
		bodyJLayeredPane.add(bodyContentPanel);
		
		mainFrame.getContentPane().add(bodyJLayeredPane);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	bodyContentPanel.setBounds(0, 0, mainFrame.getWidth(), (mainFrame.getHeight() - 130) );
            }

        });
		mainFrame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				bodyContentPanel.setBounds(0, 0, mainFrame.getWidth(), (mainFrame.getHeight() - 130) );
			}
		});
    
	}
	private void setUpHeader(JPanel bodyContentPanel) 
	{
		//Setup header pannel
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(10, 90));
		headerPanel.setMinimumSize(new Dimension(10, 128));
		headerPanel.setBackground(SystemColor.windowBorder);
		mainFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel headerBottom = new JPanel();
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(DriveMeConstants.Colour.secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		setUpHeaderTop(bodyContentPanel,headerPanel,headerBottom);
		
		
		headerBottom.add(vehicleController.vehicleHeaderBottom(mainFrame, vehiclePageActive),BorderLayout.CENTER);
		headerPanel.add(headerBottom, BorderLayout.CENTER);
	}
	
	private void setUpHeaderTop(JPanel bodyContentPanel,JPanel headerPanel,JPanel headerBottom ) {
		//Setup header top to header pannel
		JPanel headerTop = new JPanel();
		headerPanel.add(headerTop, BorderLayout.NORTH);
		headerTop.setMaximumSize(new Dimension(32767, 0));
		headerTop.setMinimumSize(new Dimension(0, 0));
		headerTop.setPreferredSize(new Dimension(0, 35));
		headerTop.setBackground(DriveMeConstants.Colour.primaryColor);
		
		//Set FlowLayout that buttons at headerTop are left justified
		FlowLayout headerTopFlowLayout = (FlowLayout) headerTop.getLayout();
		headerTopFlowLayout.setVgap(0);
		headerTopFlowLayout.setHgap(0);
		headerTopFlowLayout.setAlignment(FlowLayout.LEFT);
		
		//Add Buttons to header
		JButton customerButton = new JButton("Kunden");
		customerButton.setForeground(Color.WHITE);
		customerButton.setBorder(null);
		customerButton.setBackground(DriveMeConstants.Colour.primaryColor);
		customerButton.setMargin(new Insets(0, 0, 0, 0));
		customerButton.setPreferredSize(new Dimension(150, 35));
		
		JButton vehicleButton = new JButton("Fahrzeuge");
		vehicleButton.setBorder(null);
		vehicleButton.setForeground(DriveMeConstants.Colour.primaryColor);
		vehicleButton.setBackground(DriveMeConstants.Colour.secondColor);
		vehicleButton.setMargin(new Insets(0, 0, 0, 0));
		vehicleButton.setPreferredSize(new Dimension(150, 35));
		headerTop.add(vehicleButton);
		
	
		JPanel customerContent = customerController.getCustomerContent();
		JPanel vehicleContent = vehicleController.getVehicleContent();
		
		JPanel customerHeaderBottom = customerController.customerHeaderBottom(mainFrame);
		JPanel vehicleHeaderBottom = vehicleController.vehicleHeaderBottom(mainFrame, vehiclePageActive);
		
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(DriveMeConstants.Colour.secondColor);	
				customerButton.setForeground(DriveMeConstants.Colour.primaryColor);
				vehicleButton.setBackground(DriveMeConstants.Colour.primaryColor);	
				vehicleButton.setForeground(Color.WHITE);
				
				driveMeUtil.clearAndSetContent(bodyContentPanel, customerContent, null);
//				bodyContentPanel.removeAll();
//				bodyContentPanel.repaint();
//				bodyContentPanel.revalidate();
//				bodyContentPanel.add(customerContent);
//				bodyContentPanel.repaint();
//				bodyContentPanel.revalidate();
				
				driveMeUtil.clearAndSetContent(headerBottom, customerHeaderBottom, BorderLayout.CENTER);
//				headerBottom.removeAll();
//				headerBottom.repaint();
//				headerBottom.revalidate();
//				headerBottom.add(customeHeaderBottom, BorderLayout.CENTER);
//				headerBottom.repaint();
//				headerBottom.revalidate();
				vehiclePageActive = false;
			}
		});
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(DriveMeConstants.Colour.primaryColor);	
				customerButton.setForeground(Color.WHITE);
				vehicleButton.setBackground(DriveMeConstants.Colour.secondColor);	
				vehicleButton.setForeground(DriveMeConstants.Colour.primaryColor);

				driveMeUtil.clearAndSetContent(bodyContentPanel, vehicleContent, null);
//				bodyContentPanel.removeAll();
//				bodyContentPanel.repaint();
//				bodyContentPanel.revalidate();
//				bodyContentPanel.add(vehicleContent);
//				bodyContentPanel.repaint();
//				bodyContentPanel.revalidate();
				
				driveMeUtil.clearAndSetContent(headerBottom, vehicleHeaderBottom, BorderLayout.CENTER);
//				headerBottom.removeAll();
//				headerBottom.repaint();
//				headerBottom.revalidate();
//				headerBottom.add(vehicleHeaderBottom, BorderLayout.CENTER);
//				headerBottom.repaint();
//				headerBottom.revalidate();
				vehiclePageActive = true;
			}
		});
		headerTop.add(customerButton);
	}
	
}
