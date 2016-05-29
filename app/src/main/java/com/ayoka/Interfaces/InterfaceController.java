package com.ayoka.Interfaces;

import com.ayoka.Model.CategoryReportModel;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.Model.LoginInfoModel;
import com.ayoka.Model.LoginInfoResponse;
import com.ayoka.Model.LoginUserRequest;
import com.ayoka.Model.ReportDetail;
import com.ayoka.Model.ReportRequest;
import com.ayoka.Model.ReportResponse;
import com.ayoka.Model.ResponseMessage;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;



/**
 * Created by Osman Korcan
 */
public interface InterfaceController {


    // GET yada POST mu olduğunu belirliyoruz.@Path("projectId")
    @GET("/LoginReport")
    public void LoginReport(@Query("username") String projectId, @Query("password") String password, Callback<LoginInfoModel> callback);

    @POST("/LoginUser")
    public void LoginUser(@Body LoginUserRequest arguments, Callback<ResponseMessage<LoginInfoResponse>> calback);

    @GET("/GetCategoryReports")
    public void GetCategoryReports(@Query("departmentId") String departmentId,Callback<ResponseMessage<CategoryReportModel[]>> callback);

    @GET("/GetSubCategoryReports")
    public void GetSubCategoryReports(@Query("mainCategoryId") String categoryId,Callback<ResponseMessage<CategoryReportModel[]>> callback);
    @POST("/getreport")
    public void getreport(@Body ReportRequest reportRequest, Callback<ResponseMessage<ReportResponse>> callback);


    @GET("/GetUserDepartments")
    public void GetDepartments(@Query("UserId") String userId, Callback<ResponseMessage<DepartmanModel[]>> callback);

}
