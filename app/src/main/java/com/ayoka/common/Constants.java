package com.ayoka.common;

/**
 * Created by OsmanKorcan on 24.4.2016.
 */
public final class Constants {
    //Şirket Bazlı Raporlama İçin
    public enum Companies{
        VDF(1),
        Sigorta(2);
        private final int value;

        private Companies(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }

        public static Companies parse(int value){
            // DocumentType.values() metodu değer kümesini döndürür
            for (Companies type : Companies.values()) {
                if (value == type.getValue()) {
                    return type;
                }
            }
            return VDF;
        }
    };

}
