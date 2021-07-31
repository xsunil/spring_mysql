package hello.tools;

import java.sql.*;
public class MysqlInsert
{
    public static final String URL = "jdbc:mysql://127.0.0.1:10075/organization?useSSL=false";
    public static final String USER_NAME = "organization";
    public static final String PASSWORD = "organization";

    public static void main(String args[])
    {
        MysqlInsert mysqlInsert = new MysqlInsert();
        mysqlInsert.insertUniversity(1, 1000,10);
    }


    private void insertUniversity(int universityCount , int collegeCount, int buildingCount)
    {
        Connection conn = getConnection();

        try
        {
            for(int i =1 ; i <= universityCount; i++)
            {
                long universityPK = Long.valueOf(i);
                String universityName = "bitsindri" + i;
                String state = "jharkhand" + 1;

                String str = getUniversitySQl(universityPK, universityName, state);
                System.out.println(str);
                Statement statement = conn.createStatement();
                statement.execute(str);
                statement.close();

                statement = conn.createStatement();
                for(int j = 1; j<=collegeCount; j++)
                {
//                    String collegePKStr = String.valueOf(universityPK) + String.valueOf(j);
                    long collgePK = System.nanoTime();
                    String collegeName = "college" + collgePK;
                    str = getCollegeSQl(collgePK, universityPK, collegeName, "ORG_COLLEGE");
                    statement.execute(str);
                    str = getCollegeSQl(collgePK, universityPK, collegeName, "ORG_COLLEGE1");
                    statement.execute(str);

                    for(int k =1; k<= buildingCount; k++)
                    {
//                        String buildingPKStr = String.valueOf(collgePK) + String.valueOf(k);
                        long buildingPK = System.nanoTime();
                        String buildingName = "building" + buildingPK;
                        str = getBuildingSQl(buildingPK, collgePK, buildingName, "ORG_BUILDING");
                        statement.execute(str);
                        str = getBuilding1SQl(buildingPK, collgePK, buildingName, "ORG_BUILDING1");
                        statement.execute(str);
                    }
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private String  getUniversitySQl(long pk, String universityName, String state)
    {
        String sql ="INSERT INTO ORG_UNIVERSITY "
                + "("
                + "PK, UNIVERSITY_NAME,STATE, PROCESS, UPDATED_TIMESTAMP,"
                + "CREATED_TIMESTAMP,CREATED_BY,UPDATED_BY,CONTEXT_ID,"
                + "FOUNDING_YEAR,VERSION,"
                + "PROP1, PROP2,PROP3,PROP4,PROP5, "
                + "PROP6,PROP7,PROP8,PROP9,PROP10,"
                + "PROP11,PROP12,PROP13,PROP14,PROP15,"
                + "PROP16,PROP17,PROP18,PROP19,PROP20,"
                + "PROP21,PROP22,PROP23,PROP24,PROP25"
                + ") "

                + "VALUES ("+ pk + ",'"+universityName+"','"+ state+"', NULL, '2021-07-31 07:21:51.129000',"
                + "'2021-07-31 07:21:51.129000', 'systemadmin@system.com','systemadmin@system.com', NULL,"
                + "2010,1,"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value'"
                + ");";

        return sql;
    }

    private String  getCollegeSQl(long pk, long universityPK, String collegeName, String tableName)
    {
        String sql ="INSERT INTO "+ tableName +" "
                + "("
                + "PK, UNIVERSITY_PK, COLLEGE_NAME, PROCESS, UPDATED_TIMESTAMP,"
                + "CREATED_TIMESTAMP,CREATED_BY,UPDATED_BY,CONTEXT_ID,"
                + "VERSION,"
                + "PROP1, PROP2,PROP3,PROP4,PROP5, "
                + "PROP6,PROP7,PROP8,PROP9,PROP10,"
                + "PROP11,PROP12,PROP13,PROP14,PROP15,"
                + "PROP16,PROP17,PROP18,PROP19,PROP20,"
                + "PROP21,PROP22,PROP23,PROP24,PROP25"
                + ") "

                + "VALUES ("+ pk + ",'"+universityPK+"','"+ collegeName+"', NULL, '2021-07-31 07:21:51.129000',"
                + "'2021-07-31 07:21:51.129000', 'systemadmin@system.com','systemadmin@system.com', NULL,"
                + "1,"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value'"
                + ");";

        return sql;
    }


    private String  getBuildingSQl(long pk, long collegePK, String buildingName, String tableName)
    {
        String sql ="INSERT INTO "+ tableName +" "
                + "("
                + "PK, COLLEGE_PK, BUILDING_NAME, PROCESS, UPDATED_TIMESTAMP,"
                + "CREATED_TIMESTAMP,CREATED_BY,UPDATED_BY,CONTEXT_ID,"
                + "VERSION,"
                + "PROP1, PROP2,PROP3,PROP4,PROP5, "
                + "PROP6,PROP7,PROP8,PROP9,PROP10,"
                + "PROP11,PROP12,PROP13,PROP14,PROP15,"
                + "PROP16,PROP17,PROP18,PROP19,PROP20,"
                + "PROP21,PROP22,PROP23,PROP24,PROP25"
                + ") "

                + "VALUES ("+ pk + ",'"+collegePK+"','"+ buildingName+"', NULL, '2021-07-31 07:21:51.129000',"
                + "'2021-07-31 07:21:51.129000', 'systemadmin@system.com','systemadmin@system.com', NULL,"
                + "1,"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value'"
                + ");";

        return sql;
    }


    private String  getBuilding1SQl(long pk, long collegePK, String buildingName, String tableName)
    {
        String sql ="INSERT INTO "+ tableName +" "
                + "("
                + "PK, COLLEGE1_PK, BUILDING_NAME, PROCESS, UPDATED_TIMESTAMP,"
                + "CREATED_TIMESTAMP,CREATED_BY,UPDATED_BY,CONTEXT_ID,"
                + "VERSION,"
                + "PROP1, PROP2,PROP3,PROP4,PROP5, "
                + "PROP6,PROP7,PROP8,PROP9,PROP10,"
                + "PROP11,PROP12,PROP13,PROP14,PROP15,"
                + "PROP16,PROP17,PROP18,PROP19,PROP20,"
                + "PROP21,PROP22,PROP23,PROP24,PROP25"
                + ") "

                + "VALUES ("+ pk + ",'"+collegePK+"','"+ buildingName+"', NULL, '2021-07-31 07:21:51.129000',"
                + "'2021-07-31 07:21:51.129000', 'systemadmin@system.com','systemadmin@system.com', NULL,"
                + "1,"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value',"
                + "'value','value','value','value','value'"
                + ");";

        return sql;
    }


    private Connection getConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    private void closeConnection(Connection con)
    {
        try
        {
            if (con != null)
            {
                con.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
