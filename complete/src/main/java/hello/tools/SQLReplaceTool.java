package hello.tools;

import java.io.*;

public class SQLReplaceTool
{
    public static final String fileName = "/Users/sunikumar/cp/tools/test.txt";

    String input = "true, PaymentFraud, 1000, ecomorg, ecomorg";

    // Output File will be fileName_formatted.sql

    //[bitsindri, open, 0, 20]

    public static void main(String args[])
    {
        SQLReplaceTool tool = new SQLReplaceTool();
        tool.parseIt();
    }

    public void parseIt()
    {
        String[] inputs = input.split(",");
        try
        {
            File file = new File(fileName);    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
            String line;
            int questionMarkCounter = 0;
            while ((line = br.readLine()) != null)
            {
                if (line.contains("?"))
                {
                    while (line.contains("?"))
                    {
                        String replacementString = "'" + inputs[questionMarkCounter].trim() + "'";
                        line = line.replaceFirst("\\?", replacementString);
                        questionMarkCounter = questionMarkCounter + 1;
                    }
                    sb.append(line);
                }
                else
                {
                    sb.append(line);
                }

                sb.append("\n");
            }

            fr.close();    //closes the stream and release the resources
            System.out.println(sb.toString());   //returns a string that textually represents the object
            writeToFile(sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeToFile(String content)
    {
        try
        {
            File f = new File(fileName);
            String sourceFilePath = f.getAbsolutePath();
            String modifiedFile = getFileNameWithoutExtension(sourceFilePath) + "_formatted.sql";
            FileWriter writer = new FileWriter(modifiedFile);
            writer.write(content);
            writer.close();
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
