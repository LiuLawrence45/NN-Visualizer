package LL.NN.Visualizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.axis.IntegerTickSelector;
import org.jfree.chart3d.axis.NumberAxis3D;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.plot.XYZPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.awt.Color;

import javafx.*;



public class Initialize{
	
	int input = 2;
	int hlayers = 1;
	int hneurons = 6;
	int output = 1;


	public static void createLoss(int ITERATIONS, float[][]total_loss) {
		JFrame window = new JFrame("Loss output");
		window.setSize(600,400);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
		XYSeries series = new XYSeries("Loss");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("Loss Squared Regression", "Iterations", "Value", dataset);
		window.add(new ChartPanel(chart), BorderLayout.CENTER);
		window.setVisible(true);
		
		for (int i =0; i < ITERATIONS; i++) {
			series.add(i,total_loss[i][0]);
			window.repaint();
			//window.revalidate();
			//System.out.println("loss: " + total_loss[i][0]);
			}
	
	}
	
	
	public static void createCompare() {
		StackPane sp = new StackPane();
	    XYZDataset dataset = new XYZSeriesCollection<>();
	    Chart3D chart = Chart3DFactory.createXYZLineChart("XYZ Line Chart Demo", 
	    "Orson Charts", dataset, "Day", "Index", "Station");
	 
	    Chart3DViewer viewer = new Chart3DViewer(chart);
	    sp.getChildren().add(viewer);
	    Scene scene = new Scene(sp, 768, 512);
	    Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Orson Charts: XYZLineChart3DFXDemo1.java");
        stage.show();
	    
		
	}
	
	
	

	public static void main(String []args) {


		
		
		
		NeuralNetwork gate = new NeuralNetwork();
		Neuron.setRangeWeight(-1,1);
		
		/*
		 * Three layered neural network with 2 input neurons, 6 hidden neurons, and 1 output
		 */
		gate.layers = new Layer[3];
		gate.layers[0] = null;
		gate.layers[1] = new Layer(2,6);
		gate.layers[2] = new Layer(6,1);
		
		gate.CreateXORTrainingData();
	
		System.out.println("======================");
		System.out.println("Output before training");
		System.out.println("======================");
		
		for (int i = 0; i < gate.tDataSet.length;i++) {
			gate.forward(gate.tDataSet[i].data);
			System.out.println(gate.layers[2].neurons[0].value);
		}
		
		//float[][] total_loss = new float[1000000][1];
		
		final int ITERATIONS = 1000000;
		
		float[][] total_loss = gate.train(ITERATIONS, 0.1f);
		//System.out.println("first loss: " + total_loss[0][0]);
		
		
		System.out.println("======================");
		System.out.println("Output after training");
		System.out.println("======================");
		
		for (int i = 0; i < gate.tDataSet.length;i++) {
			gate.forward(gate.tDataSet[i].data);
			System.out.println(gate.layers[2].neurons[0].value);
		}
		
		//Loss graph
		//Initialize.createCompare();
		Initialize.createLoss(ITERATIONS, total_loss);
	//	
		
		
		
		
		
	}

}
