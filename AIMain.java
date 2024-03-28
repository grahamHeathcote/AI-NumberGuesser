package AINumberGuesser;
import java.util.Arrays;

public class AIMain {
    public static void main(String[] args) {
        // Example: input layer size = 10, one hidden layer with 5 neurons, output layer size = 1
        int inputCount = 4;
        int[] hiddenLayerCounts = {3};
        int outputCount = 1;

        NeuralNetwork network = new NeuralNetwork(inputCount, hiddenLayerCounts, outputCount);
        
        double[][] trainingInputs = {
                                    {1, 0, 1, 1},
                                    {1, 0, 0, 1},
                                    {1, 0, 0, 0},

                                    {0, 0, 0, 0},
                                    {0, 0, 1, 1},
                                    {0, 1, 1, 1},

                                    {0, 1, 0, 1},
                                    {1, 1, 0, 1},
                                    {1, 1, 1, 0},

                                    {1, 0, 1, 1},

                                     };
        double[][] expectedOutputs = { 
                                    {1},
                                    {0},
                                    {0},

                                    {0},
                                    {0},
                                    {1},

                                    {0},
                                    {1},
                                    {1},

                                    {1},


                                    };

        int epochs = 5000000;
        double learningRate = .5;
        
        network.train(trainingInputs, expectedOutputs, epochs, learningRate);





        double[][] allInputs = {
            {1, 0, 0,1},
            {1, 1, 1,1},
            {1, 1, 1,0},
             };

        for(int i=0; i< allInputs.length; i++){
            double[] output = network.forwardPropagate(allInputs[i]);
            System.out.println("Predicted output for"+Arrays.toString(allInputs[i])+": ");
            for (double val : output) {
                System.out.println("Value: " + val);
            }
        }


    }
}
