package LL.NN.Visualizer;
import java.awt.BorderLayout;
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
	
	 Layer[] layers;
	
	 TrainingData[] tDataSet;
	 
	 //float [][] total_loss;
	 
	 int input, hlayers,hneurons,output;
	 
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
		 layers[1] = new Layer(input,hneurons);
		 layers[2] = new Layer(hneurons,output);
		 
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
	
	public  void loadData() throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Import data: ");
		String []temp = input.readLine().split(" ");
		float[]data = new float[2];
		for (int i = 0; i <2;i++) {
			data[i] = (float)Integer.parseInt(temp[i]);
		}
		forward(data);
		System.out.println(layers[2].neurons[0].value);
		
	}
	
	
	
	
	/**
	 * Forward propagation in the neural network
	 * Sum previous layer weights and biases and pass onto next layer neurons
	 * Sigmoid function to normalize all values
	 * @param inputs
	 */
	public  void forward(float[] inputs) {
		layers[0] = new Layer(inputs);
		
		for (int i = 1	;i<layers.length; i++) {
			for (int j = 0; j < layers[i].neurons.length; j++) {
				float sum = 0;
				for (int k = 0; k < layers[i-1].neurons.length; k++) {
					//sum+= layers[i].neurons[j].bias;
					sum += layers[i-1].neurons[k].value * layers[i].neurons[j].weights[k];
				}
				layers[i].neurons[j].value = StatUtil.Sigmoid(sum);
			}
		}
	}
	
	//https://mattmazur.com/2015/03/17/a-step-by-step-backpropagation-example/
	/**
	 * Backwards propagation in the neural network for training
	 * @param learning_rate
	 * @param tData
	 */
	public float[] backward(float learning_rate, TrainingData tData) {
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
			loss_data[counter] = (float)Math.pow(derivative, 2);
			//loss_data[counter] = derivative;
			//System.out.println("loss" + loss_data[counter]);
			counter++;
			
			float delta = derivative*(output*(1-output));
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
				float delta = (gradient_sum) * output*(1-output);
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
				//System.out.println(total_loss[counter][0]);
				counter++;

			}
		}
		return total_loss;
	}

	/*
	 * Print the output of the neural network based on the training inputs
	 */
	public void printOutput() {
		for (int i = 0; i < tDataSet.length;i++) {
			forward(tDataSet[i].data);
			System.out.println(layers[2].neurons[0].value);
		}
	}
	
	public void createLossGraph(int ITERATIONS, float[][]total_loss){
		JFrame window = new JFrame("Loss output");
		window.setSize(600,400);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
		XYSeries series = new XYSeries("Loss");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("Loss Squared Regression", "Iterations", "Value", dataset);
		window.add(new ChartPanel(chart), BorderLayout.CENTER);
		window.setVisible(true);
		
		for (int i =0; i < ITERATIONS/1000; i++) {
			series.add(i,total_loss[i][0]);
			//System.out.println(total_loss[i][0]);

			window.repaint();
			//window.validate();
//			try {
//				Thread.sleep(2000);
//			}
//			catch(Exception e) {
//				
//			}
			
			//System.out.println("loss: " + total_loss[i][0]);
			}
		window.repaint();
//		
//		double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
//                12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
//                49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
//                93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
//                77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
//              };
//
//
//HistogramDataset dataset = new HistogramDataset();
//dataset.addSeries("key", values, 20);
//
//JFreeChart histogram = ChartFactory.createHistogram("JFreeChart Histogram",
//                   "Data", "Frequency", dataset);
//
//window.add(new ChartPanel(histogram));
//window.setVisible(true);
	}
	
	
	
	
	
	
	

}

