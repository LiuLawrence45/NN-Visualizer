package LL.NN.Visualizer;

public class Neuron {
	
	static float minWeightValue;
	static float maxWeightValue;
	
	float [] weights;
	//old weights to use during backpropagation
	float[] cache_weights;
	float gradient;
	float bias;
	float value = 0;

	/**
	 * Constructor for hidden neurons
	 * @param weights
	 * @param bias
	 */
	public Neuron(float [] weights, float bias) {
		this.bias = bias;
		this.weights = weights;
		this.cache_weights = weights;
		gradient = 0;
	}
	
	/**
	 * Constructor for input neurons
	 * @param value
	 */
	public Neuron(float value) {
		weights = null;
		bias = -1;
		cache_weights = null;
		gradient = -1;
		this.value = value;
		
	}
	
	/**
	 * Set min and max for weights for all instances
	 * @param min
	 * @param max
	 */
	public static void setRangeWeight(float min, float max) {
		minWeightValue = min;
		maxWeightValue = max;
	}
	
	/**
	 * Used at end of backpropogation to copy the calculated value in the cache weights to weights
	 */
	public void update_weight() {
		weights = cache_weights;
	}
	
	

}
