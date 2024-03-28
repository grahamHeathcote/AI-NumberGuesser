package AINumberGuesser;
import java.util.List;

public class Neuron {
    private double[] weights;
    private double bias;
    private double output;
    private double delta;

    public Neuron(int inputCount) {
        weights = new double[inputCount];
        for (int i = 0; i < inputCount; i++) {
            weights[i] = Math.random() - 0.5; 
        }
        bias = Math.random() - 0.5;
    }

    public double calculateOutput(double[] inputs) {
        double sum = bias;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }
        output = sigmoid(sum);
        return output;
    }

    
    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public void calculateDelta(double targetOutput) {
        double error = targetOutput - output; // For output layer
        this.delta = error * sigmoidDerivative(output);
    }

    public void calculateDelta(List<Neuron> nextLayer, int index) {
        double sum = 0;
        for (Neuron neuron : nextLayer) {
            sum += neuron.weights[index] * neuron.delta;
        }
        this.delta = sum * sigmoidDerivative(output); // For hidden layers
    }

    // Update weights and bias for this neuron
    public void updateWeights(double[] inputs, double learningRate) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += learningRate * delta * inputs[i];
        }
        bias += learningRate * delta;
    }

    private double sigmoidDerivative(double output) {
        return output * (1 - output);
    }

    public double[] getWeights() {
        return this.weights;
    }

    public double getDelta() {
        return this.delta;
    }

    public double getOutput() {
        return this.output;
    } 
}
