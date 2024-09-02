import javax.swing.*;
import java.awt.*;


public class WaterFlaskGUI {
    private final JLabel flaskDisplayLabel;
    private final JTextField temperatureField;
    private final JTextField volumeField;
    private final JButton fillButton;
    private final JButton drinkButton;
    private final WaterFlask flask;

    public WaterFlaskGUI() {
        flask = new WaterFlask(25, 250); // Initialize with 25°C and 250 ml

        JFrame frame = new JFrame("Water Flask");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create a panel for inputs and buttons
        JPanel controlPanel = new JPanel(new GridLayout(2, 4, 5, 5)); // GridLayout with gaps
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Labels and text fields
        JLabel temperatureLabel = new JLabel("Temperature (°C):");
        temperatureField = new JTextField();
        JLabel volumeLabel = new JLabel("Volume (ml):");
        volumeField = new JTextField();

        // Buttons
        JButton heatButton = new JButton("Heat");
        JButton coolButton = new JButton("Cool");
        fillButton = new JButton("Fill");
        drinkButton = new JButton("Drink");

        // Set button sizes
        Dimension buttonSize = new Dimension(60, 30);
        heatButton.setPreferredSize(buttonSize);
        coolButton.setPreferredSize(buttonSize);
        fillButton.setPreferredSize(buttonSize);
        drinkButton.setPreferredSize(buttonSize);

        // Add components to the control panel
        controlPanel.add(temperatureLabel);
        controlPanel.add(temperatureField);
        controlPanel.add(volumeLabel);
        controlPanel.add(volumeField);
        controlPanel.add(heatButton);
        controlPanel.add(coolButton);
        controlPanel.add(fillButton);
        controlPanel.add(drinkButton);

        // Display label for the flask
        flaskDisplayLabel = new JLabel("", SwingConstants.CENTER);
        flaskDisplayLabel.setFont(new Font("Monospaced", Font.PLAIN, 16)); // Bigger font for ASCII art
        flaskDisplayLabel.setPreferredSize(new Dimension(600, 250));

        // Add action listeners
        heatButton.addActionListener(_ -> {
            double temp = Double.parseDouble(temperatureField.getText());
            flask.heat(temp);
            updateDisplay();
        });

        coolButton.addActionListener(_ -> {
            double temp = Double.parseDouble(temperatureField.getText());
            flask.cool(temp);
            updateDisplay();
        });

        fillButton.addActionListener(_ -> {
            double volume = Double.parseDouble(volumeField.getText());
            flask.fill(volume);
            updateDisplay();
        });

        drinkButton.addActionListener(_ -> {
            double volume = Double.parseDouble(volumeField.getText());
            flask.drink(volume);
            updateDisplay();
        });

        // Add components to the frame
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(flaskDisplayLabel, BorderLayout.CENTER);

        updateDisplay();
        frame.setVisible(true);
    }

    private void updateDisplay() {
        flaskDisplayLabel.setText("<html>" + flask.getFlaskDisplay() + "</html>");

        // Check thresholds and update buttons visibility
        // Show fill button if volume is less than 500 ml
        fillButton.setVisible(!(flask.getCurrentVolume() >= flask.getCapacity())); // Hide fill button if volume is 500 ml or more

        // Show drink button if volume is more than 0 ml
        drinkButton.setVisible(!(flask.getCurrentVolume() <= 0)); // Hide drink button if volume is 0 ml
    }

    public static void main(String[] args) {
        new WaterFlaskGUI();
    }

    private static class WaterFlask {
        private double temperature; // Current temperature in Celsius
        private final double capacity; // Maximum capacity in ml
        private double currentVolume; // Current volume in ml

        public WaterFlask(double initialTemperature, double initialVolume) {
            this.temperature = initialTemperature;
            this.capacity = 500; // Setting the maximum capacity to 500 ml
            if (initialVolume <= capacity) {
                this.currentVolume = initialVolume;
            } else {
                this.currentVolume = capacity; // Ensuring the initial volume does not exceed the capacity
            }
        }

        // Method to heat the flask
        public void heat(double degrees) {
            temperature += degrees;
        }

        // Method to cool the flask
        public void cool(double degrees) {
            temperature -= degrees;
        }

        // Method to fill the flask
        public void fill(double volume) {
            if (currentVolume + volume <= capacity) {
                currentVolume += volume;
            } else {
                currentVolume = capacity;
            }
        }

        // Method to drink from the flask
        public void drink(double volume) {
            if (currentVolume - volume >= 0) {
                currentVolume -= volume;
            } else {
                currentVolume = 0;
            }
        }

        // Method to get flask display based on temperature and volume
        public String getFlaskDisplay() {
            StringBuilder display = new StringBuilder();
            display.append("Current Temperature: ").append(temperature).append("°C<br>");
            display.append("Current Volume: ").append(currentVolume).append(" ml / ").append(capacity).append(" ml<br>");
            if (temperature < 0) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   :::&nbsp; |<br>");
                display.append("  |  ICE&nbsp;  |<br>");
                display.append("  | :::&nbsp; |<br>");
                display.append("   |______|<br>");
            } else if (temperature >= 0 && temperature <= 20) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ....   |<br>");
                display.append("  |  COOL   |<br>");
                display.append("  | ....   |<br>");
                display.append("   |______|<br>");
            } else if (temperature > 20 && temperature <= 25) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ....   |<br>");
                display.append("  |  ROOM   |<br>");
                display.append("  | TEMP    |<br>");
                display.append("   |______|<br>");
            } else if (temperature > 25 && temperature <= 40) {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ,,,,   |<br>");
                display.append("  |  WARM   |<br>");
                display.append("  | ,,,,    |<br>");
                display.append("   |______|<br>");
            } else {
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("      |||<br>");
                display.append("   |______|<br>");
                display.append("  |   ~~~~   |<br>");
                display.append("  |  HOT!   |<br>");
                display.append("  | ~~~~    |<br>");
                display.append("   |______|<br>");
            }
            return display.toString();
        }

        public double getCurrentVolume() {
            return currentVolume;
        }

        public double getCapacity() {
            return capacity;
        }
    }
}