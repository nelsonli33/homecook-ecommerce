package com.homecook.tappay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ApiHelper
{
    public static ObjectMapper mapper = new ObjectMapper()
    {{
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }};

    /**
     * Json Serialization of a given object.
     *
     * @param obj The object to serialize into Json.
     * @return The serialized Json String representation of the given object.
     * @throws JsonProcessingException Signals that a Json Processing Exception has occurred.
     */
    public static String serialize(Object obj)
            throws JsonProcessingException
    {
        if (obj == null)
        {
            return null;
        }

        return mapper.writeValueAsString(obj);
    }


    /**
     * Json deserialization of the given Json string.
     *
     * @param <T>   The type of the object to deserialize into
     * @param json  The Json string to deserialize
     * @param clazz The type of the object to deserialize into
     * @return The deserialized object
     * @throws IOException Signals if any I/O exception occured.
     */
    public static <T extends Object> T deserialize(String json, Class<T> clazz)
            throws IOException
    {
        if (isNullOrWhiteSpace(json))
        {
            return null;
        }

        return mapper.readValue(json, clazz);
    }

    /**
     * Validates if the string is null, empty or whitespace.
     *
     * @param s The string to validate.
     * @return The result of validation.
     */
    public static boolean isNullOrWhiteSpace(String s)
    {
        if (s == null)
        {
            return true;
        }

        int length = s.length();
        if (length > 0)
        {
            for (int start = 0, middle = length / 2, end = length - 1; start <= middle;
                 start++, end--)
            {
                if (s.charAt(start) > ' ' || s.charAt(end) > ' ')
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
