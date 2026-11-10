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

        // 1 - Istirja3 el mo3tayet b isti3mel nafs el mafeti7
        String m = CleExtras.MATRICULE;
        Intent in = getIntent();
        nom = in.getStringExtra(CleExtras.nomVoyageur(m));
        int naissance = in.getIntExtra(CleExtras.anneeNaissance(m), 0);
        int destination = in.getIntExtra(CleExtras.anneeDestination(m), 0);
        String motif = in.getStringExtra(CleExtras.motif(m));

        // 2 - 7iseb el 3mor el zamani (el far9 bin 3am el wejha w 3am el wleda)
        int ageTemporel = destination - naissance;

        // 3 - 3ardh el mola5as 3la chakel jawaz safar
        ((TextView) findViewById(R.id.tv_nom_oa)).setText(getString(R.string.lbl_nom, nom));
        ((TextView) findViewById(R.id.tv_age_oa)).setText(getString(R.string.lbl_age, ageTemporel));
        ((TextView) findViewById(R.id.tv_motif_oa)).setText(getString(R.string.lbl_motif, motif));

        Button btnApprouver = findViewById(R.id.btn_approuver_oa);
        Button btnRefuser = findViewById(R.id.btn_refuser_oa);

        // 4 - Ta3til bouton Approuver ken el motif Modification du passe w el far9 3adad awali
        boolean modification = getString(R.string.motif_modification).equals(motif);
        if (modification && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // 5 - El inti9al ila nachat el natija m3a el 9arar w esm el mosefer
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true));
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false));
    }

    // 6 - Fonction i5tibar el 3adad el awali
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