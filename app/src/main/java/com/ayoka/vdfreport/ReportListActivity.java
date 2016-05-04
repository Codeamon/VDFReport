package com.ayoka.vdfreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ayoka.Adapters.ReportAdapter;
import com.ayoka.Model.Reports;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReportListActivity extends AppCompatActivity {
    final List<Reports> reports=new ArrayList();
    private int currentCategory=0;
    private  int currentProjectId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        setReportList();

        final ListView r_listview = (ListView)this.findViewById(R.id.reportlist);

        ReportAdapter r_adaptor = new ReportAdapter(this, this.reports);
        r_listview.setAdapter(r_adaptor);
        r_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                startActivity(new Intent(getApplicationContext(),ReportActivity.class));
                //Toast.makeText(ReportListActivity.this.getBaseContext(), r_listview.getAdapter().getItem(position).toString(), 0).show();
            }
        });
//        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                Toast.makeText(ReportListActivity.this.getBaseContext(), listemiz.getAdapter().getItem(position).toString(),0).show();
//            }
//        });
    }

    private void setReportList()
    {
        /*
        reports.add(new Reports("Krediler", Constants.Companies.VDF.getValue(), "VDF kredileşme istatistikleri"));
        reports.add(new Reports("KHM", Constants.Companies.VDF.getValue(), "Kredi Hesaplama Makinesi Hesaplatma Reporu"));
        reports.add(new Reports("Poliçeler", Constants.Companies.Sigorta.getValue(), "VDF Sigorta Poliçeleşme Oranı"));
*/
        /*String json = new ReportList().ReportListJson;
        try  {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArrayList = jsonObj.optJSONArray("categoryList");
            for(int i=0; i < jsonArrayList.length(); i++){
                JSONObject jsonlistOnj = jsonArrayList.getJSONObject(i);

                int Id = Integer.parseInt(jsonlistOnj.optString("Id").toString());
                int ProjectId = Integer.parseInt(jsonlistOnj.optString("ProjectId").toString());
                String CategoryName = jsonlistOnj.optString("CategoryName").toString();
                int MainCategoryId = Integer.parseInt(jsonlistOnj.optString("MainCategoryId").toString());

                if((MainCategoryId==currentCategory & ProjectId==currentProjectId)||
                        (MainCategoryId==currentCategory & currentProjectId==0))
                    reports.add(new Reports(CategoryName, ProjectId, CategoryName));
            }

        }
        catch (JSONException ex){
            ex.printStackTrace();      }
   */ }

}
