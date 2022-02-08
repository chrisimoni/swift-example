package com.chrisreal.swiftexample.utils;

import com.chrisreal.swiftexample.exceptions.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class AppUtils {
    public static boolean isEmpty (Object object) {
        return object == null || object.equals("");
    }

    public static void validateRequiredFields(Object object, String[] requiredFields) {
        if (object != null && requiredFields != null && requiredFields.length != 0) {
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Object> objectFields = mapper.convertValue(object, HashMap.class);
            for(String field: requiredFields) {
                if(!objectFields.containsKey(field))
                    throw new BadRequestException("400", field + " is required");
                if(objectFields.containsKey(field) && isEmpty(objectFields.get(field)))
                    throw new BadRequestException("400", field + " cannot be empty or null");
            }
        }
    }
}
