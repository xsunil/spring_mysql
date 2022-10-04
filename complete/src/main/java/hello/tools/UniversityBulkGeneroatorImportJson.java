package hello.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.comp.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversityBulkGeneroatorImportJson
{

    ObjectMapper objectMapper = new ObjectMapper();

    public static Boolean IS_FORMATTED = true;

    public static final String DESTINATION_FILE =  "/Users/sunikumar/cp/tools/university.json";

    public static final String SAMPLE_SOURCE_FILE =  "/Users/sunikumar/cp/tools/university_sample.json";

    public static void main(String args[])
    {
        UniversityBulkGeneroatorImportJson universityBulkGeneroator = new UniversityBulkGeneroatorImportJson();
//        universityBulkGeneroator.generateOnlyUniversities(1000);
        universityBulkGeneroator.generateComplexUniversity(2000, 10, 2, 0);
    }


    public void generateComplexUniversity(int startingNumber, int universityCount, int collegeCount, int studentCount)
    {
        JsonToMapConvertor jsonToMapConvertor = new JsonToMapConvertor();
        Map<String, Object> sampleMap = jsonToMapConvertor.getMap(SAMPLE_SOURCE_FILE);

        List<Map<String, Object>> list = new ArrayList<>();

        String universityName = (String)sampleMap.get("UniversityName");
        String state = (String)sampleMap.get("State");

        for (int i = startingNumber; i < universityCount + startingNumber; i++)
        {
            Map<String, Object> deepCopy = new HashMap<>(sampleMap);
            String runningUniversityName = universityName + "-" + i;

            deepCopy.put("UniversityName", runningUniversityName);
            deepCopy.put("State", state + "-" + i);
            list.add(deepCopy);

            List<Map> sampleCollegeMapList  = (List)sampleMap.get("College");
            Map sampleCollegeMap = null;

            if(sampleCollegeMapList != null && sampleCollegeMapList.size() > 0)
            {
                sampleCollegeMap = sampleCollegeMapList.get(0);
            }

            if(sampleCollegeMap != null)
            {
                List<Map> collegeList = new ArrayList<>();
                for(int j =0; j < collegeCount; j++)
                {
                    Map<String, Object> collegeDeepCopy = new HashMap<>(sampleCollegeMap);
                    String runingCollegeName = runningUniversityName + "College_" + j;
                    collegeDeepCopy.put("CollegeName", runingCollegeName);
                    collegeList.add(collegeDeepCopy);
                }

                if(!collegeList.isEmpty())
                {
                    deepCopy.put("College", collegeList);
                }
            }

            // need to add code student


        }


        Map bulk = new HashMap<>();
        bulk.put("data", list);
        writeToFile(bulk, DESTINATION_FILE);

    }


    public void generateOnlyUniversities(int count)
    {
        String universityName = "University_";
        String state = "State_";
        List<Map> universityDocList = new ArrayList<>();

        for(int i =0; i< count; i++)
        {

            Map document = new HashMap();
            document.put("UniversityName", universityName + i);
            document.put("State", state + i);
            universityDocList.add(document);

        }
        Map bulk = new HashMap<>();
        bulk.put("data", universityDocList);
        writeToFile(bulk, DESTINATION_FILE);
    }

    public void writeToFile(Map<String , Object> finalMap, String destinationFile)
    {
        try
        {
            File f = new File(destinationFile);
            FileWriter writer = new FileWriter(destinationFile);
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
            System.out.println("Content has been written in file " + f.getAbsolutePath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
