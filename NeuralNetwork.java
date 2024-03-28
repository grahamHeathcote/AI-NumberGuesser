package AINumberGuesser;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private List<NeuralLayer> layers;

    public NeuralNetwork(int inputCount, int[] hiddenLayerCounts, int outputCount) {
        layers = new ArrayList<>();
        
        // Create first hidden layer
        layers.add(new NeuralLayer(hiddenLayerCounts[0], inputCount));

        for (int i = 1; i < hiddenLayerCounts.length; i++) {
            layers.add(new NeuralLayer(hiddenLayerCounts[i], hiddenLayerCounts[i - 1]));
        }
        layers.add(new NeuralLayer(outputCount, hiddenLayerCounts[hiddenLayerCounts.length - 1]));
    }

    public double[] forwardPropagate(double[] inputs) {
        double[] outputs = inputs;
        for (NeuralLayer layer : layers) {
            outputs = layer.calculateOutputs(outputs);
        }
        return outputs;
    }



    public void train(double[][] inputs, double[][] expectedOutputs, int epochs, double learningRate) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < inputs.length; i++) {
                forwardPropagate(inputs[i]);
                backPropagate(expectedOutputs[i]);
                updateWeights(inputs[i], learningRate);
            }
        }
    }

public void backPropagate(double[] expectedOutputs) {
        int numberOfLayers = layers.size();
        for (int layerIdx = numberOfLayers - 1; layerIdx >= 0; layerIdx--) {
            List<Neuron> layer = layers.get(layerIdx).getNeurons();
            for (int neuronIdx = 0; neuronIdx < layer.size(); neuronIdx++) {
                Neuron neuron = layer.get(neuronIdx);
                if (layerIdx == numberOfLayers - 1) {
                    // Output layer
                    neuron.calculateDelta(expectedOutputs[neuronIdx]);
                } else {
                    // Hidden layers
                    List<Neuron> nextLayer = layers.get(layerIdx + 1).getNeurons();
                    double sum = 0;
                    for (Neuron nextLayerNeuron : nextLayer) {
                        sum += nextLayerNeuron.getWeights()[neuronIdx] * nextLayerNeuron.getDelta();
                    }
                    neuron.calculateDelta(sum);
                }
            }
        }
    }

    public void updateWeights(double[] inputs, double learningRate) {
        double[] currentInputs = inputs;
        for (NeuralLayer layer : layers) {
            double[] nextInputs = new double[layer.getNeurons().size()];
            for (int i = 0; i < layer.getNeurons().size(); i++) {
                Neuron neuron = layer.getNeurons().get(i);
                neuron.updateWeights(currentInputs, learningRate);
                nextInputs[i] = neuron.getOutput();
            }
            currentInputs = nextInputs;
        }
    }
}
