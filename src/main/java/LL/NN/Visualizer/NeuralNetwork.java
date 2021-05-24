package LL.NN.Visualizer;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sun.glass.events.WindowEvent;

import javafx.*;
import java.awt.BorderLayout;
/**
 * Neural network trained for Logic Gate
 * @author lawrenceliu
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


 

public class NeuralNetwork {
	JFrame window;
	
	int ITERATIONS;
	float [][]total_loss;
	float learning_rate;
	
	Layer[] layers;
	
	TrainingData[] tDataSet;
	 
	 //float [][] total_loss;
	 
	 public int input, hlayers,hneurons,output, type_hidden,type_output;
	 
	 
	 /**
	  * Default constructor override
	  */
	 public NeuralNetwork() {
		 layers = new Layer[1+2];
		 layers[0] = null;
		 layers[1] = new Layer(2,6);
		 layers[2] = new Layer(6,1);
		 
		 Neuron.setRangeWeight(-1,1); 
	 }
	 
	 /**
	  * Constructor w/ specifications
	  * @param input
	  * @param hlayers
	  * @param hneurons
	  * @param output
	  */
	 public NeuralNetwork(int input, int hlayers, int hneurons, int output) {
			layers = new Layer[hlayers+2];
			layers[0] = null;
			layers[1] = new Layer(input, hneurons);
			for (int i = 2; i <=hlayers; i++) {
				layers[i] = new Layer(hneurons,hneurons);
			}
			layers[hlayers+1] = new Layer(hneurons,output);

		 Neuron.setRangeWeight(-1,1); 
	 }
	 
	 
	
	
	/**
	 * Manual training data for XOR Gate
	 */
	public  void CreateXORTrainingData() {
		float [] input1 = new float[] {0,0};
		float [] input2 = new float[] {0,1};
		float [] input3 = new float[] {1,0};
		float [] input4 = new float[] {1,1};
		
		float[]expectedOutput1 = new float[] {0};
		float[]expectedOutput2 = new float[] {1};
		float[]expectedOutput3 = new float[] {1};
		float[]expectedOutput4 = new float[] {0};
		
		tDataSet = new TrainingData[4];
		tDataSet[0] = new TrainingData(input1, expectedOutput1);
		tDataSet[1] = new TrainingData(input2, expectedOutput2);
		tDataSet[2] = new TrainingData(input3, expectedOutput3);
		tDataSet[3] = new TrainingData(input4, expectedOutput4);
		
	}
	
	/**
	 * Manual training data for XNOR Gate
	 */
	public  void CreateXNORTrainingData() {
		float [] input1 = new float[] {0,0};
		float [] input2 = new float[] {0,1};
		float [] input3 = new float[] {1,0};
		float [] input4 = new float[] {1,1};
		
		float[]expectedOutput1 = new float[] {1};
		float[]expectedOutput2 = new float[] {0};
		float[]expectedOutput3 = new float[] {0};
		float[]expectedOutput4 = new float[] {1};
		
		tDataSet = new TrainingData[4];
		tDataSet[0] = new TrainingData(input1, expectedOutput1);
		tDataSet[1] = new TrainingData(input2, expectedOutput2);
		tDataSet[2] = new TrainingData(input3, expectedOutput3);
		tDataSet[3] = new TrainingData(input4, expectedOutput4);
		
	}
	
	/**
	 * Manual training data for AND Gate
	 */
	public  void CreateANDTrainingData() {
		float [] input1 = new float[] {0,0};
		float [] input2 = new float[] {0,1};
		float [] input3 = new float[] {1,0};
		float [] input4 = new float[] {1,1};
		
		float[]expectedOutput1 = new float[] {0};
		float[]expectedOutput2 = new float[] {0};
		float[]expectedOutput3 = new float[] {0};
		float[]expectedOutput4 = new float[] {1};
		
		tDataSet = new TrainingData[4];
		tDataSet[0] = new TrainingData(input1, expectedOutput1);
		tDataSet[1] = new TrainingData(input2, expectedOutput2);
		tDataSet[2] = new TrainingData(input3, expectedOutput3);
		tDataSet[3] = new TrainingData(input4, expectedOutput4);
		
	}
	
	/**
	 * Manual training data for OR Gate
	 */
	public  void CreateORTrainingData() {
		float [] input1 = new float[] {0,0};
		float [] input2 = new float[] {0,1};
		float [] input3 = new float[] {1,0};
		float [] input4 = new float[] {1,1};
		
		float[]expectedOutput1 = new float[] {0};
		float[]expectedOutput2 = new float[] {1};
		float[]expectedOutput3 = new float[] {1};
		float[]expectedOutput4 = new float[] {1};
		
		tDataSet = new TrainingData[4];
		tDataSet[0] = new TrainingData(input1, expectedOutput1);
		tDataSet[1] = new TrainingData(input2, expectedOutput2);
		tDataSet[2] = new TrainingData(input3, expectedOutput3);
		tDataSet[3] = new TrainingData(input4, expectedOutput4);
		
	}
	

	/**
	 * Manual training data for NOR Gate
	 */
	public  void CreateNORTrainingData() {
		float [] input1 = new float[] {0,0};
		float [] input2 = new float[] {0,1};
		float [] input3 = new float[] {1,0};
		float [] input4 = new float[] {1,1};
		
		float[]expectedOutput1 = new float[] {1};
		float[]expectedOutput2 = new float[] {0};
		float[]expectedOutput3 = new float[] {0};
		float[]expectedOutput4 = new float[] {0};
		
		tDataSet = new TrainingData[4];
		tDataSet[0] = new TrainingData(input1, expectedOutput1);
		tDataSet[1] = new TrainingData(input2, expectedOutput2);
		tDataSet[2] = new TrainingData(input3, expectedOutput3);
		tDataSet[3] = new TrainingData(input4, expectedOutput4);
		
	}
	
//	public  void loadData() throws IOException{
//		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("Import data: ");
//		String []temp = input.readLine().split(" ");
//		float[]data = new float[2];
//		for (int i = 0; i <2;i++) {
//			data[i] = (float)Integer.parseInt(temp[i]);
//		}
//		forward(data);
//		//System.out.println(layers[2].neurons[0].value);
//		
//	}
	
	
	
	
	/**
	 * Forward propagation in the neural network
	 * Sum previous layer weights and biases and pass onto next layer neurons
	 * to weighted sum
	 * Sigmoid function to normalize all values
	 * @param inputs
	 * @param type_hidden specifying what activation function the hidden layer takes (0 for sigmoid, 1 for relu)
	 * @param type_output specifying what activation function the output layer takes (0 for sigmoid, 1 for relu)
	 */
	public  void forward(float[] inputs) {
		layers[0] = new Layer(inputs);
		for (int i = 1	;i<layers.length; i++) {
			for (int j = 0; j < layers[i].neurons.length; j++) {
				float sum = 0;
				for (int k = 0; k < layers[i-1].neurons.length; k++) {
					sum += layers[i-1].neurons[k].value * layers[i].neurons[j].weights[k];
				}
				sum+= layers[i].neurons[j].bias;
				
				//System.out.println ("Max: " + Math.max(sum, 0));
				if (i < layers.length-1) {
					if (type_hidden == 0) {
						layers[i].neurons[j].value = StatUtil.Sigmoid(sum);
					}
					else {
						layers[i].neurons[j].value = Math.max(sum, 0);
					}

				}
				else {
					if (type_output == 0) {
						layers[i].neurons[j].value = StatUtil.Sigmoid(sum);
					}
					else {
						layers[i].neurons[j].value =  Math.max(sum, 0);
					}

				}

			}
		}
	}

	
	
	
	//https://mattmazur.com/2015/03/17/a-step-by-step-backpropagation-example/
	/**
	 * Backwards propagation in the neural network for training
	 * @param learning_rate
	 * @param tData, training data
	 * @param type_hidden specifying what activation function the hidden layer takes (0 for sigmoid, 1 for relu)
	 * @param type_output specifying what activation function the output layer takes (0 for sigmoid, 1 for relu)
	 */
	public float[] backward(float learning_rate, TrainingData tData) {
		//0 is sigmoid activation
		//1 is relu activation
		
		
		int number_layers = layers.length;
		int out_index = number_layers - 1;
		
		
		float[] loss_data = new float[layers[out_index].neurons.length];
		int counter = 0;
		
		//Update output layers
		for (int i = 0; i < layers[out_index].neurons.length; i++) {
			float output = layers[out_index].neurons[i].value;
			float target = tData.expectedOutput[i];
			float derivative = output-target;
			
			//This is technically incorrect, only works because I have one output neuron
			//mean squared error
			loss_data[counter] = (float)Math.pow(derivative, 2);

			counter++;
			
			
			//DIFFERENTIATING between RELU and Sigmoid gradients
			float delta;
			if (type_output == 0) {
				delta = derivative*(output*(1-output));
			}
			else {
				if (output > 0) {
					delta = 1;
				}
				else {
					delta = 0;
				}

			}

			
			layers[out_index].neurons[i].gradient = delta;
			for (int j = 0; j < layers[out_index].neurons[i].weights.length; j++) {
				float previous_output = layers[out_index-1].neurons[j].value;
				float error = delta*previous_output;
				
				//update weights for the neurons in final layer
				layers[out_index].neurons[i].cache_weights[j] = layers[out_index].neurons[i].weights[j]- learning_rate*error;
			}
		}
		
		//Update hidden layers
		for (int i = out_index-1;i>0; i--) {
			for (int j = 0;j < layers[i].neurons.length; j++) {
				float output = layers[i].neurons[j].value;
				float gradient_sum = sumGradient(j, i+1);
				
				
				//DIFFERENTIATING between RELU and Sigmoid gradients
				float delta;
				if (type_hidden == 0) {
					delta = gradient_sum*(output*(1-output));
				}
				else {
					if (output > 0) {
						delta = 1*gradient_sum;
					}
					else {
						delta = 0;
					}

				}
				
				//System.out.println("output: " + output + "\tdelta: " + delta);
				
				
				layers[i].neurons[j].gradient = delta;
				
				//And all weights
				for (int k = 0; k < layers[i].neurons[j].weights.length; k++) {
					float previous_output = layers[i-1].neurons[k].value;
					float error = delta*previous_output;
					layers[i].neurons[j].cache_weights[k] = layers[i].neurons[j].weights[k] - learning_rate*error;
				}
			}
		}
		
		//Refresh all weights
		for (int i = 0; i < layers.length; i++) {
			for (int j = 0; j < layers[i].neurons.length; j++) {
				layers[i].neurons[j].update_weight();
			}
		}
		return loss_data;
		
	}
	
	
	public  float sumGradient(int n_index,int l_index) {
		float gradient_sum = 0;
		Layer current_layer = layers[l_index];
		
		for (int i = 0; i < current_layer.neurons.length; i++) {
			Neuron current_neuron = current_layer.neurons[i];
			gradient_sum += current_neuron.weights[n_index]*current_neuron.gradient;
		}
		return gradient_sum;
	}
	
	/**
	 * Train the data set according to the training data, training iterations, and learning rate
	 * @param training_iterations
	 * @param learning_rate
	 * @return
	 */
	public float[][] train(int training_iterations, float learning_rate) {
		float [][] total_loss = new float[training_iterations*tDataSet.length][1];
		int counter = 0;
		for (int i = 0; i < training_iterations; i++) {
			for (int j = 0; j < tDataSet.length; j++) {
				forward(tDataSet[j].data);
				total_loss[counter] = backward(learning_rate, tDataSet[j]);
				//System.out.println("Loss: " + total_loss[counter][0]);
				counter++;

			}
		}
		return total_loss;
	}
	
	/**
	 * Train the data set according to the training data, training iterations, and learning rate
	 * This creates a loss graph that is animated
	 * @param training_iterations
	 * @param learning_rate
	 * @return
	 */
	public void lossTrain(int training_iterations, float learning_rate1) {
		
		this.ITERATIONS = training_iterations;
		this.learning_rate = learning_rate1;
		
		Thread runner = new Thread() {
			public void run() {
				window = new JFrame("Loss output");
				window.setSize(600,400);
				window.setLayout(new BorderLayout());
				window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				

				

				
				XYSeries series = new XYSeries("Loss");
				XYSeriesCollection dataset = new XYSeriesCollection(series);
				JFreeChart chart = ChartFactory.createXYLineChart("Loss Squared Regression", "Iterations", "Value", dataset);
				window.add(new ChartPanel(chart), BorderLayout.CENTER);
				window.setVisible(true);
				
				window.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				       printInfo();
				       window.dispose();
				    }
				});
				
				
				for (int i = 0; i < ITERATIONS; i++) {
					float counter = 0;
					for (int j = 0; j < tDataSet.length; j++) {
						forward(tDataSet[j].data);
						counter = counter + backward(learning_rate, tDataSet[j])[0];

					}
					//MSE 
					counter /= tDataSet.length;
					series.add(i,counter);
					window.repaint();
				}

			}

		};
		runner.start();

				
				
		
	}


	//Old loss graph creation method; override with LossTrain for live-accuracy
	public void createLossGraph(int ITERATIONS1, float[][]total_loss1){
		this.ITERATIONS = ITERATIONS1;
		this.total_loss = total_loss1;
//		Thread worker = new Thread() {

//			public void run() {
//				
//			}
//		};
//		
//		worker.start();
		
			window = new JFrame("Loss output");
			window.setSize(600,400);
			window.setLayout(new BorderLayout());
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


			
			XYSeries series = new XYSeries("Loss");
			XYSeriesCollection dataset = new XYSeriesCollection(series);
			JFreeChart chart = ChartFactory.createXYLineChart("Loss Squared Regression", "Iterations", "Value", dataset);
			window.add(new ChartPanel(chart), BorderLayout.CENTER);
			window.setVisible(true);

			
			for (int i =0; i < ITERATIONS; i++) {
				for (int j = 0; j < total_loss[i].length; j++) {
					series.add(i,total_loss[i][j]);
					window.repaint();
					System.out.println(total_loss[i][j]);
				}


			}
		
	}
	
	
	public void printInfo() {
		float accuracy = 0;
		for (int i = 0; i < tDataSet.length;i++) {
			for (int j = 0; j < tDataSet[i].data.length; j++) {
				System.out.print(tDataSet[i].data[j] + " ");
			}
			System.out.print("\t");
			forward(tDataSet[i].data);
			
			
			//THIS IS ONLY FOR BINARY OUTPUT!!!!
			float answer = (float)Math.round(layers[layers.length-1].neurons[0].value);
			
			//this only works with one output neuron
			accuracy+= Math.abs(tDataSet[i].expectedOutput[0] - answer) ;
			
			System.out.println(answer);
			

			//System.out.println("")
		}
		System.out.println("======================");
		System.out.println("Accuracy: " +  (1 - (accuracy/tDataSet.length)));
	}
	

}

