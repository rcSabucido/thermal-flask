public class WaterFlask {
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

    // Main method to demonstrate the WaterFlask class
}