package swp391_gr7.hivsystem.service;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;

import java.util.Map;

public class JWTUtils {

    // This method extracts the doctorId from a JWT token.
    public int extractDoctorId(String token) {
        try {
            // Parse the JWT token
            JWSObject jwsObject = JWSObject.parse(token);

            // Extract the payload
            Payload payload = jwsObject.getPayload();

            // Convert the payload to a JSONObject
            Map<String, Object> jsonMap = payload.toJSONObject();

            // Extract the doctorId from the JSON object
            Object doctorID = jsonMap.get("doctorId"); // doctorId is the key in the JWT payload

            // Number is used to handle both Integer and Long types
            return ((Number) doctorID).intValue();

        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }

    // This method extracts the customerId from a JWT token.
    public int extractCustomerId(String token) {
        try {

            // Parse the JWT token
            JWSObject jwsObject = JWSObject.parse(token);

            // Extract the payload
            Payload payload = jwsObject.getPayload();

            // Convert the payload to a JSONObject
            Map<String, Object> jsonMap = payload.toJSONObject(); // payload.toJSONObject() returns a Map

            // Extract the customerId from the JSON object
            Object customerID = jsonMap.get("customerId"); // customerId is the key in the JWT payload

            // Number is used to handle both Integer and Long types
            return ((Number) customerID).intValue();

        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }

    // This method extracts the managerId from a JWT token.
    public int extractManagerId(String token) {
        try {

            // Parse the JWT token
            JWSObject jwsObject = JWSObject.parse(token);

            // Extract the payload
            Payload payload = jwsObject.getPayload();

            // Convert the payload to a JSONObject
            Map<String, Object> jsonMap = payload.toJSONObject(); // payload.toJSONObject() trả về một Map

            // Extract the managerId from the JSON object
            Object managerID = jsonMap.get("managerId"); // managerId is the key in the JWT payload

            // Number is used to handle both Integer and Long types
            return ((Number) managerID).intValue();

        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }

    // This method extracts the staffId from a JWT token.
    public int extractStaffId(String token) {
        try {

            // Parse the JWT token
            JWSObject jwsObject = JWSObject.parse(token);

            // Extract the payload
            Payload payload = jwsObject.getPayload();

            // Convert the payload to a JSONObject
            Map<String, Object> jsonMap = payload.toJSONObject(); // payload.toJSONObject() returns a Map

            // Extract the staffId from the JSON object
            Object staffID = jsonMap.get("staffId"); // staffId is the key in the JWT payload

            // Number is used to handle both Integer and Long types
            return ((Number) staffID).intValue();

        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }

    // This method extracts the adminId from a JWT token.
    public int extractAdminId(String token) {
        try {

            // Parse the JWT token
            JWSObject jwsObject = JWSObject.parse(token);

            // Extract the payload
            Payload payload = jwsObject.getPayload();

            // Convert the payload to a JSONObject
            Map<String, Object> jsonMap = payload.toJSONObject(); // payload.toJSONObject() returns a Map

            // Extract the adminId from the JSON object
            Object adminID = jsonMap.get("adminId"); // adminId is the key in the JWT payload

            // Number is used to handle both Integer and Long types
            return ((Number) adminID).intValue();

        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }
}