package com.example.douanetemporelle;

// Classe ta3mel les clés putExtra automatiquement b matricule, bla ma nektbouhom directement fel code
public class CleExtras {

    public static final String MATRICULE = "3483";

    //Na3mlou fonction statique ta3mel el clé automatiquement b numéro carte étudiant
    private static String construire(String matricule, String suffixe) {
        return matricule + "_" + suffixe;
    }

    // Clé mta3 esm el voyageur
    public static String nomVoyageur(String matricule) {
        return construire(matricule, "NOM_VOYAGEUR");
    }

    // Clé mta3 année de naissance
    public static String anneeNaissance(String matricule) {
        return construire(matricule, "ANNEE_NAISSANCE");
    }

    // Clé mta3 année de destination
    public static String anneeDestination(String matricule) {
        return construire(matricule, "ANNEE_DESTINATION");
    }

    // cle mta3 lmotif 3leh
    public static String motif(String matricule) {
        return construire(matricule, "MOTIF");
    }

    //cle mt3 decision finale
    public static String verdict(String matricule) {
        return construire(matricule, "VERDICT");
    }
}
