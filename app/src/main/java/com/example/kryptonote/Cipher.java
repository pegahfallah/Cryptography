package com.example.kryptonote;

public class Cipher {
    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public Cipher(String key){
        this.key = key;
    }

    private String makePad(String note){ //take the note and set the pad = to the given key.Then create a loop that
        String pad = this.key;
        for( ; pad.length() < note.length(); ){
            pad += this.key; //pad = pad + this.key eg. 12341234123412 depending on how long the note is
        }
        return pad; //return a pad that repeats
    }

    public String encrypt(String note){
        String result = "";
            String pad = makePad(note); //get the pad that is repeated
            for (int i = 0; i < note.length(); i++) { // set an integer i , make sure i is < notelength, run code once, check again, increment i
                String c = note.substring(i, i + 1); // c is index of user note get the letter of the alphabet

                int position = ALPHABET.indexOf(c); //get the index of that letter by
                int shift = Integer.parseInt(pad.substring(i, i + 1)); //pad is a string, has a method called substring that gets specific letter
                int newPosition = (position + shift) % ALPHABET.length(); //the remainder is the index
                result = result + ALPHABET.substring(newPosition, newPosition + 1); // tell computer the alphabet order , shift forward to next
            }
        return result;
    }

    public String decrypt(String note){ //get note from the user
       String result = "";
           String pad = makePad(note);

           for (int i = 0; i < note.length(); i++) {
               String c = note.substring(i, i + 1); // getting a letter of the note
               int position = ALPHABET.indexOf(c); //getting the index of that letter in the alphabet

               int shift = Integer.parseInt(pad.substring(i, i + 1)); //substring method matches the pad with the letter given
               int newPosition = (position - shift); //new position is the position in the alphabet minus the shift given by substring
               if (newPosition < 0) {
                   newPosition += ALPHABET.length();
               }
               result = result + ALPHABET.substring(newPosition, newPosition + 1);
           }

        return result;
    }


    public static void main(String[] args) {
        Cipher v = new Cipher("1234");
        System.out.println(v.decrypt("UJLWAKVDBBWITV"));
    }
}
