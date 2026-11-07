package com.example.douanetemporelle;

// صنف مسؤول عن بناء مفاتيح putExtra (ممنوع كتابتها مباشرة في الكود)
public class CleExtras {

    // 1 - رقم البطاقة (آخر 4 أرقام) لأحد أعضاء المجموعة - غيّره برقمك
    public static final String MATRICULE = "1234";

    // 2 - دالة ثابتة تبني المفتاح انطلاقا من الرقم
    private static String construire(String matricule, String suffixe) {
        return matricule + "_" + suffixe;
    }

    // 3 - مفتاح اسم المسافر
    public static String nomVoyageur(String matricule) {
        return construire(matricule, "NOM_VOYAGEUR");
    }

    // 4 - مفتاح سنة الولادة
    public static String anneeNaissance(String matricule) {
        return construire(matricule, "ANNEE_NAISSANCE");
    }

    // 5 - مفتاح سنة الوجهة
    public static String anneeDestination(String matricule) {
        return construire(matricule, "ANNEE_DESTINATION");
    }

    // 6 - مفتاح سبب السفر
    public static String motif(String matricule) {
        return construire(matricule, "MOTIF");
    }

    // 7 - مفتاح القرار النهائي (منطقي)
    public static String verdict(String matricule) {
        return construire(matricule, "VERDICT");
    }
}
