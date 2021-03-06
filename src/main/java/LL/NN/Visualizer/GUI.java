package LL.NN.Visualizer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.Button;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javafx.*;
import javax.swing.JFileChooser;
import java.io.*;
public class GUI {
	
	//Initialize modifier = new Initialize();
	static NeuralNetwork gate = new NeuralNetwork();
	static int input = 2;
	static int hlayers = 1;
	static int hneurons = 6;
	static int output = 1;

	public JFrame frmNeuralNetworkVisualizer;
	private JTextField u_outputNeurons;
	private JTextField u_inputNeurons;
	private JTextField u_hiddenLayers;
	private JTextField u_hiddenNeurons;
	JTextField u_rate;
	JTextField u_iterations;
	
	JTextArea m_trainingData;
	
	static JRadioButton enableXOR;
	
	JRadioButton h_relu,h_sigmoid,o_relu,o_sigmoid;

	static JRadioButton enableNOR;
	static JRadioButton enableAND;
	static JCheckBox displayLoss;
	static boolean manualTraining;
	static boolean manualFile;
	static JTextArea u_input;
	TrainingData parsedUserInput;
	static boolean networkTrained = false;
	private final ButtonGroup hidden_group = new ButtonGroup();
	private final ButtonGroup output_group = new ButtonGroup();
	JTextField filePath;
	JCheckBox splittedData;

		
		
	
	public void manualTraining(String data1) {
		String [] data = data1.split("\\n");
		float [][] tData = new float[data.length][data1.split("\\n")[0].split("[,:]").length];
		
		for (int i = 0; i < data.length; i++) {
			String [] splitted = data1.split("\\n")[i].split("[,:]");
			for (int b =0; b < splitted.length; b++) {
				tData[i][b] = Float.parseFloat(splitted[b]);
			}
			
		
		}
		
		gate.tDataSet = new TrainingData[data.length];
		for (int i = 0; i < data.length; i++) {
			gate.tDataSet[i] = new TrainingData(Arrays.copyOfRange(tData[i],0,tData[0].length-1),
					Arrays.copyOfRange( tData[i],tData[0].length-1, tData[0].length));
		}
		
//		for (int i =0; i < gate.tDataSet.length; i++) {
//			System.out.println(Arrays.toString(gate.tDataSet[i].data));
//			System.out.println(Arrays.toString(gate.tDataSet[i].expectedOutput));
//			System.out.println("======================");
//		}
	}
	
	//Training with splitted user input data
	public void splittedTraining(String data1) {
		String [] data = data1.split("\\n");
		float [][] tData = new float[data.length][data1.split("\\n")[0].split("[,:]").length];
		
		for (int i = 0; i < data.length/2; i++) {
			String [] splitted = data1.split("\\n")[i].split("[,:]");
			for (int b =0; b < splitted.length; b++) {
				tData[i][b] = Float.parseFloat(splitted[b]);
			}
		}
		
		gate.tDataSet = new TrainingData[data.length/2];
		for (int i = 0; i < data.length/2; i++) {
			gate.tDataSet[i] = new TrainingData(Arrays.copyOfRange(tData[i],0,tData[0].length-1),
					Arrays.copyOfRange( tData[i],tData[0].length-1, tData[0].length));
		}
		//System.out.println("tdataset is:" + gate.tDataSet.length);
		
//		for (int i =0; i < gate.tDataSet.length; i++) {
//			System.out.println(Arrays.toString(gate.tDataSet[i].data));
//			System.out.println(Arrays.toString(gate.tDataSet[i].expectedOutput));
//			System.out.println("======================");
//		}
	}
	
	//Output with splitted data provided by user
	public void splittedOutput(String data1) {
		String [] data = data1.split("\\n");
		float [][] tData = new float[data.length][data1.split("\\n")[0].split("[,:]").length];
		
		for (int i = 0; i < data.length; i++) {
			String [] splitted = data1.split("\\n")[i].split("[,:]");
			for (int b =0; b < splitted.length; b++) {
				tData[i][b] = Float.parseFloat(splitted[b]);
			}
		}
		
		TrainingData[] tDataSet = new TrainingData[data.length/2];
		for (int i = 0; i < data.length/2; i++) {
			tDataSet[i] = new TrainingData(Arrays.copyOfRange(tData[i],tData[0].length/2,tData[0].length-1),
					Arrays.copyOfRange( tData[i],tData[0].length-1, tData[0].length));
		}
		
		
		float accuracy = 0;
		
		//tDataSet.length
		
		System.out.println("Output after training with testing data");
		System.out.println("======================");
		for (int i = 0; i < tDataSet.length;i++) {

			gate.forward(tDataSet[i].data);
			
			
			//THIS IS ONLY FOR BINARY OUTPUT!!!!
			float answer = (float)Math.round(gate.layers[gate.layers.length-1].neurons[0].value);
			
			//this only works with one output neuron
			accuracy+= Math.abs(tDataSet[i].expectedOutput[0] - answer) ;
			
			if (i < 10) {
				for (int j = 0; j < tDataSet[i].data.length; j++) {
					System.out.print(tDataSet[i].data[j] + " ");
				}
				System.out.print("\t");
				System.out.println(answer);
			}

			

			//System.out.println("")
		}
		System.out.println("...");
		System.out.println("----------------------");
		System.out.println("Accuracy: " +  (1 - (accuracy/tDataSet.length)));
		System.out.println("======================");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmNeuralNetworkVisualizer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		
//		GUI window = new GUI();
//		window.frmNeuralNetworkVisualizer.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNeuralNetworkVisualizer = new JFrame();
		frmNeuralNetworkVisualizer.setTitle("Neural Network Visualizer");
		frmNeuralNetworkVisualizer.getContentPane().setBackground(new Color (238,238,238));
		frmNeuralNetworkVisualizer.setBounds(100, 100, 980, 517);
		frmNeuralNetworkVisualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNeuralNetworkVisualizer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 433, 477);
		frmNeuralNetworkVisualizer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(6, 6, 421, 248);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 6, 409, 47);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		u_inputNeurons = new JTextField();
		u_inputNeurons.setBounds(157, 5, 130, 26);
		panel_3.add(u_inputNeurons);
		u_inputNeurons.setColumns(10);
		
		JButton submitInput = new JButton("Submit");
		submitInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					input = Integer.parseInt(u_inputNeurons.getText());
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("======================");
					
					
				}
				catch(Exception E) {
			
				}
			}
		});
		submitInput.setBounds(315, 5, 88, 29);
		panel_3.add(submitInput);
		
		JLabel lblNewLabel_2 = new JLabel("Input Neurons");
		lblNewLabel_2.setBounds(6, 11, 90, 16);
		panel_3.add(lblNewLabel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 65, 409, 47);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Hidden Layers");
		lblNewLabel_1.setBounds(6, 11, 89, 16);
		panel_4.add(lblNewLabel_1);
		
		u_hiddenLayers = new JTextField();
		u_hiddenLayers.setBounds(157, 6, 130, 26);
		panel_4.add(u_hiddenLayers);
		u_hiddenLayers.setColumns(10);
		
		
		/**
		 * Changing layers does work now! YAY!
		 */
		JButton submitHLayers = new JButton("Submit");
		submitHLayers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					hlayers = Integer.parseInt(u_hiddenLayers.getText());
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("======================");
					
				}
				catch(Exception E) {
					
				}

			}
		});
		submitHLayers.setBounds(315, 6, 88, 29);
		panel_4.add(submitHLayers);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 124, 409, 47);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Hidden Layer Neurons");
		lblNewLabel_4.setBounds(6, 11, 139, 16);
		panel_5.add(lblNewLabel_4);
		

		JButton submitHNeurons = new JButton("Submit");
		submitHNeurons.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					hneurons = Integer.parseInt(u_hiddenNeurons.getText());
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("======================");
				}
				catch(Exception E) {
					
				}
			}
		});
		submitHNeurons.setBounds(315, 6, 88, 29);
		panel_5.add(submitHNeurons);
		
		u_hiddenNeurons = new JTextField();
		u_hiddenNeurons.setBounds(157, 6, 130, 26);
		panel_5.add(u_hiddenNeurons);
		u_hiddenNeurons.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(6, 183, 409, 47);
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Output Neurons");
		lblNewLabel_3.setBounds(6, 11, 101, 16);
		panel_6.add(lblNewLabel_3);
		
		u_outputNeurons = new JTextField();
		u_outputNeurons.setBounds(157, 6, 130, 26);
		panel_6.add(u_outputNeurons);
		u_outputNeurons.setColumns(10);
		
		JButton submitOutput = new JButton("Submit");
		submitOutput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					output = Integer.parseInt(u_outputNeurons.getText());
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("======================");
					
				}
				catch(Exception E) {
					
				}
			}
		});
		submitOutput.setBounds(315, 6, 88, 29);
		panel_6.add(submitOutput);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setBounds(6, 266, 421, 205);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		m_trainingData = new JTextArea();
		m_trainingData.setBounds(6, 34, 409, 124);
		panel_7.add(m_trainingData);
		
		//Manual Training Data
		JLabel lblNewLabel = new JLabel("Manual Training Data ");
		lblNewLabel.setBounds(6, 6, 409, 16);
		panel_7.add(lblNewLabel);
		
		JButton submitMTD = new JButton("Submit");
		submitMTD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				manualTraining = true;
				System.out.println("Submitted Manual Training Data... ");
				System.out.println("======================");
				
				
				
			}
		});
		submitMTD.setBounds(144, 170, 117, 29);
		panel_7.add(submitMTD);
		submitMTD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		submitOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(451, 6, 295, 477);
		frmNeuralNetworkVisualizer.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setBounds(6, 266, 283, 207);
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(6, 6, 159, 195);
		panel_8.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Display Loss");
		lblNewLabel_10.setBounds(6, 6, 147, 16);
		panel_11.add(lblNewLabel_10);
		
		displayLoss = new JCheckBox("Enable");
		displayLoss.setBounds(16, 34, 128, 23);
		panel_11.add(displayLoss);
		
		JLabel lblNewLabel_11 = new JLabel("Display Gradient");
		lblNewLabel_11.setBounds(6, 69, 147, 16);
		panel_11.add(lblNewLabel_11);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Enable");
		chckbxNewCheckBox_1.setBounds(16, 97, 128, 23);
		panel_11.add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel_12 = new JLabel("Display Weight/Bias Changes");
		lblNewLabel_12.setBounds(6, 132, 147, 16);
		panel_11.add(lblNewLabel_12);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Enable");
		chckbxNewCheckBox_2.setBounds(16, 160, 128, 23);
		panel_11.add(chckbxNewCheckBox_2);
		
		
		
		JPanel panel_12 = new JPanel();
		panel_12.setBounds(177, 172, 100, 29);
		panel_8.add(panel_12);
		panel_12.setLayout(null);
		
		
		
		/**
		 * Once the train button is pressed
		 */
		JButton btnNewButton_2 = new JButton("Train");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String content = "";
					gate = new NeuralNetwork(input, hlayers, hneurons, output);
					

					if (manualTraining == true && m_trainingData.getText().trim().length() != 0) {
						manualTraining(m_trainingData.getText());
					}
					else if (manualFile == true) {
						try {
							content = new String (Files.readAllBytes(Paths.get(filePath.getText())), StandardCharsets.UTF_8);
							//System.out.println(content);
							if (splittedData.isSelected()) {
								splittedTraining(content);
							}
							
							else {
								manualTraining(content);
							}


						}
						catch(Exception E) {

						}

					}
					else {
						if (enableNOR.isSelected()) {
							gate.CreateNORTrainingData();
						}
						else if (enableXOR.isSelected()) {
							gate.CreateXORTrainingData();
						}
						else if (enableAND.isSelected()) {
							gate.CreateANDTrainingData();
						}
						else {
							System.out.println("Defaulting to XOR...");
							System.out.println("======================");
							gate.CreateXORTrainingData();
						}	
					}
					
					if (h_relu.isSelected()) {
						
						gate.type_hidden = 1;
//						System.out.println("Hidden ReLU Activation");
//						System.out.println("======================");
					}
					else {
//						System.out.println("Hidden Sigmoid Activation");
//						System.out.println("======================");
						gate.type_hidden = 0;
					}
					if (o_relu.isSelected()) {
//						System.out.println("Output ReLU Activation");
//						System.out.println("======================");
						gate.type_output = 1;
					}
					else {
//						System.out.println("Output Sigmoid Activation");
//						System.out.println("======================");
						gate.type_output = 0;
					}
					
					
					networkTrained = true;

					System.out.println("Output before training");
					System.out.println("======================");
					

					gate.printInfo();
					
				

					int ITERATIONS; float RATE;
					if (u_iterations.getText().isEmpty() == false) {
						ITERATIONS = Integer.parseInt(u_iterations.getText());
					}
					else {
						System.out.println("Defaulting to 1 million iterations... ");
						System.out.println("======================");
						ITERATIONS = 1000000;
					}
					if (u_rate.getText().isEmpty() == false) {

						RATE = Float.parseFloat(u_rate.getText());
					}
					else {
						System.out.println("Defaulting to LR of 0.05... ");
						System.out.println("======================");
						RATE = 0.05f;
					}
					
					
					if (displayLoss.isSelected() == true) {
						System.out.println("Output after training with training data");
						System.out.println("======================");
						gate.lossTrain(ITERATIONS, RATE);
						
				}
					else {
						gate.train(ITERATIONS, RATE);
						if (splittedData.isSelected()) {
							try {
								content = new String (Files.readAllBytes(Paths.get(filePath.getText())), StandardCharsets.UTF_8);
								
								splittedOutput(content);	
							}
							catch(Exception E) {
								
							}

						}
						else {
							System.out.println("Output after training with training data");
							System.out.println("======================");
							gate.printInfo();
						}



						

					}
					
			}
				
				
				catch (java.lang.OutOfMemoryError E) {
					System.out.println("Out of memory...");
				}
				


				
				
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(6, 6, 94, 23);
		panel_12.add(btnNewButton_2);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(177, 6, 100, 154);
		panel_8.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Learning Rate");
		lblNewLabel_13.setBounds(6, 6, 88, 16);
		panel_13.add(lblNewLabel_13);
		
		u_rate = new JTextField();
		u_rate.setBounds(6, 34, 88, 26);
		panel_13.add(u_rate);
		u_rate.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Iterations");
		lblNewLabel_14.setBounds(16, 72, 61, 16);
		panel_13.add(lblNewLabel_14);
		
		u_iterations = new JTextField();
		u_iterations.setBounds(6, 100, 88, 26);
		panel_13.add(u_iterations);
		u_iterations.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(6, 6, 283, 126);
		panel_1.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Presets");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(6, 6, 271, 16);
		panel_9.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("XOR Gate");
		lblNewLabel_6.setBounds(16, 94, 61, 16);
		panel_9.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("NOR Gate");
		lblNewLabel_8.setBounds(16, 64, 61, 16);
		panel_9.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("AND Gate");
		lblNewLabel_9.setBounds(16, 34, 61, 16);
		panel_9.add(lblNewLabel_9);
		
		enableAND = new JRadioButton("Enable");
		enableAND.setBounds(89, 34, 141, 23);
		panel_9.add(enableAND);
		
		enableNOR = new JRadioButton("Enable");
		enableNOR.setBounds(89, 60, 141, 23);
		panel_9.add(enableNOR);
		
		enableXOR = new JRadioButton("Enable");
		enableXOR.setBounds(89, 90, 141, 23);
		panel_9.add(enableXOR);
		
		//ADD BOUTTONS
		ButtonGroup group = new ButtonGroup();
		group.add(enableXOR);
		group.add(enableNOR);
		group.add(enableAND);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(6, 144, 283, 110);
		panel_1.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Manual Input");
		lblNewLabel_7.setBounds(6, 6, 271, 16);
		panel_10.add(lblNewLabel_7);
		
		u_input = new JTextArea();
		u_input.setBounds(6, 34, 271, 29);
		panel_10.add(u_input);
		
		
		
		//MANUAL INPUT AFTER NETWORK TRAINED
		JButton submit_uinput = new JButton("Submit");
		submit_uinput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (networkTrained == true) {
					
					
					
					try {
						

						
						
						String[] data = u_input.getText().split(",");
						float [] float_data = new float [data.length];
						for (int i = 0; i < data.length; i++) {
							float_data[i] = Float.parseFloat(data[i]);
						}
						gate.forward(float_data);
						
						//Print every neuron
						for (int i =0; i < gate.layers[gate.layers.length-1].neurons.length; i++) {
							for (int j = 0; j < float_data.length; j++) {
								System.out.print(float_data[j] + " ");
							}
							System.out.print("\t");
							System.out.print(Math.round(gate.layers[gate.layers.length-1].neurons[i].value));
						}
						System.out.println();
						System.out.println("======================");

						
					}
					catch (Exception E) {
						System.out.println("Invalid input");
						System.out.println("======================");
					}
					
					
				}
				else {
					System.out.println("Network has not been trained yet.");
					System.out.println("======================");
				}
			}
		});
		submit_uinput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		submit_uinput.setBounds(84, 75, 117, 29);
		panel_10.add(submit_uinput);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(758, 6, 216, 477);
		frmNeuralNetworkVisualizer.getContentPane().add(panel_14);
		panel_14.setLayout(null);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.LIGHT_GRAY);
		panel_15.setBounds(6, 6, 204, 248);
		panel_14.add(panel_15);
		panel_15.setLayout(null);
		
		JLabel lblNewLabel_17 = new JLabel("Import File Path");
		lblNewLabel_17.setBounds(52, 5, 99, 16);
		panel_15.add(lblNewLabel_17);
		
		filePath = new JTextField();
		filePath.setBounds(37, 26, 130, 26);
		panel_15.add(filePath);
		filePath.setColumns(10);
		
		splittedData = new JCheckBox("Split data");
		splittedData.setBounds(10, 60, 91, 23);
		panel_15.add(splittedData);
		
		JButton submitFile = new JButton("Submit");
		submitFile.setBounds(106, 57, 88, 29);
		submitFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					manualFile = true;
					System.out.println("Path: " + filePath.getText());
//					String content = new String (Files.readAllBytes(Paths.get(filePath.getText())), StandardCharsets.UTF_8);
//					manualTraining(content);


				
			}
		});
		panel_15.add(submitFile);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBounds(6, 95, 192, 147);
		panel_15.add(panel_20);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.LIGHT_GRAY);
		panel_16.setBounds(6, 266, 204, 205);
		panel_14.add(panel_16);
		panel_16.setLayout(null);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBounds(6, 6, 192, 193);
		panel_16.add(panel_17);
		panel_17.setLayout(null);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBounds(6, 6, 180, 81);
		panel_17.add(panel_18);
		
		JLabel lblNewLabel_15 = new JLabel("Hidden Layers");
		panel_18.add(lblNewLabel_15);
		
		h_sigmoid = new JRadioButton("Sigmoid");
		hidden_group.add(h_sigmoid);
		panel_18.add(h_sigmoid);
		
		h_relu = new JRadioButton("ReLU");
		hidden_group.add(h_relu);
		panel_18.add(h_relu);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBounds(6, 106, 180, 81);
		panel_17.add(panel_19);
		panel_19.setLayout(null);
		
		JLabel lblNewLabel_16 = new JLabel("Output Layer");
		lblNewLabel_16.setBounds(45, 5, 89, 16);
		panel_19.add(lblNewLabel_16);
		
		o_sigmoid = new JRadioButton("Sigmoid");
		output_group.add(o_sigmoid);
		o_sigmoid.setBounds(14, 26, 83, 23);
		panel_19.add(o_sigmoid);
		
		o_relu = new JRadioButton("ReLU");
		output_group.add(o_relu);
		o_relu.setBounds(102, 26, 63, 23);
		panel_19.add(o_relu);
	}
}




/*
if (splittedData.isSelected()) {
						System.out.println("Output with split test data");
						System.out.println("======================");
						String content = "";
						try {
							content = new String (Files.readAllBytes(Paths.get(filePath.getText())), StandardCharsets.UTF_8);
						}
						catch(Exception E) {
							
						}

						String [] data = content.split("\\n");
						float [][] tData = new float[data.length][content.split("\\n")[0].split("[,:]").length];
						
						for (int i = data.length/2-1; i < data.length; i++) {
							String [] splitted = content.split("\\n")[i].split("[,:]");
							for (int b =0; b < splitted.length; b++) {
								tData[i][b] = Float.parseFloat(splitted[b]);
							}
						}
						
						gate.tDataSet = new TrainingData[data.length/2];
						for (int i = 0; i < data.length/2; i++) {
							gate.tDataSet[i] = new TrainingData(Arrays.copyOfRange(tData[i],0,tData[0].length-1),
									Arrays.copyOfRange( tData[i],tData[0].length-1, tData[0].length));
						}
						gate.printInfo();
						
					}
					*/
