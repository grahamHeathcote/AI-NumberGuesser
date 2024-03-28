package AINumberGuesser;
import java.util.ArrayList;
import java.util.List;

public class NeuralLayer {
    private List<Neuron> neurons;
    private double[] outputs;

    public NeuralLayer(int neuronCount, int inputCount) {
        neurons = new ArrayList<>();
        for (int i = 0; i < neuronCount; i++) {
            neurons.add(new Neuron(inputCount));
        }
        outputs = new double[neuronCount];
    }

    public double[] calculateOutputs(double[] inputs) {
        for (int i = 0; i < neurons.size(); i++) {
            outputs[i] = neurons.get(i).calculateOutput(inputs);
        }
        return outputs;
    }

    public List<Neuron> getNeurons() {
        return this.neurons;
    }
}
