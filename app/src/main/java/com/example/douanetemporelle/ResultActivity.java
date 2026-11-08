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

        // nraj3o ldecision w esm el voyageur mel Intent elli jena mel ecran lowel
        String m = CleExtras.MATRICULE;
        boolean approuve = getIntent().getBooleanExtra(CleExtras.verdict(m), false);
        String nom = getIntent().getStringExtra(CleExtras.nomVoyageur(m));

        // Nbadlou loun l'écran w message 7asb el décision
        TextView tvMessage = findViewById(R.id.tv_message_mk);
        findViewById(R.id.layout_result_mk).setBackgroundColor(
                approuve ? Color.parseColor("#2E7D32") : Color.parseColor("#C62828"));
        tvMessage.setText(approuve
                ? getString(R.string.msg_ok, nom)
                : getString(R.string.msg_ko, nom));

        // nrj3o lel ecran principal w nsakro les activites lokhin
        Button btnNouveau = findViewById(R.id.btn_nouveau_mk);
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
