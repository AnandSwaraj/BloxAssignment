package org.example.DataBaseMigration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Q.2 Impleented a class to check that the copied data is the same as the original data
public class DataValidator {

    public static void validateData(List<Map<String, String>> originalData, List<Map<String, String>> copiedData) {
        // Checking row count
        if (originalData.size() != copiedData.size()) {
            System.out.println("Row counts do not match!");
            return;
        } else {
            System.out.println("Row counts match.");
        }

        // Checking for checksums
        String originalChecksum = generateChecksum(originalData);
        String copiedChecksum = generateChecksum(copiedData);
        if (!originalChecksum.equals(copiedChecksum)) {
            System.out.println("Checksums do not match!");
            return;
        } else {
            System.out.println("Checksums match.");
        }

        // checking Row-by-row comparison
        for (int i = 0; i < originalData.size(); i++) {
            if (!originalData.get(i).equals(copiedData.get(i))) {
                System.out.println("Data mismatch at row " + i);
            }
        }

        System.out.println("Data validation completed.");
    }

    private static String generateChecksum(List<Map<String, String>> data) {
        StringBuilder sb = new StringBuilder();
        for (Map<String, String> row : data) {
            sb.append(row.toString());
        }
        return Integer.toHexString(sb.toString().hashCode());
    }

    public static void main(String[] args) {
        // Sample original data
        List<Map<String, String>> originalData = new ArrayList<>();
        Map<String, String> originalRow1 = new HashMap<>();
        originalRow1.put("id", "1");
        originalRow1.put("name", "John Doe");
        originalRow1.put("email", "john.doe@example.com");
        originalData.add(originalRow1);

        Map<String, String> originalRow2 = new HashMap<>();
        originalRow2.put("id", "2");
        originalRow2.put("name", "Jane Smith");
        originalRow2.put("email", "jane.smith@example.com");
        originalData.add(originalRow2);

        // Sample copied data
        List<Map<String, String>> copiedData = new ArrayList<>();
        Map<String, String> copiedRow1 = new HashMap<>();
        copiedRow1.put("id", "1");
        copiedRow1.put("name", "John Doe");
        copiedRow1.put("email", "john.doe@example.com");
        copiedData.add(copiedRow1);

        Map<String, String> copiedRow2 = new HashMap<>();
        copiedRow2.put("id", "2");
        copiedRow2.put("name", "Jane Smith");
        copiedRow2.put("email", "jane.smith@example.com");
        copiedData.add(copiedRow2);

        // Calling the validation method
        validateData(originalData, copiedData);

        // Changing a value in copiedData to test the mismatch case
        copiedRow2.put("email", "jane.smith@differentdomain.com");
        validateData(originalData, copiedData);
    }
}
