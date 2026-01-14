import java.util.ArrayList;

public class Cereal {
    // Instance Variables
    private String name;
    private double cups;
    private int protein;
    private double fiber;
    private int vitamins;

    // Constructor
    public Cereal(String name, double cups, int protein, double fiber, int vitamins) {
        this.name = name;
        this.cups = cups;
        this.protein = protein;
        this.fiber = fiber;
        this.vitamins = vitamins;
    }

    // Accessor Methods (Getters)
    public String getName() {
        return name;
    }

    public double getCups() {
        return cups;
    }

    // Helper method to calculate "Nutrition Score"
    // Justification: We define "Nutritious" as the sum of Protein, Fiber, and Vitamins.
    public double getNutritionScore() {
        return protein + fiber + vitamins;
    }

    // Method to calculate Nutrition Density (Score per Cup)
    public double getNutritionDensity() {
        if (cups == 0) return 0; // Prevent divide by zero
        return getNutritionScore() / cups;
    }

    // toString Method
    public String toString() {
        return name + " (Density: " + String.format("%.2f", getNutritionDensity()) + ")";
    }

    // ---------------------------------------------------------
    // MAIN METHOD (Test Runner)
    // ---------------------------------------------------------
    public static void main(String[] args) {
        // 1. Create specific instances based on the source text 
        ArrayList<Cereal> cerealList = new ArrayList<Cereal>();
        
        // Data derived from the provided image
        cerealList.add(new Cereal("100% Bran", 0.33, 4, 10.0, 25));
        cerealList.add(new Cereal("All-Bran", 0.33, 4, 9.0, 25));
        cerealList.add(new Cereal("All-Bran with Extra Fiber", 0.5, 4, 14.0, 25));
        cerealList.add(new Cereal("Almond Delight", 0.75, 2, 1.0, 25));
        cerealList.add(new Cereal("Apple Cinnamon Cheerios", 0.75, 2, 1.5, 25));
        cerealList.add(new Cereal("Apple Jacks", 1.0, 2, 1.0, 25));

        // 2. Calculate Average Nutrition Density
        double totalDensity = 0;
        for (Cereal c : cerealList) {
            totalDensity += c.getNutritionDensity();
        }
        double averageDensity = totalDensity / cerealList.size();

        // 3. Calculate Standard Deviation
        double sumSquaredDiffs = 0;
        Cereal mostDenseCereal = cerealList.get(0);
        
        for (Cereal c : cerealList) {
            double diff = c.getNutritionDensity() - averageDensity;
            sumSquaredDiffs += (diff * diff);
            
            // Track the max
            if (c.getNutritionDensity() > mostDenseCereal.getNutritionDensity()) {
                mostDenseCereal = c;
            }
        }
        
        double variance = sumSquaredDiffs / cerealList.size();
        double stdDev = Math.sqrt(variance);

        // 4. Calculate Z-Score (Standard Deviations above the mean)
        double zScore = (mostDenseCereal.getNutritionDensity() - averageDensity) / stdDev;

        // Output Results
        System.out.println("Average Nutrition Density: " + averageDensity);
        System.out.println("Standard Deviation: " + stdDev);
        System.out.println("Most Densely Nutritious Cereal: " + mostDenseCereal.getName());
        System.out.println("Z-Score (Std Devs above mean): " + zScore);
    }
}
