package com.ayoka.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ayoka.Model.Category;
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
            "\t\"CategoryName\" :  \"Finans\",\n" +
            "\t\"Type\" :\"1\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"2\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"0\",\n" +
            "\t\"CategoryName\" :  \"Sigorta\",\n" +
            "\t\"Type\" :\"1\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"3\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"0\",\n" +
            "\t\"CategoryName\" :  \"Faktoring\",\n" +
            "\t\"Type\" :\"1\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"4\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"3\",\n" +
            "\t\"CategoryName\" :  \"Bayi Raporları\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"5\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"1\",\n" +
            "\t\"CategoryName\" :  \"Komisyon Raporları\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"6\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"1\",\n" +
            "\t\"CategoryName\" :  \"Prim Raporları\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Id\" : \"7\",\n" +
            "\t\"ProjectId\" : \"1\",\n" +
            "\t\"MainCategoryId\" : \"2\",\n" +
            "\t\"CategoryName\" :  \"Poliçe Satış\",\n" +
            "\t\"Type\" :\"2\"\n" +
            "\t}\n" +
            "]}";


    public ArrayList<Category> GetListByCategory(int categoryId) {
        ArrayList<Category> categoryList = new ArrayList<Category>();
        try  {


            JSONObject jsonObj = new JSONObject(categoryJson);
            JSONArray jsonArray = jsonObj.optJSONArray("categoryList");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonlistOnj = jsonArray.getJSONObject(i);
                if(Integer.parseInt(jsonlistOnj.optString("MainCategoryId"))==categoryId)
                {
                    int id = Integer.parseInt(jsonlistOnj.optString("Id").toString());
                    int ProjectId = Integer.parseInt(jsonlistOnj.optString("ProjectId").toString());
                    int MainCategoryId = Integer.parseInt(jsonlistOnj.optString("MainCategoryId").toString());
                    String CategoryName = jsonlistOnj.optString("CategoryName").toString();
                    int Type = Integer.parseInt(jsonlistOnj.optString("Type").toString());
                    Category category = new Category(CategoryName,CategoryName,id,MainCategoryId,Type);
                    categoryList.add(category);
                }
            }
        }
        catch (JSONException ex){
            ex.printStackTrace();      }

        return categoryList;
    }
}
