package com.ayoka.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ayoka.Model.Category;
import com.ayoka.Model.Reports;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by OsmanKorcan on 1.5.2016.
 */
public class JsonOperations {
    private String categoryJson = "{\n" +
            "\t\"categoryList\":[\n" +
            "\t{\n" +
            "\t\"Id\" : \"1\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"0\",\n" +
            "\t\"CategoryName\" :  \"volkswagen doğuş finans\",\n" +
            "\t\"Type\" :\"1\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"2\",\n" +
            "\t\"ProjectId\" : \"2\",\n" +
            "\t\"MainCategoryId\" : \"0\",\n" +
            "\t\"CategoryName\" :  \"vdf sigorta hizmetleri\",\n" +
            "\t\"Type\" :\"1\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"3\",\n" +
            "\t\"ProjectId\" : \"3\",\n" +
            "\t\"MainCategoryId\" : \"0\",\n" +
            "\t\"CategoryName\" :  \"vdf faktoring\",\n" +
            "\t\"Type\" :\"1\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"4\",\n" +
            "\t\"ProjectId\" : \"2\",\n" +
            "\t\"MainCategoryId\" : \"2\",\n" +
            "\t\"CategoryName\" :  \"Bayi Raporları\",\n" +
            "\t\"Type\" :\"1\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"5\",\n" +
            "\t\"ProjectId\" : \"2\",\n" +
            "\t\"MainCategoryId\" : \"4\",\n" +
            "\t\"CategoryName\" :  \"Komisyon Raporları\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"6\",\n" +
            "\t\"ProjectId\" : \"2\",\n" +
            "\t\"MainCategoryId\" : \"4\",\n" +
            "\t\"CategoryName\" :  \"Prim Raporları\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"7\",\n" +
            "\t\"ProjectId\" : \"2\",\n" +
            "\t\"MainCategoryId\" : \"2\",\n" +
            "\t\"CategoryName\" :  \"Poliçe Satış\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"8\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"1\",\n" +
            "\t\"CategoryName\" :  \"Krediler\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t}\n" +
            "]}";

    public ArrayList<String> GetMainList()
    {
        ArrayList<String> mainReportsList = new ArrayList<String>();
        try  {
            JSONObject jsonObj = new JSONObject(categoryJson);
            JSONArray jsonArrayList = jsonObj.optJSONArray("categoryList");
            for(int i=0; i < jsonArrayList.length(); i++){
                JSONObject jsonlistOnj = jsonArrayList.getJSONObject(i);

                int Id = Integer.parseInt(jsonlistOnj.optString("Id").toString());
                int ProjectId = Integer.parseInt(jsonlistOnj.optString("ProjectId").toString());
                String CategoryName = jsonlistOnj.optString("CategoryName").toString();
                int MainCategoryId = Integer.parseInt(jsonlistOnj.optString("MainCategoryId").toString());

                if( MainCategoryId==0 )
                    mainReportsList.add(CategoryName);
            }

        }
        catch (JSONException ex){
            ex.printStackTrace();      }
        return mainReportsList;

    }

    public ArrayList<Category> GetListByCategory(int categoryId,int projectId) {

        ArrayList<Category> categoryList = new ArrayList<Category>();
        try  {

            JSONObject jsonObj = new JSONObject(categoryJson);
            JSONArray jsonArray = jsonObj.optJSONArray("categoryList");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonlistOnj = jsonArray.getJSONObject(i);
   //             if(Integer.parseInt(jsonlistOnj.optString("MainCategoryId"))==categoryId)
    //            {
                    int id = Integer.parseInt(jsonlistOnj.optString("Id").toString());
                    int ProjectId = Integer.parseInt(jsonlistOnj.optString("ProjectId").toString());
                    int MainCategoryId = Integer.parseInt(jsonlistOnj.optString("MainCategoryId").toString());
                    String CategoryName = jsonlistOnj.optString("CategoryName").toString();
                    int Type = Integer.parseInt(jsonlistOnj.optString("Type").toString());
                    Category category = new Category(CategoryName,CategoryName,id,MainCategoryId,Type);
                    if((MainCategoryId==categoryId & ProjectId==projectId)||
                            (MainCategoryId==categoryId & projectId==0))
                    categoryList.add(category);
       //         }
            }
        }
        catch (JSONException ex){
            ex.printStackTrace();      }

        return categoryList;
    }

    private String reportJson(){
        return "{\n" + this.reportDetailJsonKrediler+ "}";
    }

    private String reportDetailJsonKrediler =
            "\t\"report\":[\n" +
            "\t{\n" +
            "\t\"Id\" : \"0\",\n" +
            "\t\"Value\" : \"102\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"1\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"1\",\n" +
            "\t\"Value\" : \"802\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"2\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"2\",\n" +
            "\t\"Value\" : \"604\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"3\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"3\",\n" +
            "\t\"Value\" : \"412\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"4\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"4\",\n" +
            "\t\"Value\" : \"455\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"5\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"5\",\n" +
            "\t\"Value\" : \"0\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"6\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"6\",\n" +
            "\t\"Value\" : \"12\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"7\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"7\",\n" +
            "\t\"Value\" : \"106\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"8\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"8\",\n" +
            "\t\"Value\" : \"405\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"9\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"9\",\n" +
            "\t\"Value\" : \"702\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"10\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"10\",\n" +
            "\t\"Value\" : \"202\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"11\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"11\",\n" +
            "\t\"Value\" : \"492\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"12\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"12\",\n" +
            "\t\"Value\" : \"212\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"13\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"13\",\n" +
            "\t\"Value\" : \"902\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"14\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"14\",\n" +
            "\t\"Value\" : \"160\",\n" +
            "\t\"TabId\" : \"1\",\n" +
            "\t\"TypeValue\" : \"15\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"15\",\n" +
            "\t\"Value\" : \"1212902\",\n" +
            "\t\"TabId\" : \"2\",\n" +
            "\t\"TypeValue\" : \"Ocak\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"16\",\n" +
            "\t\"Value\" : \"1212902\",\n" +
            "\t\"TabId\" : \"2\",\n" +
            "\t\"TypeValue\" : \"Şubat\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"17\",\n" +
            "\t\"Value\" : \"1902902\",\n" +
            "\t\"TabId\" : \"2\",\n" +
            "\t\"TypeValue\" : \"Mart\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"18\",\n" +
            "\t\"Value\" : \"119902\",\n" +
            "\t\"TabId\" : \"2\",\n" +
            "\t\"TypeValue\" : \"Nisan\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"8\"\n" +
            "\t}\n" +
            "\t]\n" ;

    public ArrayList<Reports> GetReportByCategoryId(int categoryId,int projectId,int tabId) {

        ArrayList<Reports> reportsList = new ArrayList<Reports>();
        try  {

            JSONObject jsonObj = new JSONObject(reportJson());
            JSONArray jsonArray = jsonObj.optJSONArray("report");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonlistOnj = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonlistOnj.optString("Id").toString());
                int ProjectId = Integer.parseInt(jsonlistOnj.optString("ProjectId").toString());
                int MainCategoryId = Integer.parseInt(jsonlistOnj.optString("MainCategoryId").toString());
                float Value = Integer.parseInt(jsonlistOnj.optString("Value").toString());
                int TabId = Integer.parseInt(jsonlistOnj.optString("TabId").toString());
                String typeValue = jsonlistOnj.optString("TypeValue").toString();

                Reports reports = new Reports(id+"",id,projectId,MainCategoryId,Value,TabId,typeValue);
                if((MainCategoryId==categoryId & ProjectId==projectId) & tabId == TabId)
                    reportsList.add(reports);
            }
        }
        catch (JSONException ex){
            ex.printStackTrace();      }

        return reportsList;
    }
}
