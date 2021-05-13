package LL.NN.Visualizer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.Button;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
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
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JTree;

public class GUI {
	
	static NeuralNetwork gate = new NeuralNetwork();
	static int input = 2;
	static int hlayers = 1;
	static int hneurons = 6;
	static int output = 1;

	private JFrame frame;
	private JTextField u_outputNeurons;
	private JTextField u_inputNeurons;
	private JTextField u_hiddenLayers;
	private JTextField u_hiddenNeurons;
	private JTextField textField;
	private JTextField textField_1;
	
	
	public static void runNN() {
		Neuron.setRangeWeight(-1,1);
		gate.layers = new Layer[hlayers+2];
		gate.layers[0] = null;
		gate.layers[1] = new Layer(input,hneurons);
		gate.layers[2] = new Layer(hneurons,output);
		gate.CreateXORTrainingData();
		
		System.out.println("======================");
		System.out.println("Output before training");
		System.out.println("======================");
		
		for (int i = 0; i < gate.tDataSet.length;i++) {
			gate.forward(gate.tDataSet[i].data);
			System.out.println(gate.layers[2].neurons[0].value);
		}
		
		
		final int ITERATIONS = 1000000;
		
		float[][] total_loss = gate.train(ITERATIONS, 0.05f);
		
		
		System.out.println("======================");
		System.out.println("Output after training");
		System.out.println("======================");
		
		for (int i = 0; i < gate.tDataSet.length;i++) {
			gate.forward(gate.tDataSet[i].data);
			System.out.println(gate.layers[2].neurons[0].value);
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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color (238,238,238));
		frame.setBounds(100, 100, 752, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 433, 477);
		frame.getContentPane().add(panel);
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
					gate.layers[1] = new Layer(input,hneurons);
					
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("--------");
					
					
					
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
		 * Changing layers doesnt work
		 */
		JButton submitHLayers = new JButton("Submit");
		submitHLayers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					hlayers = Integer.parseInt(u_hiddenLayers.getText());
					gate.layers = new Layer[hlayers+2];
					gate.layers[1] = new Layer(input, hneurons);
					for (int i = 2; i < hlayers+1; i++) {
						gate.layers[i] = new Layer(hneurons,hneurons);
					}
					gate.layers[hlayers+1] = new Layer(hneurons,output);
					
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("--------");
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
					gate.layers[1] = new Layer(input, hneurons);
					for (int i = 2; i < hlayers+1; i++) {
						gate.layers[i] = new Layer(hneurons,hneurons);
					}
					gate.layers[hlayers+1] = new Layer(hneurons,output);
					
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("--------");
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
					gate.layers[hlayers+1] = new Layer(hneurons,output);
					
					System.out.println("input: " + input);
					System.out.println("hlayers: " + hlayers);
					System.out.println("hneurons: " + hneurons);
					System.out.println("output: " + output);
					System.out.println("--------");
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(6, 34, 409, 136);
		panel_7.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Manual Training Data ");
		lblNewLabel.setBounds(6, 6, 409, 16);
		panel_7.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(144, 170, 117, 29);
		panel_7.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		submitOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(451, 6, 295, 477);
		frame.getContentPane().add(panel_1);
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Enable");
		chckbxNewCheckBox.setBounds(16, 34, 128, 23);
		panel_11.add(chckbxNewCheckBox);
		
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
				runNN();
				System.out.println("input: " + input);
				System.out.println("hlayers: " + hlayers);
				System.out.println("hneurons: " + hneurons);
				System.out.println("output: " + output);
				System.out.println("--------");
				
				
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
		
		textField = new JTextField();
		textField.setBounds(6, 34, 88, 26);
		panel_13.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Iterations");
		lblNewLabel_14.setBounds(16, 72, 61, 16);
		panel_13.add(lblNewLabel_14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 100, 88, 26);
		panel_13.add(textField_1);
		textField_1.setColumns(10);
		
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
		
		JRadioButton enableAND = new JRadioButton("Enable");
		enableAND.setBounds(89, 34, 141, 23);
		panel_9.add(enableAND);
		
		JRadioButton enableNOR = new JRadioButton("Enable");
		enableNOR.setBounds(89, 60, 141, 23);
		panel_9.add(enableNOR);
		
		JRadioButton enableXOR = new JRadioButton("Enable");
		enableXOR.setBounds(89, 90, 141, 23);
		panel_9.add(enableXOR);
		
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
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(6, 34, 271, 29);
		panel_10.add(textArea_1);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setBounds(84, 75, 117, 29);
		panel_10.add(btnNewButton_1);
	}
}
