package LL.NN.Visualizer;

public class Layer {
	public Neuron[] neurons;
	
	/**
	 * Constructor for hidden and output layer
	 * x input neurons, y hidden neurons - number of weights are different for each layer
	 * specify, inNeurons as well as number of Neurons in layer
	 * @param inNeurons
	 * @param numNeurons
	 */
	public Layer(int inNeurons, int numberNeurons) {
		neurons = new Neuron[numberNeurons];
		
		for (int i =0;i < numberNeurons; i++) {
			float[] weights = new float[inNeurons];
			
			for (int j = 0; j < inNeurons;j++) {
				weights[j] = StatUtil.RandomFloat(Neuron.minWeightValue, Neuron.maxWeightValue);
			}
			neurons[i] = new Neuron(weights,StatUtil.RandomFloat(0, 1));
		}
	}
	
	/**
	 * Constructor for input layer
	 * input layer of neurons takes value as constructor
	 * Only for NeuralNetwork, not convolutional neural network
	 * @param input
	 */
	public Layer(float input[]) {
		neurons = new Neuron[input.length];
		for (int i = 0; i < neurons.length; i++) {
			neurons[i] = new Neuron(input[i]);
		}
	}

}
