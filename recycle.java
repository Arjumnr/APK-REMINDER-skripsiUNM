package com.apk.reminder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.reminder.Adapter.adapterMahasiswa;
import com.apk.reminder.DBuser.DBuser;
import com.apk.reminder.DBuser.FieldUser;
import com.apk.reminder.Model.jadwal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recycle extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterMahasiswa adapterMahasiswa;
    LinearLayoutManager linearLayoutManager;
    List<jadwal> list=new ArrayList<>();
    DBuser dBuser;
    String[] datalist;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        dBuser = new DBuser( recycle.this );
        datalist = dBuser.getData( FieldUser.NAMA_TABLE );
        id = datalist[0];

        recyclerView = findViewById(R.id.id_recycle_mhs);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getdata(getApplicationContext());
//        Collections.sort();

    }

    void getdata(Context context){
        adapterMahasiswa =new adapterMahasiswa(list,context);
        recyclerView.setAdapter(adapterMahasiswa);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://anr.my.id/APIR/tampil-card-mhs", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean status = jsonObject.getBoolean("error");
                    if (!status){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i<jsonArray.length(); i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            jadwal jadwal = new jadwal();
                            jadwal.setNamaDosen(jsonObject1.getString("dosen"));
                            jadwal.setTglPertemuan(jsonObject1.getString("tgl_pertemuan"));
                            jadwal.setWaktuPertemuan(jsonObject1.getString("waktu_pertemuan"));
                            jadwal.setStatus(jsonObject1.getString("status_jadwal"));
                            Log.e(jadwal.getStatus(),"gagal");
                            jadwal.setTglPenginputan(jsonObject1.getString("created_at"));

                            list.add(jadwal);
                            adapterMahasiswa = new adapterMahasiswa(list,context);
                            recyclerView.setAdapter(adapterMahasiswa);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap=new HashMap<>();
                hashMap.put("id", id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);
    }
}