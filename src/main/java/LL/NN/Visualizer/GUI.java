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

	static JRadioButton enableNOR;
	static JRadioButton enableAND;
	static JCheckBox displayLoss;
	static boolean manualTraining;
	static JTextArea u_input;
	TrainingData parsedUserInput;
	static boolean networkTrained = false;
	
	
	public void createLossGraph(float[][]total_loss, int ITERATIONS) {
		
		//new Thread(() -> createLossGraph(float[][]total_loss, int ITERATIONS)).start();
		
		JFrame window = new JFrame("Loss output");
		//window.setVisible(true);
		window.setSize(600,400);
		window.getContentPane().setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		
		XYSeries series = new XYSeries("Loss");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("Loss Squared Regression", "Iterations", "Value", dataset);
		window.getContentPane().add(new ChartPanel(chart), BorderLayout.CENTER);
		window.setVisible(true);
		
//		try {
//			System.out.println("start");
//			Thread.sleep(5000);
//			System.out.println("end");
//
//			
//		}
//		catch (Exception E) {
//			
//		}
		for (int i =0; i < ITERATIONS; i++) {
			series.add(i,total_loss[i][0]);
			
			//window.revalidate();
			window.repaint();
			System.out.println("loss: " + total_loss[i][0]);
			}

		
	

	}
		
		
	
	public void manualTraining() {
		String [] data = m_trainingData.getText().split("\\n");
		float [][] tData = new float[data.length][m_trainingData.getText().split("\\n")[0].split("[,:]").length];
		
		for (int i = 0; i < data.length; i++) {
			String [] splitted = m_trainingData.getText().split("\\n")[i].split("[,:]");
			for (int b =0; b < splitted.length; b++) {
				tData[i][b] = Float.parseFloat(splitted[b]);
			}
			
		
		}
		
		gate.tDataSet = new TrainingData[data.length];
		for (int i = 0; i < data.length; i++) {
			gate.tDataSet[i] = new TrainingData(Arrays.copyOfRange(tData[i],0,tData[0].length-1),
					Arrays.copyOfRange( tData[i],tData[0].length-1, tData[0].length));
		}
		
		for (int i =0; i < gate.tDataSet.length; i++) {
			System.out.println(Arrays.toString(gate.tDataSet[i].data));
			System.out.println(Arrays.toString(gate.tDataSet[i].expectedOutput));
			System.out.println("======================");
		}
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
		frmNeuralNetworkVisualizer.setBounds(100, 100, 752, 517);
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
					gate = new NeuralNetwork(input, hlayers, hneurons, output);
					//System.out.println("laeyrs 2: " + gate.layers.length);
					//runNN();
					if (manualTraining == true) {
						manualTraining();
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
					networkTrained = true;
					
					
//					System.out.println("input: " + input);
//					System.out.println("hlayers: " + hlayers);
//					System.out.println("hneurons: " + hneurons);
//					System.out.println("output: " + output);
					//System.out.println("======================");
					
					//System.out.println("======================");
					System.out.println("Output before training");
					System.out.println("======================");
					
					for (int i = 0; i < gate.tDataSet.length;i++) {
						gate.forward(gate.tDataSet[i].data);
						System.out.println(gate.layers[gate.layers.length-1].neurons[0].value);
					}
					System.out.println("======================");
					
					//float[][] total_loss = new float[1000000][1];
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
					
					
					float[][] total_loss = gate.train(ITERATIONS, 0.05f);
					//System.out.println("first loss: " + total_loss[0][0]);
					
					
					//System.out.println("======================");
					System.out.println("Output after training");
					System.out.println("======================");
					
					for (int i = 0; i < gate.tDataSet.length;i++) {
						for (int j = 0; j < gate.tDataSet[i].data.length; j++) {
							System.out.print(gate.tDataSet[i].data[j] + " ");
						}
						System.out.print("\t");
						gate.forward(gate.tDataSet[i].data);
						System.out.println(gate.layers[gate.layers.length-1].neurons[0].value);
						//System.out.println("")
					}
					System.out.println("======================");

					if (displayLoss.isSelected() == true) {
						Initialize.createLoss(ITERATIONS, total_loss);
						//GUI temp = new GUI();
						//temp.createLossGraph(total_loss,ITERATIONS);

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
							System.out.print(gate.layers[gate.layers.length-1].neurons[i].value);
						}
						System.out.println();
						System.out.println("======================");

						
					}
					catch (Exception E) {
						
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
	}
}
