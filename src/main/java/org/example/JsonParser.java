package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;


//Implementing JsonParser for question no. 3
public class JsonParser {

    public static Object parseJson(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Configuring ObjectMapper to use BigDecimal for floating-point numbers and BigInteger for integers
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);

        // Parsing JSON into JsonNode
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        // Converting JsonNode to appropriate Java type
        return convertNodeToObject(jsonNode);
    }

    private static Object convertNodeToObject(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            // Converting to Map for JSON objects
            return new ObjectMapper().convertValue(jsonNode, Map.class);
        } else if (jsonNode.isArray()) {
            // Converting to List for JSON arrays
            return new ObjectMapper().convertValue(jsonNode, List.class);
        } else if (jsonNode.isIntegralNumber()) {
            return jsonNode.isBigInteger() ? jsonNode.bigIntegerValue() : jsonNode.longValue();
        } else if (jsonNode.isFloatingPointNumber()) {
            //return jsonNode.decimalValue();
            return new BigDecimal(jsonNode.asText());   //using BigDecimal for arbitrary precision
        } else if (jsonNode.isBoolean()) {
            return jsonNode.booleanValue();
        } else if (jsonNode.isTextual()) {
            return jsonNode.textValue();
        } else if (jsonNode.isNull()) {
            return null;
        } else {
            throw new IllegalArgumentException("Unsupported JSON node type: " + jsonNode.getNodeType());
        }
    }

    public static void main(String[] args) {
        // Test cases with different types of JSON inputs
        String jsonObject = "{\"number\": 12345678901234567890, \"array\": [1.234567890123456789, true, \"string\"]}";
        String jsonArray = "[12345678901234567890, 1.234567890123456789, true, \"string\"]";
        String jsonNumber = "12345678901234567890";
        String jsonDecimal = "1.234567890123456789";
        String jsonString = "\"Hello, world!\"";
        String jsonBoolean = "true";
        String jsonNull = "null";

        try {
            System.out.println(parseJson(jsonObject)); // Parses JSON object
            System.out.println(parseJson(jsonArray));  // Parses JSON array
            System.out.println(parseJson(jsonNumber)); // Parses JSON integer with BigInteger
            System.out.println(parseJson(jsonDecimal)); // Parses JSON decimal with BigDecimal
            System.out.println(parseJson(jsonString)); // Parses JSON string
            System.out.println(parseJson(jsonBoolean)); // Parses JSON boolean
            System.out.println(parseJson(jsonNull));   // Parses JSON null
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

