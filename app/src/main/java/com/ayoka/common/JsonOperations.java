package com.ayoka.common;

import android.util.EventLogTags;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Model.Category;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.Model.Reports;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;



/**
 * Created by OsmanKorcan on 1.5.2016.
 */


public class JsonOperations {
    public static final OkHttpClient clienteOkHttp = new OkHttpClient();
    public static final long timeOutSecond = 100;
    public RestAdapter restAdapter;
    public InterfaceController restInterface;
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
        final ArrayList<String> mainReportsList = new ArrayList<String>();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);

//        restInterface.GetDepartments("1",new Callback<DepartmanModel[]>() {
//            @Override
//            public void success(DepartmanModel[] departmanModels, Response response) {
//
//                for (DepartmanModel departmanModel : departmanModels) {
//                        mainReportsList.add(departmanModel.getDepartmentName());
//                }
//            }
//            @Override
//            public void failure(RetrofitError retrofitError) {
//                retrofitError.printStackTrace(); //to see if you have errors
//            }
//        });
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

    //Burası daha sonra webservisten alacak biçime çevrilecek.
    private String reportJson(int categoryId,int projectId){
        switch (projectId) {
            //VDF
            case 1:
                return reportJsonVDF(categoryId);
            //Sigorta
            case 2:
                return reportJsonSigorta(categoryId);
            //Faktoring
            case 3:
                return reportJsonVDF(categoryId);
            default:
                return "";
        }
    }

    private String reportJsonVDF(int categoryId) {
        if(categoryId== 8)
            return  reportDetailJsonKrediler;
        else return "";
    }

    private String reportJsonSigorta(int categoryId) {
        if(categoryId== 8)
            return  "";
        else return "";
    }

    private String reportJsonFaktoring(int categoryId) {
        if(categoryId== 8)
            return  "";
        else return "";
    }

    private String reportDetailJsonKrediler ="{\"report\" : \n" +
            "{\n" +
            "\"description\" :  [\n" +
            " {\n" +
            "\"TabId\" : \"2\", \n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\" ,\n" +
            "\"description\" :\"Krediler Günlük Detayları\"\n" +
            " },\n" +
            " {\n" +
            "\"TabId\" : \"2\", \n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\" ,\n" +
            "\"description\" :\"Krediler Aylık Detayları\"\n" +
            " }\n" +
            " ],\n" +
            "\"reportlist\":[ \n" +
            "{\n" +
            "\"Id\" : \"0\",\n" +
            "\"Value\" : \"102\",  \n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"1\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"1\",\n" +
            "\"Value\" : \"802\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"2\",\n" +
            "\"ProjectId\" : \"1\",  \n" +
            "\"MainCategoryId\" : \"8\"  \n" +
            "},  \n" +
            "{ \n" +
            "\"Id\" : \"2\",  \n" +
            "\"Value\" : \"604\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"3\",  \n" +
            "\"ProjectId\" : \"1\",  \n" +
            "\"MainCategoryId\" : \"8\" \n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"3\", \n" +
            "\"Value\" : \"412\",  \n" +
            "\"TabId\" : \"2\", \n" +
            "\"TypeValue\" : \"4\",  \n" +
            "\"ProjectId\" : \"1\",  \n" +
            "\"MainCategoryId\" : \"8\"  \n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"4\",  \n" +
            "\"Value\" : \"455\",  \n" +
            "\"TabId\" : \"2\",  \n" +
            "\"TypeValue\" : \"5\",\n" +
            "\"ProjectId\" : \"1\",  \n" +
            "\"MainCategoryId\" : \"8\"  \n" +
            "}, \n" +
            "{\n" +
            "\"Id\" : \"5\",  \n" +
            "\"Value\" : \"0\",\n" +
            "\"TabId\" : \"2\", \n" +
            "\"TypeValue\" : \"6\", \n" +
            "\"ProjectId\" : \"1\",  \n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"6\",  \n" +
            "\"Value\" : \"12\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"7\",  \n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"7\",\n" +
            "\"Value\" : \"106\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"8\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"8\",\n" +
            "\"Value\" : \"405\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"9\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"9\",\n" +
            "\"Value\" : \"702\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"10\",  \n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"  \n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"10\",\n" +
            "\"Value\" : \"202\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"11\",\n" +
            "\"ProjectId\" : \"1\",  \n" +
            "\"MainCategoryId\" : \"8\" \n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"11\",\n" +
            "\"Value\" : \"492\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"12\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"12\",\n" +
            "\"Value\" : \"212\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"13\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"13\",\n" +
            "\"Value\" : \"902\",\n" +
            "\"TabId\" : \"2\",\n" +
            "\"TypeValue\" : \"14\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"14\",\n" +
            "\"Value\" : \"160\",  \n" +
            "\"TabId\" : \"2\",  \n" +
            "\"TypeValue\" : \"15\", \n" +
            "\"ProjectId\" : \"1\", \n" +
            "\"MainCategoryId\" : \"8\" \n" +
            "}, \n" +
            "{ \n" +
            "\"Id\" : \"15\",\n" +
            "\"Value\" : \"1212902\",\n" +
            "\"TabId\" : \"1\",\n" +
            "\"TypeValue\" : \"Ocak\",\n" +
            "\"ProjectId\" : \"1\", \n" +
            "\"MainCategoryId\" : \"8\" \n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"16\",\n" +
            "\"Value\" : \"1212902\",\n" +
            "\"TabId\" : \"1\",\n" +
            "\"TypeValue\" : \"Şubat\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"17\",\n" +
            "\"Value\" : \"1902902\",\n" +
            "\"TabId\" : \"1\",\n" +
            "\"TypeValue\" : \"Mart\",\n" +
            "\"ProjectId\" : \"1\",\n" +
            "\"MainCategoryId\" : \"8\"\n" +
            "},\n" +
            "{\n" +
            "\"Id\" : \"18\",\n" +
            "\"Value\" : \"119902\",\n" +
            "\"TabId\" : \"1\",\n" +
            "\"TypeValue\" : \"Nisan\",  \n" +
            "\"ProjectId\" : \"1\",  \n" +
            "\"MainCategoryId\" : \"8\"  \n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}";

    public ArrayList<Reports> GetReportByCategoryId(int categoryId,int projectId,int tabId,String Description) {

        ArrayList<Reports> reportsList = new ArrayList<Reports>();
        try  {

            JSONObject jsonObj = new JSONObject(reportJson(categoryId,projectId));
            JSONObject jsonobjReport = jsonObj.getJSONObject("report");
            JSONArray jsonArray = jsonobjReport.getJSONArray("reportlist");
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

            JSONArray jsonArrayDes = jsonobjReport.getJSONArray("description");

            for(int i=0; i < jsonArrayDes.length(); i++){
                JSONObject jsonlistOnj = jsonArrayDes.getJSONObject(i);

                int MainCategoryId = Integer.parseInt(jsonlistOnj.optString("MainCategoryId").toString());
                int ProjectId = Integer.parseInt(jsonlistOnj.optString("ProjectId").toString());
                int TabId = Integer.parseInt(jsonlistOnj.optString("TabId").toString());
                if((MainCategoryId==categoryId & ProjectId==projectId) & tabId == TabId)
                    Description =  jsonlistOnj.optString("description").toString();
            }
        }
        catch (JSONException ex){
            ex.printStackTrace();      }

        return reportsList;
    }

}
