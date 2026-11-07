package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1 - استرجاع القرار والاسم
        String m = CleExtras.MATRICULE;
        boolean approuve = getIntent().getBooleanExtra(CleExtras.verdict(m), false);
        String nom = getIntent().getStringExtra(CleExtras.nomVoyageur(m));

        // 2 - تغيير لون الخلفية والرسالة حسب القرار
        TextView tvMessage = findViewById(R.id.tv_message_nj);
        findViewById(R.id.layout_result_nj).setBackgroundColor(
                approuve ? Color.parseColor("#2E7D32") : Color.parseColor("#C62828"));
        tvMessage.setText(approuve
                ? getString(R.string.msg_ok, nom)
                : getString(R.string.msg_ko, nom));

        // 3 - العودة إلى الشاشة الرئيسية مع إغلاق الأنشطة السابقة
        Button btnNouveau = findViewById(R.id.btn_nouveau_nj);
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
