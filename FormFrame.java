package budgetaryinfoXXYN;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.swing.ButtonGroup;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class FormFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 7729079824286964182L;
	
	protected JPanel panel;
	protected JPanel panelOne;
	protected JPanel panelTwo;
	protected JPanel panelThree;
	private JTabbedPane tabs;
	private GridBagConstraints c = new GridBagConstraints();
	private GridBagConstraints cTwo = new GridBagConstraints();	
	Insets insets = c.insets;
	
	private JLabel title, resultsTitle, iDTitle, firstName, age, grossYearlyIncome, totalDebt, carBrand, blank, blankOne, highlightOne, highlightTwo, highlightThree, highlightFour, analysisTitle, jobPoll;
	private JTextPane yourUniqueID, yourFinalScore, jobCategories;
	private JTextField firstNameContent, ageContent, grossYearlyIncomeContent, totalDebtContent, cityContents, state, getResults, getJobIdeas;
	private JComboBox<String> carB;
	private JCheckBox hasKidsContent;
	private JTextArea ErrorMessage, eatingOut, city, iDDirections, highLights, considerationMessage;
	private JSlider resturantSurveying;
	private String[] statePostal;
	private JButton submitForm, addMore, editPrevious, goToLogin, matchResults, findACareer;
	private int width = 575;
	private int height =  575;
	
	private String textOne;
	private String stateAbbrev;
	private String longDirections;
	private String userVerificationCode;
	private String stateAt;
	private String carBrandB;

	private String identifier;
	private int indexPos;
    private int top = 20;
    private int left = 20;
    private int bottom = 2;
    private int right = 0;
    
    private float newSize;
    private float resultsNewSize;
    private float resultsNewSizeOne;
    private float resultsNewSizeTwo;
    private float errorSize;
    private float resultsThreeNewSize;

    private String[] finalTallyScore;
	int startCell;
	int rowNumber;
	int lastRowIndex;
	
	public FormFrame() {
		
		stateAbbrev = "AL,AK,AZ,AR,CA,CO,CT,DE,FL,GA,HI,ID,IL,IN,IA,KS,KY,LA,ME,MD,MA,MI,"
				+ "MN,MS,MO,MT,NE,NV,NH,NJ,NM,NY,NC,ND,OH,OK,OR,PA,RI,SC,SD,TN,TX,UT,VT,VA,WA,WV,WI,WY";
		statePostal = stateAbbrev.split(",");
		
		// Using grid layout
		panel = new JPanel(new GridBagLayout());
		add(panel);
		
		// Results panel
		panelOne = new JPanel(new GridBagLayout());
		add(panelOne);
		
		panelTwo = new JPanel(new GridBagLayout());
		// Tab Options
		
		panelThree = new JPanel(new GridBagLayout());
		
		tabs = new JTabbedPane();
		
		tabs.add("Survey",panel);
		tabs.add("Results",panelOne);
		tabs.add("Login Info", panelTwo);
		tabs.add("Report", panelThree);
		add(tabs);
		tabs.setEnabledAt(1, false);
		tabs.setEnabledAt(2, false);
		tabs.setEnabledAt(3, false);
		
		// Unique Id Page
		// Set all panelTwo contents to not visible until submit button is clicked.
		
		longDirections = "Please copy the unique identifier. \n This will be required to access your results. \n "
				+ "NOTE: You will NOT be able to return \n once you click login.";
		iDDirections = new JTextArea();
		iDDirections.setText(longDirections);
		cTwo.gridx = 1;
		cTwo.gridy = 0;
		cTwo.anchor = GridBagConstraints.CENTER;
		panelTwo.add(iDDirections, cTwo);
		
		resultsNewSizeOne = 20;
		iDTitle = new JLabel("YOUR LOGIN ID");
		iDTitle.setFont(iDTitle.getFont().deriveFont(resultsNewSizeOne));
		cTwo.gridx = 1;
		cTwo.gridy = 1;
		cTwo.anchor = GridBagConstraints.WEST;
		panelTwo.add(iDTitle, cTwo);
		
		
		blank = new JLabel("*");
		cTwo.gridx = 1;
		cTwo.gridy = 2;
		cTwo.anchor = GridBagConstraints.WEST;
		panelTwo.add(blank, cTwo);
		
		resultsNewSizeTwo = 20;
		yourUniqueID = new JTextPane();
		yourUniqueID.setText("");
		yourUniqueID.setFont(yourUniqueID.getFont().deriveFont(resultsNewSizeTwo));
		cTwo.gridx = 1;
		cTwo.gridy = 3;
		cTwo.anchor = GridBagConstraints.CENTER;
		panelTwo.add(yourUniqueID, cTwo);
		
		blankOne = new JLabel("*");
		cTwo.gridx = 1;
		cTwo.gridy = 4;
		cTwo.anchor = GridBagConstraints.WEST;
		panelTwo.add(blankOne, cTwo);
		

		goToLogin = new JButton("LOGIN");
		goToLogin.addActionListener(this);
		cTwo.gridx = 1;
		cTwo.gridy = 5;
		cTwo.anchor = GridBagConstraints.CENTER;
		panelTwo.add(goToLogin, cTwo);
		
		// Results Page
		resultsNewSize = 20;
		resultsTitle = new JLabel("Enter Code, Get Results");
		resultsTitle.setFont(resultsTitle.getFont().deriveFont(resultsNewSize));
		cTwo.gridx = 1;
		cTwo.gridy = 0;
		cTwo.anchor = GridBagConstraints.WEST;
		panelOne.add(resultsTitle, cTwo);
		
		
		getResults = new JTextField();
		getResults.setColumns(20);
		cTwo.gridx = 1;
		cTwo.gridy = 1;
		cTwo.anchor = GridBagConstraints.CENTER;
		panelOne.add(getResults,cTwo);
		
		matchResults = new JButton("VERIFY CODE");
		matchResults.addActionListener(this);
		cTwo.gridx = 1;
		cTwo.gridy = 2;
		cTwo.anchor = GridBagConstraints.CENTER;
		panelOne.add(matchResults,cTwo);
		
		

		// Title of Form 
		newSize = 20;
		title = new JLabel("BUDGET ANALYSIS");
		title.setFont(title.getFont().deriveFont(newSize));
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panel.add(title, c);
		
		// First name components
		firstName = new JLabel("First Name: ");
		firstNameContent = new JTextField();
		firstNameContent.setColumns(15);
		
	    c.insets = new Insets(top, left, bottom, right);
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		panel.add(firstName, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		panel.add(firstNameContent, c);
		
		
		// Debt contents
		totalDebt = new JLabel("Non-Mortgage Debt: ");
		totalDebtContent = new JTextField();
		totalDebtContent.setColumns(15);
		totalDebtContent.setInputVerifier(new MyInputVerifier());
		
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panel.add(totalDebt, c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panel.add(totalDebtContent, c);
		
		
		// Income contents
		grossYearlyIncome = new JLabel("Gross Yearly Income: ");
		grossYearlyIncomeContent = new JTextField();
		grossYearlyIncomeContent.setColumns(15);
		grossYearlyIncomeContent.setInputVerifier(new MyInputVerifier());
		
		
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		panel.add(grossYearlyIncome, c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		panel.add(grossYearlyIncomeContent, c);
		
		// Age contents
		age = new JLabel("Age: ");
		
		ageContent = new JTextField();
		ageContent.setColumns(15);
		ageContent.setInputVerifier(new MyInputVerifier());
		

		c.gridx = 0;
	    c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		panel.add(age,c);
		
		c.gridx = 1;
		panel.add(ageContent,c);
		
		// Car Brand Contents
		carBrand = new JLabel("Car Brand: ");
		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.WEST;
		panel.add(carBrand, c);
		
		// complete ArrayList called to be used in ComboBox
		ArrayList<String> carList = CarList.Arr(); // 
	    String[] carListArray = new String[carList.size()];
	    carListArray = carList.toArray(carListArray); 
		
	    // Combo-box Car Brand drop down
	    carB = new JComboBox<String>(carListArray); 
	    carB.setEnabled(true);
	    
		AutoCompletion.enable(carB);
		c.gridx = 1;
		c.gridy = 7;
		carB.addActionListener(carB);
		c.anchor = GridBagConstraints.WEST;
		panel.add(carB, c);
		
		// Has kids content
		
		hasKidsContent = new JCheckBox("KIDS?"); 
		c.gridx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.WEST;
		panel.add(hasKidsContent, c);
		
		// Validation error message 
		errorSize = 14;
		ErrorMessage = new JTextArea("Please enter a number");
		ErrorMessage.setBackground(Color.RED);
		ErrorMessage.setFont(ErrorMessage.getFont().deriveFont(errorSize));
		c.gridx = 0;
		c.gridy = 10;
		panel.add(ErrorMessage, c);
		ErrorMessage.setVisible(false);
		
		// Submit button contents
		submitForm = new JButton("SUBMIT");
		submitForm.addActionListener(this);
		submitForm.setEnabled(false);
		c.gridx = 3;
		c.gridy = 17;
		c.anchor = GridBagConstraints.CENTER;

		panel.add(submitForm, c);
		
		addMore = new JButton("ADD MORE INFO");
		addMore.setEnabled(false);
		addMore.addActionListener(this);
		c.gridx = 1;
		c.gridy = 11;
		c.anchor = GridBagConstraints.WEST;
		panel.add(addMore, c);
		
		editPrevious = new JButton("EDIT PREVIOUS");
		editPrevious.addActionListener(this);
		editPrevious.setEnabled(false);
		c.gridx = 1;
		c.gridy = 11;
		c.anchor = GridBagConstraints.WEST;
		panel.add(editPrevious, c);
		
		
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	

	
	ArrayList<String> txtFieldContentsAL = new ArrayList<>();
	

			public void actionPerformed(ActionEvent e) {
				

		        if (e.getSource() == submitForm) {
		    		
		        	String todayDate = GetCurrentDate.getToday();
		        	String 	carBrandSelection = carB.getSelectedItem().toString();
		        	String withChildren = Boolean.toString(hasKidsContent.isSelected());
		        	String resturantSurveyValue = String.valueOf(resturantSurveying.getValue());
		        	
		        	String[] textFields = {firstNameContent.getText(), totalDebtContent.getText(), grossYearlyIncomeContent.getText(), 
		        			ageContent.getText(), carBrandSelection, withChildren, resturantSurveyValue, cityContents.getText().toString(), state.getText().toString(),todayDate}; 
		        	int numTextFields = textFields.length;

		        	for (int i = 0; i < numTextFields; i ++) {
		        		txtFieldContentsAL.add(textFields[i]);
		        	}
		        	
					identifier = UniqueIdentifier.createUniqueKey();	 
					txtFieldContentsAL.add(0, identifier);
					
				     
					try {
						sendData();
					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
		    		tabs.setEnabledAt(0,false);
		    		tabs.setEnabledAt(1,false);	    		
		        	tabs.setSelectedIndex(2);
		        	
		        	yourUniqueID.setText(identifier);
		        	
		        	// Reset all inputs to default
		        	firstNameContent.setText("");
		        	totalDebtContent.setText("");
		        	totalDebtContent.setText("");
		        	grossYearlyIncomeContent.setText("");
		        	ageContent.setText("");
		        	carB.setSelectedIndex(0);
		        	hasKidsContent.setSelected(false);
		        	resturantSurveying.setValue(15);
		        	cityContents.setText("");
		        	state.setText("");
		        	addMore.setSelected(false);
		        	editPrevious.setSelected(true);
		        	System.out.println(txtFieldContentsAL.toString());
		        }
		        
		        	
		        	
		        	if (e.getSource() == addMore) {
		        		submitForm.setEnabled(true);
		        		setSize(width, (int) (height*1.5));
		        		addMore.setVisible(false);
		        		firstNameContent.setVisible(false);
		        		totalDebtContent.setVisible(false);
		        		grossYearlyIncomeContent.setVisible(false);
		        		ageContent.setVisible(false);
		        		carB.setVisible(false);
		        		hasKidsContent.setVisible(false);
		        		editPrevious.setEnabled(true);
		        		
		        		eatingOut = new JTextArea("Eating Out Frequency \n per Month");
		        		c.gridx = 0;
		        		c.gridy = 14;
		        		c.anchor = GridBagConstraints.WEST;
		        		panel.add(eatingOut, c);
		        		
		        		resturantSurveying = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);  
		        		resturantSurveying.setMinorTickSpacing(2);  
		        		resturantSurveying.setMajorTickSpacing(10);  
		        		resturantSurveying.setPaintTicks(true);  
		        		resturantSurveying.setPaintLabels(true); 

		        		c.gridx = 1;
		        		c.gridy = 14;
		        		c.anchor = GridBagConstraints.WEST;
		        		panel.add(resturantSurveying, c);
		        		
		        		// Place of residence contents 
		        		city = new JTextArea("Place of Residence \n City, State (Lynn, CO)");
		        		c.gridx = 0;
		        		c.gridy = 15;
		        		c.anchor = GridBagConstraints.WEST;
		        		panel.add(city, c);
		        		
		        		// City
		        		cityContents = new JTextField();
		        		cityContents.setColumns(15);
		        		c.gridx = 1;
		        		c.gridy = 15;
		        		c.anchor = GridBagConstraints.WEST;
		        		panel.add(cityContents, c);
		        		
		        		// State (only valid US postal addresses)
		        		state = new JTextField();
		        		state.setInputVerifier(new MyInputVerifierState());
		        		state.setColumns(4);
		        		c.gridx = 3;
		        		c.gridy = 15;
		        		c.anchor = GridBagConstraints.WEST;
		        		panel.add(state, c);
		        	
		        		
		        		
		        		
		        	}
		        		if (e.getSource() == editPrevious) {
			        		addMore.setVisible(true);
			        		firstNameContent.setVisible(true);
			        		totalDebtContent.setVisible(true);
			        		grossYearlyIncomeContent.setVisible(true);
			        		ageContent.setVisible(true);
			        		carB.setVisible(true);
			        		hasKidsContent.setVisible(true);
		        	}
		        		
		        		if (e.getSource() == findACareer) {
		        		
		        			String jobListGiant = "Careers: Agriculture and Natural Resources Agriculture, Food and Natural Resources Careers Careers: Architecture and Construction Architecture and Construction Careers Careers: Education and Training Education and Training Careers Careers: Business Management and Administration Business Management and Administration Careers Careers: Arts, Audio/Video Technology and Communications Arts, Audio/Video Technology and Communications Careers Careers: Finance Finance Careers Careers: Government and Public Administration Government and Public Administration Careers Careers: Health Science Health Science Careers Careers: Hospitality and Tourism Hospitality and Tourism Careers Careers: Human Services Human Services Careers Careers: Information Technology Information Technology Careers Careers: Law, Public Safety, Corrections and Security Law, Public Safety, Corrections and Security Careers Careers: Manufacturing Manufacturing Careers Careers: Marketing, Sales and Service Marketing, Sales and Service Careers Careers: Science, Technology, Engineering and Mathematics Science, Technology, Engineering and Mathematics Careers Careers: Transportation, Distribution and Logistics Transportation, Distribution and Logistics Careers";
		        			jobCategories = new JTextPane();
		        			jobCategories.setSize(40, 20);
		        			jobCategories.setText(jobListGiant);
		        			c.gridx = 2;
		        			c.gridy = 6;
		        			panelThree.add(jobCategories, c);
		        			
		        			getJobIdeas = new JTextField();
		        			getJobIdeas.setColumns(25);
		        			c.gridx = 2;
		        			c.gridy = 7;
		        			c.anchor = GridBagConstraints.WEST;
		        			panelThree.add(getJobIdeas, c);
		        			
		        			jobPoll = new JLabel("Copy/paste up to 3 from above");
		        			c.gridx = 1;
		        			c.gridy = 7;
		        			c.anchor = GridBagConstraints.WEST;
		        			panelThree.add(jobPoll, c);
		        			
		        		}
		        		
		        		if(e.getSource() == matchResults ) {
		        			// execute query
		        			userVerificationCode = getResults.getText().toString();
		        			String stateFromAL = txtFieldContentsAL.get(9);
		        			stateAt = stateFromAL;
		        			String carBrandFromAL = carB.getSelectedItem().toString();
		        			carBrandB = carBrandFromAL;
		        			
		        
		        			SQLConnect sqlConn = new SQLConnect();
		        			try {
		        				
								sqlConn.getPrimary(userVerificationCode);
								sqlConn.getStateInfo(stateAt);
								sqlConn.isLux(carBrandB);
							System.out.println(sqlConn.yourBudgetScore());
								
								// sqlConn.getStateInfo(stateAt);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		        			
							finalTallyScore = sqlConn.yourBudgetScore();
							
					
		        			tabs.setEnabledAt(3, true);
		        			tabs.setEnabledAt(0, false);
		        			tabs.setEnabledAt(1, false);
		        			tabs.setEnabledAt(2, false);
		        			
		        			tabs.setSelectedIndex(3);
		        			
		        			String hiName = finalTallyScore[7];
		        			
		        			double totalScore = Double.parseDouble(finalTallyScore[10]);
		        			
		        			String resultSentiment = "";
		        			if (totalScore < 40) {
		        				 resultSentiment = ". gives you an F. You have some areas  that need improvement";
		        			}
		        			if (totalScore >= 40 && totalScore < 60) {
			        				 resultSentiment = "results in a C.\nYou could use some work on certain areas of your budget.";
		        			}
		        			if (totalScore >= 60 && totalScore < 80) {
		        				 resultSentiment = "returns a B! Consider looking through highlighted areas of concern.";
	        			}
		        			if (totalScore >= 80 && totalScore < 90) {
		        				 resultSentiment = "returns an A! Good work! \n Here are some thoughts:";
	        			}
		        			if (totalScore >= 90 && totalScore < 130) {
		        				 resultSentiment = "returns an A+! Fantastic work.";
	        			}
		        			
		        			String[] namesInFinalList = {"Debt To Income", "Poor Wealth Habits", "Life Circumstances", "Home cost of living", "Income", "Debt", "Car", "Eating Out", "State"};
		        			double debtInc = Double.parseDouble(finalTallyScore[0]); 
		        			double povertyScore = Double.parseDouble(finalTallyScore[1]);
		        			double kidsScore = Double.parseDouble(finalTallyScore[2]);
		        			double colScore = Double.parseDouble(finalTallyScore[3]);
		        			
		        			String debtTo, lifestyleTo, famTo, coo;
		        			if (finalTallyScore[0].length() > 3) {
		        				debtTo = finalTallyScore[0].substring(2, 4);
		        			}
		        			if (finalTallyScore[0].length() == 3) {
		        				debtTo = finalTallyScore[0].substring(2, 3);
		        			}
		        			else {
		        				debtTo = finalTallyScore[0];
		        			}
		        			
		        			if (finalTallyScore[1].length() > 3) {
		        				lifestyleTo = finalTallyScore[1].substring(2, 4);
		        			}
		        			if (finalTallyScore[1].length() == 3) {
		        				lifestyleTo = finalTallyScore[1].substring(2, 3);
		        			}
		        			else {
		        				lifestyleTo = finalTallyScore[1];
		        			}
		        			
		        			if (finalTallyScore[2].length() > 3) {
		        				famTo = finalTallyScore[2].substring(2, 4);
		        			}
		        			if (finalTallyScore[2].length() == 3) {
		        				famTo = finalTallyScore[2].substring(2, 3);
		        			}
		        			else {
		        				famTo = finalTallyScore[2];
		        			}
		        			
		        			if (finalTallyScore[3].length() > 3) {
		        				coo = finalTallyScore[3].substring(2, 4);
		        			}
		        			if (finalTallyScore[3].length() == 3) {
		        				coo = finalTallyScore[3].substring(2, 3);
		        			}
		        			else {
		        				coo = finalTallyScore[3];
		        			}
		        			
		        			String resultStatus = "";
		        			if (debtInc < 15) {
		        				resultStatus = "well below acceptable range";
		        			}
		        			
		        			if (debtInc >= 15 && debtInc < 28) {
		        				resultStatus = "below accetable range";
		        			}
		        			if (debtInc >= 28 && debtInc < 40) {
		        				resultStatus = " needing work";
		        			}	
		        			if (debtInc >= 40 && debtInc < 60) {
		        				resultStatus = "not bad, but not ideal ";
		        			}		        			
		        			
		        			if (debtInc >= 60 && debtInc < 80) {
		        				resultStatus = "needing hard work, but you can clear the debt soon.";
		        			}		        			
		        			
		        			if (debtInc >= 80 && debtInc < 100) {
		        				resultStatus = " managable...in less than six months and you'll be free of debt!";
		        			}		        			
		        			
		        			if (debtInc >= 100) {
		        				resultStatus = "fantastic. Debt free! Amazing.";
		        			}	
		        			
		        			highlightOne = new JLabel("Your Debt to Income score component is: " + resultStatus + "at " + debtTo  + "% of 100.");
		        			highlightTwo = new JLabel("Your lifestyle score component is: " + lifestyleTo  + "% of 100.");
		        			highlightThree = new JLabel("Your income and family score component is: " + famTo + "% of 100.");
		        			highlightFour = new JLabel("Your cost of living score component: " + coo + "% of 100.");
		        			
		        			
		        			double maxA = Math.min(debtInc, povertyScore);
		        			double maxB = Math.min(kidsScore, colScore);
		        			double worstComponent = Math.min(maxA, maxB);
		        			
		        			System.out.println(maxA);
		        			System.out.println(maxB);
		        			
		        			String worstC = String.valueOf(worstComponent);
		        			int worstComponentIndex = 0;
		        			for (int i = 0; i < 4; i++) {
		        				if (finalTallyScore[i].equals(worstC)) {
		        					worstComponentIndex = i;
		        					System.out.println(i);
		        				}
		        			}
		        			
		        			
		        			highLights = new JTextArea(8, 40);
		        			highLights.setText("Hi " + hiName +  ": \nThank you for completing this report. \n \n Your score is " + finalTallyScore[10].substring(0, 4) + ", which " 
		        			+ resultSentiment + " \n \n To make amendments to your financial status, please consider looking at your  \n " + namesInFinalList[worstComponentIndex] + " ratio");
		        			
	
		        			String considerationsMessage = "";
		        			if (namesInFinalList[worstComponentIndex].equals("Debt To Income")) {
		        				considerationsMessage = "Your debt/income ratio of " + finalTallyScore[14].substring(0, 2) + "%, compared to your income of "
		        			 + finalTallyScore[5] + " is not \n ideal. You should use the resourses below to work on that income!";
		        				
		        			findACareer	= new JButton("BROWSE JOBS");
		        			
		        			c.gridx = 2;
		        			c.gridy = 5;
		        			c.anchor = GridBagConstraints.CENTER;
		        			panelThree.add(findACareer, c);
		        				
		        				
		        				
		        			}
		        			
		        			if(namesInFinalList[worstComponentIndex].equals("Poor Wealth Habits")) {
		        				considerationsMessage = "Your state of residence," + finalTallyScore[11] + ", has a cost of living of " + finalTallyScore[12] + ""
		        						+ ".\n Your income of" + finalTallyScore[4] + " should work to be well above the cost of living. \n Preferabley well "
		        								+ "above if you are supporting family. Additionally, \n your lifestyle must match your income and associated. \n"
		        								+ "Eating out frequently or choosing to drive a " + finalTallyScore[4] + "\n or eating out " +   finalTallyScore[4]  + " a month may impact your \n "
		        										+ "likelihood of being wealthy."; 
		        			}
		        			
		        			if (namesInFinalList[worstComponentIndex].equals("Life Circumstances")) {
		        				considerationsMessage = "The average cost of rasing one child is about $15,000 a year \n. " + "In " + finalTallyScore[11] + ", the cost of living index is: " + finalTallyScore[13] + " You"
		        						+ " have a living wage of " + finalTallyScore[12] + " so you should aim for well above that cost - especially with a family.";
		        			}
		        			// (income / living wage / )*100
		        			double inc = Double.parseDouble(finalTallyScore[5]);
		        			double coll = Double.parseDouble(finalTallyScore[12]); 
		        			double incomeToLivingWage = (inc/coll)*100;
		        			
		        			if (namesInFinalList[worstComponentIndex].equals("Home cost of living")) {
		        				considerationsMessage = "While beautiful, " + finalTallyScore[11] + " is expensive!! You have a \n living wage of " + finalTallyScore[12] +". "
		        						+ " Keep in mind that doesn't cover luxury. Your income is " + incomeToLivingWage + "% compared to the living wage.";
		        			}
		        				
		        			
		        			
		        			resultsThreeNewSize = 20;
		        			analysisTitle = new JLabel("Your COMBINED SCORE");
		        			analysisTitle.setFont(analysisTitle.getFont().deriveFont(resultsThreeNewSize));
		        			analysisTitle.setForeground(Color.BLUE);
		        			c.gridx = 2;
		        			c.gridy = 0;
		        			c.anchor = GridBagConstraints.CENTER;
		        			panelThree.add(analysisTitle, c);
		        			
		        			
		        			String resultSe = "";
		        			if (totalScore < 40) {
		        				resultSe = "F";
		        			}
		        			if (totalScore >= 40 && totalScore < 50) {
		        				resultSe = "C-";
		        			}
		        			if (totalScore >= 50 && totalScore < 60) {
		        				resultSe = "C";
		        			}
		        			if (totalScore >= 60 && totalScore < 70) {
		        				resultSe = "B-";
	        			}
		        			if (totalScore >= 70 && totalScore < 80) {
		        				resultSe = "B+";
	        			}
		        			if (totalScore >= 80 && totalScore < 85) {
		        				resultSe = "A-";
	        			}
		        			if (totalScore >= 85 && totalScore < 90) {
		        				resultSe = "A";
	        			}
		        			if (totalScore >= 90 && totalScore < 130) {
		        				resultSe = "A+";
	        			}
		        			
		        			
		        			c.gridx = 2;
		        			c.gridy = 1;
		        			c.anchor = GridBagConstraints.CENTER;
		        			yourFinalScore = new JTextPane();
		        			yourFinalScore.setText(resultSe + ":   " + finalTallyScore[10].substring(0,2) + "%");
		        			panelThree.add(yourFinalScore, c);
	
/*
 * 		        			c.gridx = 2;
		        			c.gridy = 1;
		        			c.anchor = GridBagConstraints.CENTER;
		        			yourFinalScore = new JTextPane();
		        			yourFinalScore.setText(finalTallyScore[10].substring(0,2));
		        			panelThree.add(yourFinalScore, c);
 */
		        			
		        			
		        			c.gridx = 2;
		        			c.gridy = 4;
		        			c.anchor = GridBagConstraints.WEST;
		        			panelThree.add(highlightOne, c);
		        			
		        			c.gridx = 2;
		        			c.gridy = 8;
		        			c.anchor = GridBagConstraints.WEST;
		        			panelThree.add(highlightTwo, c);
		        			
		        			c.gridx = 2;
		        			c.gridy = 9;
		        			c.anchor = GridBagConstraints.WEST;
		        			panelThree.add(highlightThree, c);
		        			
		        			c.gridx = 2;
		        			c.gridy = 10;
		        			c.anchor = GridBagConstraints.WEST;
		        			panelThree.add(highlightFour, c);
		        			
		        			
		        			c.gridx = 2;
		        			c.gridy = 2;
		        			c.anchor = GridBagConstraints.CENTER;
		        			panelThree.add(highLights, c);
		        			
			        		considerationMessage = new JTextArea(6,40);
			        		considerationMessage.setText(considerationsMessage);
		        			c.gridx = 2;
		        			c.gridy = 3;
		        			c.anchor = GridBagConstraints.CENTER;
		        			panelThree.add(considerationMessage, c);
	        			
		        		}
		        		
		
		        		if(e.getSource() == goToLogin) {
				        	tabs.setSelectedIndex(1);
				        	tabs.setEnabledAt(1, true);
				    		tabs.setEnabledAt(0,true);
				    		tabs.setEnabledAt(2,false);	    		
		        		}

			}
			
			public class MyInputVerifier extends InputVerifier {
			    @Override
			    public boolean verify(JComponent input) {
			    	if (!ageContent.getText().isEmpty()) {
			    		addMore.setEnabled(true);
			    	}
			        String text = ((JTextField) input).getText();
			        try {
			        	int intValue = Integer.parseInt(text);
			        	ErrorMessage.setVisible(false);
			        	return true;
			        } catch (NumberFormatException e) {
			        	ErrorMessage.setText("Verify your input");
			        	ErrorMessage.setVisible(true);
			            return false;
			        }
			    }
			}
			public class MyInputVerifierState extends InputVerifier {
			    @Override
			    public boolean verify(JComponent input) {
			        textOne = ((JTextField) input).getText();
			        indexPos = 52;
			        for (int i = 0; i<statePostal.length; i++) {
			            if (statePostal[i].equals(textOne)) {
			                indexPos = i;
			            }
			        }
			        try {
			        	String stateCode = statePostal[indexPos];
			        	ErrorMessage.setVisible(false);
			        	return true;
			        } catch (ArrayIndexOutOfBoundsException e) {
			        	ErrorMessage.setText("Invalid Abbreviation \n"
			        			+ "Example: MA");
			        	ErrorMessage.setVisible(true);
			            return false;
			        }
			    }
			}
			
			public void sendData() throws IOException, SQLException {
				
				 {
					 
					 String[] columnLabels = {"ID", "firstName", "debt", "yearlyIncome", "age", "carBrand", "kids", "eatingOut",
							 "city", "state", "submisionDate"};
					 }
					 
					 String[] budgetInfoDataArray = new String[txtFieldContentsAL.size()];
				     
				     for (int i = 0; i < budgetInfoDataArray.length; i++) {
				    	 budgetInfoDataArray[i] = txtFieldContentsAL.get(i);
				     }
				     
				     String uniqueId = budgetInfoDataArray[0];
				     String firstName =  budgetInfoDataArray[1];
				     int debt = Integer.parseInt(budgetInfoDataArray[2]);
				     int income = Integer.parseInt(budgetInfoDataArray[3]);
				     int age = Integer.parseInt(budgetInfoDataArray[4]);
				     String carBrand = budgetInfoDataArray[5];
				     boolean hasKids = Boolean.parseBoolean(budgetInfoDataArray[6]);
				     int eatingOut = Integer.parseInt(budgetInfoDataArray[7]);
				     String city = budgetInfoDataArray[8];
				     String state = budgetInfoDataArray[9];
				     String submitDate = budgetInfoDataArray[10];
				  
				     
				     SQLConnect sqlConnect = new SQLConnect();
				     sqlConnect.insert(uniqueId, firstName, debt, income, age, carBrand, hasKids, eatingOut, city, state, submitDate);
				     

				}
			

}





