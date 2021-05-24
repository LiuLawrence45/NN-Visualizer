# NN-Visualizer
Neural Network Visualizer for Data and Algorithms Final. NN-Visualizer is an open source Java Neural Network framework.

<h2>Features</h2>

  - Presets to solve common gates (XOR, NOR, AND)
  - Customize Neural Network Structure through GUI
  - Provide manual training data
  - Display an animated graph for MSE over given epochs
  - Customize the learning rate as well as epochs
  - Change activation functions for the hidden layer as well as output layer (Sigmoid and ReLU)

<h2>Literature and Information</h2>


The neural network has 3 layers
  1) Input layer (2 neurons each corresponding to one variable inputted by the user)
  2) Hidden layer (6 neurons)
  3) Output layer (1 neuron)


Training the neural network has two big steps
<h3>Forward Propagation</h3>


Take previous layer neuron values and multiply by the weight (which is randomly initialized). In this network, no biases are utilized.


![Neural Network Example](https://victorzhou.com/27cf280166d7159c0465a58c68f99b39/network3.svg)

The neural network developed is similar to the one above. 

![Sigmoid Function](https://qph.fs.quoracdn.net/main-qimg-05edc1873d0103e36064862a45566dba)

The sum is then passed through a Sigmoid function which reduces the range of the values to (0,1).

<h3>Backwards Propagation</h3>

 The weights and biases are adjusted accordingly in the process of backpropagation. This is how the neural network learns the correct outputs. 
 

