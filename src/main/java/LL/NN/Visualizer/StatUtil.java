package LL.NN.Visualizer;


public class StatUtil {
	
	/**
	 * Random float for weight/bias initialization
	 * @param min
	 * @param max
	 * @return
	 */
	public static float RandomFloat(float min, float max) {
		float a = (float) Math.random();
		float num = min+ (float)Math.random() * (max-min);
		if (a < 0.5) {
			return num;
		}
		else {
			return -num;
		}
	}
	
	/**
	 * Sigmoid function to turn range of (a,b) to (0,1)
	 * @param x
	 * @return
	 */
	public static float Sigmoid(float x) {
		return (float) (1/(1+Math.pow(Math.E, -x)));
	}
	
	/**
	 * Derivative of the Sigmoid function
	 * @param x
	 * @return
	 */
	public static float SigmoidDerivative(float x) {
		return Sigmoid(x)*(1-Sigmoid(x));
	}
	
	public static float squaredError(float output, float target) {
		return (float)(0.5*Math.pow(2, target-output));
		//return (float)(0.5*Math.pow(target-output, 2));
	}
	
	
	public static float sumSquaredError(float[]outputs, float[] targets) {
		float sum = 0;
		for (int i =0; i < outputs.length; i++) {
			sum += squaredError(outputs[i],targets[i]);
		}
		return sum;
	}
	
	

}

