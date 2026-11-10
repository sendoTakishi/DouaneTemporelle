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

        // 1 - Nraj3ou el verdict final (boolean) w esm el voyageur mel Intent explicite
        String m = CleExtras.MATRICULE;
        boolean approuve = getIntent().getBooleanExtra(CleExtras.verdict(m), false);
        String nom = getIntent().getStringExtra(CleExtras.nomVoyageur(m));

        // 2 - Nbadlou loun el fond (a5dhar wala a7mar) w n3ardhou el message personnalise
        TextView tvMessage = findViewById(R.id.tv_message_ak);
        findViewById(R.id.layout_result_ak).setBackgroundColor(
                approuve ? Color.parseColor("#2E7D32") : Color.parseColor("#C62828"));
        tvMessage.setText(approuve
                ? getString(R.string.msg_ok, nom)
                : getString(R.string.msg_ko, nom));

        // 3 - Bouton Nouveau voyageur yarja3 lel MainActivity w ysaker les activites el 9dom
        Button btnNouveau = findViewById(R.id.btn_nouveau_ak);
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}