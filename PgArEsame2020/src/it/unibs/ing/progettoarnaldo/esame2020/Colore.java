package it.unibs.ing.progettoarnaldo.esame2020;

public enum Colore {
        BLU(0), ROSSE(1), GIALLE(2), VERDI(3);

        private final int value;
        Colore(int value){
                this.value = value;
        }

        public int getValue() {
                return value;
        }

}
