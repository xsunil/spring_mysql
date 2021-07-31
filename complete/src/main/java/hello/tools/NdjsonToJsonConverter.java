package hello.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NdjsonToJsonConverter
{
    ObjectMapper objectMapper = new ObjectMapper();

    public static final String ndJsonFileName =  "/Users/sunikumar/cp/tools/user.json";

    public static Boolean IS_FORMATTED = true;

    public static void main(String args[])
    {
        NdjsonToJsonConverter converter = new NdjsonToJsonConverter();
        converter.convertToJsonFromNDJson();
    }

    public void convertToJsonFromNDJson()
    {
        try
        {
            File file = new File(ndJsonFileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            List<Map<String, Object>> dataList = new ArrayList<>();

            while ((line = br.readLine()) != null)
            {
                Map valueMap = (HashMap<String, Object>) objectMapper.readValue(line, HashMap.class);
                dataList.add(valueMap);
            }

            Map<String , Object> finalMap = new HashMap<>();
            finalMap.put("data", dataList);
            writeToFile(finalMap);

            fr.close();    //closes the stream and release the resources
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void writeToFile(Map<String , Object> finalMap)
    {
        try
        {
            File f = new File(ndJsonFileName);
            String sourceFilePath = f.getAbsolutePath();
            String modifiedFile = getFileNameWithoutExtension(sourceFilePath) + "_formatted.json";
            FileWriter writer = new FileWriter(modifiedFile);
            String content;

            if (IS_FORMATTED)
            {
                content = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalMap);
            }
            else
            {
                content = objectMapper.writeValueAsString(finalMap);
            }

            writer.write(content);
            writer.close();

            System.out.println("Content has been written in file " + modifiedFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String getFileNameWithoutExtension(String fileName)
    {
        if (fileName.indexOf(".") > 0)
        {
            return fileName.substring(0, fileName.lastIndexOf("."));
        }
        else
        {
            return fileName;
        }
    }
}
