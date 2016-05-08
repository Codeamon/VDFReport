package com.ayoka.Interfaces;

import com.ayoka.Model.CategoryReportModel;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.Model.LoginInfoModel;
import com.ayoka.Model.ReportDetail;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;



/**
 * Created by Osman Korcan
 */
public interface InterfaceController {

    // GET yada POST mu olduğunu belirliyoruz.@Path("projectId")
    @GET("/GetDepartments")
    public void GetDepartments(@Query("projectId") String projectId, Callback<DepartmanModel[]> callback);

    // GET yada POST mu olduğunu belirliyoruz.@Path("projectId")
    @GET("/LoginReport")
    public void LoginReport(@Query("username") String projectId, @Query("password") String password, Callback<LoginInfoModel> callback);

    @GET("/GetCategoryReportList")
    public void GetCategoryReportList(@Query("departmentId") String departmentId,Callback<CategoryReportModel[]> callback);

    @GET("/GetSubCategoryReportList")
    public void GetSubCategoryReportList(@Query("categoryId") String categoryId,Callback<CategoryReportModel[]> callback);
    @GET("/getreport")
    public void getreport(@Query("reportDetailId") Integer reportId, @Query("filter") String filter, Callback<ReportDetail> callback);

}
