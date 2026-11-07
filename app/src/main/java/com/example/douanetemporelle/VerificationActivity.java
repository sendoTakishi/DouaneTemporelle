package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1 - استرجاع المعطيات باستعمال نفس المفاتيح
        String m = CleExtras.MATRICULE;
        Intent in = getIntent();
        nom = in.getStringExtra(CleExtras.nomVoyageur(m));
        int naissance = in.getIntExtra(CleExtras.anneeNaissance(m), 0);
        int destination = in.getIntExtra(CleExtras.anneeDestination(m), 0);
        String motif = in.getStringExtra(CleExtras.motif(m));

        // 2 - حساب العمر الزمني (الفرق بين سنة الوجهة وسنة الولادة)
        int ageTemporel = destination - naissance;

        // 3 - عرض الملخص على شكل جواز سفر
        ((TextView) findViewById(R.id.tv_nom_nj)).setText(getString(R.string.lbl_nom, nom));
        ((TextView) findViewById(R.id.tv_age_nj)).setText(getString(R.string.lbl_age, ageTemporel));
        ((TextView) findViewById(R.id.tv_motif_nj)).setText(getString(R.string.lbl_motif, motif));

        Button btnApprouver = findViewById(R.id.btn_approuver_nj);
        Button btnRefuser = findViewById(R.id.btn_refuser_nj);

        // 4 - تعطيل زر "الموافقة" إذا كان السبب "تعديل الماضي" والفرق عددا أوليا
        boolean modification = getString(R.string.motif_modification).equals(motif);
        if (modification && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // 5 - الانتقال إلى نشاط النتيجة مع القرار واسم المسافر
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true));
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false));
    }

    // 6 - دالة اختبار العدد الأولي
    private boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private void envoyerVerdict(boolean approuve) {
        String m = CleExtras.MATRICULE;
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(CleExtras.verdict(m), approuve);
        intent.putExtra(CleExtras.nomVoyageur(m), nom);
        startActivity(intent);
    }
}
