package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etNaissance, etDestination;
    private Spinner spMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Norbto les elements mta3 l'interface bel Java bech najmou nesta3mlouhom
        etNom = findViewById(R.id.et_nom_nj);
        etNaissance = findViewById(R.id.et_naissance_nj);
        etDestination = findViewById(R.id.et_destination_nj);
        spMotif = findViewById(R.id.sp_motif_nj);
        btnInspecter = findViewById(R.id.btn_inspecter_nj);

        // N3abbiw l liste deroulante b asbeb essafar: Tourisme, Modification du passe, Fuite
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.motifs, android.R.layout.simple_spinner_dropdown_item);
        spMotif.setAdapter(adapter);

        // Ki nenzel 3al bouton, netthabtou mel donnees w ba3d nemchiw lel ecran theni
        btnInspecter.setOnClickListener(v -> inspecter());
    }

    private void inspecter() {
        String nom = etNom.getText().toString().trim();
        String naissance = etNaissance.getText().toString().trim();
        String destination = etDestination.getText().toString().trim();

        // Net2akdou elli les champs lkol m3abbin w ma fama hata champ feragh
        if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(naissance) || TextUtils.isEmpty(destination)) {
            Toast.makeText(this, R.string.erreur_champs, Toast.LENGTH_SHORT).show();
            return;
        }

        // 5 - Nesta3mlou Intent bech nba3thou les informations mel ecran lowel lel ecran theni.
        String m = CleExtras.MATRICULE;
        Intent intent = new Intent(this, VerificationActivity.class);
        intent.putExtra(CleExtras.nomVoyageur(m), nom);
        intent.putExtra(CleExtras.anneeNaissance(m), Integer.parseInt(naissance));
        intent.putExtra(CleExtras.anneeDestination(m), Integer.parseInt(destination));
        intent.putExtra(CleExtras.motif(m), spMotif.getSelectedItem().toString());
        startActivity(intent);
    }
}
