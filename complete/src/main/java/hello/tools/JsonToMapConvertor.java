package hello.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonToMapConvertor
{
    ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> getMap(String fileName)
    {
        try
        {
            File file = new File(fileName);
            return objectMapper.readValue(file, HashMap.class);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

}
