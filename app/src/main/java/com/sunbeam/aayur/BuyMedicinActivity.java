package com.sunbeam.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicinActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Unirise-D3 10000IU Capsule","","","","58"},
            {"HealthVit Chromium Picolinate 200mcg Capsule","","","","58"},
            {"Feronia-XT Tablet","","","","58"},
            {"VitaCure Multivitamin Capsule","","","","58"},
            {"OmegaWell Fish Oil Softgel","","","","58"},
            {"HerbLife Ashwagandha Extract Capsule","","","","58"},
            {"CalciumMax Calcium Citrate Tablet","","","","58"},
            {"ProbioticLife Digestive Health Capsule","","","","58"},
            {"PainRelief Plus Acetaminophen Tablet","","","","58"}

    };

    private String[] medicine_details = {
            "Building & keeping the bones & teeth strong\n"+
            "Helps to reduce iron deficiency due to chronic blood loss or low intake of iron\n"+
            "Supports overall immune health",
            "Rich in omega-3 fatty acids for heart and brain health",
            "Promotes stress relief and relaxation\n"+
            "Enhances calcium absorption for bone health",
            "Maintains a healthy gut microbiome",
            "Effective pain relief for headaches and minor aches\n"+
            "Aids in improving sleep quality",
            "Boosts energy levels and vitality",
            "Assists in maintaining healthy skin, hair, and nails\n"+
            "Supports cardiovascular health",
            "Enhances cognitive function and memory\n"+
            "Reduces inflammation and joint pain",
            "Improves digestion and nutrient absorption\n"+
            "Provides antioxidant protection",
            "Helps regulate blood sugar levels\n"+
            "Alleviates symptoms of anxiety and depression",
            "Promotes healthy vision and eye function\n"+
            "Supports muscle recovery after exercise"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicin);

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(BuyMedicinActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(BuyMedicinActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0; i < packages.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost: "+packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(BuyMedicinActivity.this, BuyMedicinDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",medicine_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}