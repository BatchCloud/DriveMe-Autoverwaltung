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
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import driveMe.constants.DriveMeConstants;
import driveMe.controller.CustomerRenderer;
import driveMe.controller.VehicleRenderer;
import driveMe.customers.model.Customer;
import driveMe.customers.service.CustomerService;
import driveMe.util.DriveMeUtil;
import driveMe.vehicles.model.Vehicle;
import driveMe.vehicles.service.VehicleService;


public class MainRenderer {

	public JFrame mainFrame = new JFrame("DriveMe");
	
	public static JPanel bodyContentPanel = new JPanel();
	private JLayeredPane bodyJLayeredPane = new JLayeredPane();;
	
	public VehicleRenderer vehicleRenderer;
	private CustomerRenderer customerRenderer;
	public DriveMeUtil driveMeUtil;
	
	public DriveMeUtil getDriveMeUtil() {
		if (driveMeUtil == null) {
			driveMeUtil = new DriveMeUtil();
		}
	
		return driveMeUtil;
	}


	private boolean vehiclePageActive = true;
	
	protected ArrayList<Vehicle> allVehicles = VehicleService.findAllVehicles();
	protected ArrayList<Customer> allCustomers = CustomerService.findAllCustomers();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void initialize() {

		vehicleRenderer = new VehicleRenderer();
		customerRenderer = new CustomerRenderer();
		
		//Setup Main frame
//		mainFrame.setType(Type.POPUP);
		mainFrame.setBackground(DriveMeConstants.Colour.primaryColor);
		mainFrame.getContentPane().setMinimumSize(new Dimension(20, 16));
		mainFrame.setBounds(100, 100, 1106, 661);
		mainFrame.setMinimumSize(new Dimension(1120, 600));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
//		bodyJLayeredPane = new JLayeredPane();
		
		//Main Body Content Panel
//		bodyContentPanel = new JPanel();
		bodyJLayeredPane.setLayer(bodyContentPanel, 1);
		bodyContentPanel.setBackground(Color.WHITE);
		bodyContentPanel.setPreferredSize(new Dimension(26, 90));
		bodyContentPanel.setLayout(new BorderLayout(0, 0));
		
		setUpHeader();
		
//		bodyContentPanel.add(vehicleController.getVehicleContent(), "name_47788877080200");
		bodyContentPanel.setLayout(new CardLayout(0, 0));
		bodyContentPanel.add(vehicleRenderer.getVehicleContent(allVehicles), "name_47788877080200");
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
	private void setUpHeader() 
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
		
		setUpHeaderTop(headerPanel,headerBottom);
		
		
		headerBottom.add(vehicleRenderer.vehicleHeaderBottom(mainFrame, vehiclePageActive),BorderLayout.CENTER);
		headerPanel.add(headerBottom, BorderLayout.CENTER);
	}
	
	private void setUpHeaderTop(JPanel headerPanel,JPanel headerBottom ) {
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
		
		JPanel customerContent = customerRenderer.getCustomerContent(allCustomers);
		JPanel vehicleContent = vehicleRenderer.getVehicleContent(allVehicles);
		
		JPanel customerHeaderBottom = customerRenderer.customerHeaderBottom(mainFrame, vehiclePageActive);
		JPanel vehicleHeaderBottom = vehicleRenderer.vehicleHeaderBottom(mainFrame, vehiclePageActive);
		
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(DriveMeConstants.Colour.secondColor);	
				customerButton.setForeground(DriveMeConstants.Colour.primaryColor);
				vehicleButton.setBackground(DriveMeConstants.Colour.primaryColor);	
				vehicleButton.setForeground(Color.WHITE);
				
				bodyContentPanel = getDriveMeUtil().clearAndSetContent(bodyContentPanel, customerContent, null);
//				getDriveMeUtil().clearAndSetContentForBodyPanel(customerContent);
				
				getDriveMeUtil().clearAndSetContent(headerBottom, customerHeaderBottom, BorderLayout.CENTER);

				vehiclePageActive = false;
			}
		});
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(DriveMeConstants.Colour.primaryColor);	
				customerButton.setForeground(Color.WHITE);
				vehicleButton.setBackground(DriveMeConstants.Colour.secondColor);	
				vehicleButton.setForeground(DriveMeConstants.Colour.primaryColor);

				bodyContentPanel = getDriveMeUtil().clearAndSetContent(bodyContentPanel, vehicleContent, null);
//				getDriveMeUtil().clearAndSetContentForBodyPanel(vehicleContent);
				
				getDriveMeUtil().clearAndSetContent(headerBottom, vehicleHeaderBottom, BorderLayout.CENTER);

				vehiclePageActive = true;
			}
		});
		headerTop.add(customerButton);
	}
	
}
