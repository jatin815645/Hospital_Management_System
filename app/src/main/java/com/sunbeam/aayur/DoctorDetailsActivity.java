package com.sunbeam.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Jitendra Patil","Hospital Address : Pimpri","Exp : 5yrs","Mobile No : 7999842948","1600"},
                    {"Doctor Name : Bhupendra Patil","Hospital Address : Nigdi","Exp : 4yrs","Mobile No : 7988842948","1500"},
                    {"Doctor Name : Rakesh Merugu","Hospital Address : Kalyan","Exp : 7yrs","Mobile No : 7849842948","1800"},
                    {"Doctor Name : Kanak Choudhary","Hospital Address : Nagpur","Exp : 5yrs","Mobile No : 6599842948","1600"},
                    {"Doctor Name : Harsh Jaiswal","Hospital Address : Delhi","Exp : 4yrs","Mobile No : 8899842948","1700"}
            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Jitendra Patil","Hospital Address : Pimpri","Exp : 5yrs","Mobile No : 7999842948","1600"},
                    {"Doctor Name : Bhupendra Patil","Hospital Address : Nigdi","Exp : 4yrs","Mobile No : 7988842948","1500"},
                    {"Doctor Name : Rakesh Merugu","Hospital Address : Kalyan","Exp : 7yrs","Mobile No : 7849842948","1800"},
                    {"Doctor Name : Kanak Choudhary","Hospital Address : Nagpur","Exp : 5yrs","Mobile No : 6599842948","1600"},
                    {"Doctor Name : Harsh Jaiswal","Hospital Address : Delhi","Exp : 4yrs","Mobile No : 8899842948","1700"}
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Jitendra Patil","Hospital Address : Pimpri","Exp : 5yrs","Mobile No : 7999842948","1600"},
                    {"Doctor Name : Bhupendra Patil","Hospital Address : Nigdi","Exp : 4yrs","Mobile No : 7988842948","1500"},
                    {"Doctor Name : Rakesh Merugu","Hospital Address : Kalyan","Exp : 7yrs","Mobile No : 7849842948","1800"},
                    {"Doctor Name : Kanak Choudhary","Hospital Address : Nagpur","Exp : 5yrs","Mobile No : 6599842948","1600"},
                    {"Doctor Name : Harsh Jaiswal","Hospital Address : Delhi","Exp : 4yrs","Mobile No : 8899842948","1700"}
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Jitendra Patil","Hospital Address : Pimpri","Exp : 5yrs","Mobile No : 7999842948","1600"},
                    {"Doctor Name : Bhupendra Patil","Hospital Address : Nigdi","Exp : 4yrs","Mobile No : 7988842948","1500"},
                    {"Doctor Name : Rakesh Merugu","Hospital Address : Kalyan","Exp : 7yrs","Mobile No : 7849842948","1800"},
                    {"Doctor Name : Kanak Choudhary","Hospital Address : Nagpur","Exp : 5yrs","Mobile No : 6599842948","1600"},
                    {"Doctor Name : Harsh Jaiswal","Hospital Address : Delhi","Exp : 4yrs","Mobile No : 8899842948","1700"}
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Jitendra Patil","Hospital Address : Pimpri","Exp : 5yrs","Mobile No : 7999842948","1600"},
                    {"Doctor Name : Bhupendra Patil","Hospital Address : Nigdi","Exp : 4yrs","Mobile No : 7988842948","1500"},
                    {"Doctor Name : Rakesh Merugu","Hospital Address : Kalyan","Exp : 7yrs","Mobile No : 7849842948","1800"},
                    {"Doctor Name : Kanak Choudhary","Hospital Address : Nagpur","Exp : 5yrs","Mobile No : 6599842948","1600"},
                    {"Doctor Name : Harsh Jaiswal","Hospital Address : Delhi","Exp : 4yrs","Mobile No : 8899842948","1700"}
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewBMDTitle);
        btn = findViewById((R.id.buttonBMDBack));

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        Log.d("ArrayHospital","list"+list);
        for (int i=0; i < doctor_details.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees: "+doctor_details[i][4]+"/-");
            Log.d("ArrayHospital","HashMap Item"+item);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );

        ListView lst = findViewById(R.id.listViewCMB);
        lst.setAdapter(sa);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });

    }


}