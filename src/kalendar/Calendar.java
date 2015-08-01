package kalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Calendar {

	private static Scanner input;
	
	public static void main(String[] args) throws IOException {
		/*
		 program koji trebamo napisati treba da pita korisnika da unese mjesec
		 i godinu te da mu ispiše, kao na slici ispod, kalendar za taj mjesec i tu godinu.
		 Kada program ispiše dati mjesec korisniku na konzoli, program onda pita korisnika
		 da li želi unijeti neki reminder za odreðeni dan ili želi vidjeti kalendar za neki drugi mjesec.
		 Ukoliko korisnik želi neki podsjetnik/reminder/note za odreðeni dan, program treba
		 da saèuva korisnikov unos i da ga prikaže pri sljedeæem pokretanju programa. 
		 */
		
		input = new Scanner(System.in);
		
		ucitajPodsjetnik();     // pozivanje metode za ucitavanje podsjetnika iz fajla
		
		unosMjesecGodina();     // pozivanje metode za unos mjeseca i godine
		
		boolean isOn = true;
		
		do {   // ispis menu-ija za unos novog mjeseca i godine, novog podsjetnika, pregled starog i izlaz
			System.out.println("\n\t1. Novi unos mjeseca i godine");
			System.out.println("\t2. Unos novog podsjetnika");
			System.out.println("\t3. Pregled zadnjeg podsjetnika");
			System.out.println("\t4. Izlaz\n");
			System.out.print("\tUnesite jednu od opcija: ");
		try {
			int opcija = input.nextInt();
			while(opcija < 0 || opcija > 4) {
				System.out.print("\tPogresan unos, unesite broj 1, 2, 3 ili 4.");
				opcija = input.nextInt(); // unos opcija
			}
		switch(opcija) {
		case 1:
			System.out.println();
			unosMjesecGodina();           // prva opcija, za novi unos mjeseca i godine (ispis kalendara)
			break;
		case 2:
			dodajPodsjetnik();            // druga opcija, pozivanje metode za unos novog podsjetnika
			break;
		case 3:
			pregledPodsjetnika();         // treca opcija, za pregled zadnje unesenog podsjetnika
			break;
		case 4:
			System.out.println("\tHvala, dovidjenja!"); // cetvrta opcija, izlaz iz programa
			isOn = false;
			System.exit(1);
			break;
		}
		snimiPodsjetnik();                // snimanje novo-unesenog podsjetnika u fajl
		
		} catch(NoSuchElementException e) {
			System.out.println("\tPogresan unos, Unesite broj!");
			input.nextLine();
		}
	 } while(isOn);                       // petlja se 'vrti' dok se uslov ne ispuni
  }
	/** Metoda za unos mjeseca i godine */
	public static void unosMjesecGodina() {
		
		input = new Scanner(System.in);
		boolean isOn = false;
		
	do {
		try {
		System.out.print("\tUnesite mjesec (brojevima, Jan=1,...,Dec=12): ");
		int mjesec = input.nextInt();            // unos mjeseca  
		System.out.print("\tUnesite godinu: ");
		int godina = input.nextInt();            // unos godine
		
		isOn = false;
		ispisBody(mjesec, godina);               // poziv metode za ispis body kalendara za uneseni mjesec i godinu
		} catch(InputMismatchException e) {
			System.out.println("\tPogresan unos, Unesite broj!");
			input.nextLine();
		}
	} while(isOn);
	}
	/** Metoda za ispis body-ja kalendara u konzolu */
	public static void ispisBody(int mjesec, int godina) {

		// niz stringova sa svih 12 mjeseci u godini
		String [] mjeseci = {"January", "February", "March", "April", "May", "Jun", "July", "August", "Septembar", "Oktombar", "Novembar", "Decembar"};
		String [] dani = {"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};
		for(int i=0; i<mjeseci.length-1; i++) {  // petlja koja prolazi kroz niz sa mjesecima
		}
		System.out.println();
		System.out.println("\t\t\t   " + mjeseci[mjesec-1] + " " + godina + "\t\t\t"); // ispis unesenog mjeseca i godine u konzolu
		System.out.println("\t___________________________________________________");
		for(int i=0; i<dani.length; i++) {       // petlja koja prolazi kroz string sa svim danima u sedmici
			System.out.print("\t" + dani[i]);    // ispis svih dana u konzolu
		}
		int i = 0;
		System.out.println();
		for (i = 0; i < pocetniDan(mjesec, godina); i++) {    // petlja koja ide od pocetka body-ja do pocetnog dana za uneseni mjesec i godinu
			System.out.print("\t");                           // ispis praznih polja do pocetnog dana u body-ju kalendara
		}
		for (i = 1; i <= daniUMjesecu(mjesec, godina); i++) { // petlja koja prolazi kroz ukupan broj dana u mjesecu za unesenu godinu i mjesec
			System.out.print("\t" + i);                       // printa prazna polja i dane (pocevsi od 1. u mjesecu)
			if ((i + pocetniDan(mjesec, godina)) % 7 == 0) {  // kad printanje dana dodje do 7 (kraja sedmice) printa se u sledeci red
			System.out.println();
			}
		}
		System.out.println();
	}
	/** Metoda koja vraca pocetni dan unesenog mjeseca, unesene godine */
	public static int pocetniDan(int mjesec, int godina) {
		
		int pocetni = 2;              // pocetni dan od 1.1.1800 godine
		// formula za izracunavanje pocetnog dana za uneseni mjesec i godinu
		return (daniGodina(mjesec, godina) + pocetni) % 7; // vraca pocetni dan za unesenu godinu i mjesec
	}
	/** Metoda koja vraca ukupno dana do unesenog broja godina */
	public static int daniGodina(int mjesec, int godina) {
		
		int suma = 0;
		
		for(int i = 1800; i < godina; i++) { // petlja koja ide od 1800 godine do unosa godine
			if(prestupna(i)) {               // ako je godina prestupna
				suma += 366;				 // sabira u ukupnu sumu 366
			} else {                         // ako godina nije prestupna
				suma += 365;                 // sabira u ukupnu sumu 365
			}
		}
		for(int i = 1; i < mjesec; i++) {    // petlja koja ide od prvog do unesenog mjeseca
			suma += daniUMjesecu(i, godina); // sabira u ukupnu sumu izracunate dane u mjesecu za unesenu godinu
		}
		return suma;                         // vraca ukupnu sumu dana od 1800 godine do unesene godine i uneseni mjesec
	}
	/** Metoda koja vraca broj dana unesenog mjeseca, unesene godine */
	public static int daniUMjesecu(int mjesec, int godina) {
		
		if(mjesec == 2 && prestupna(godina)) {  // ako je mjesec 2 i prestupna godina
			return 29;                          // vraca 29
		} 
		if(mjesec == 2 && !prestupna(godina)) { // ako je mjesec 2 i nije prestupna
			return 28;                          // vraca 28
		}
		if(mjesec == 4 || mjesec == 6 || mjesec == 9 || mjesec == 11) { // ako je unos bilo koji od ovih mjeseci
			return 30;                          // vraca 30
		}
		if(mjesec == 1 || mjesec == 3 || mjesec == 5 || mjesec == 7 ||
			mjesec == 8 || mjesec == 10 || mjesec == 12) {              // a ako je unos bilo koji od ovih mjeseci
			return 31;                          // vraca 31
		}
		return 0;                               // ako nije nista od navedenog, vraca 0
	}
	/** Metod koji provjerava da li je godina prestupna ili nije */
	public static boolean prestupna(int godina) {
		
		if (godina%400==0 || (godina%4==0 && godina%100!=0)) { // po formuli ako je godina prestupna
			return true;                                       // vraca true, istinito
		} else {                                               // ako nije prestupna
			return false;                                      // vraca false, neistinito
		}
	}

	public static File file = new File("kalendar.txt");             // fajl sa podsjetnicima
		
	public static ArrayList<String> podsjetnik = new ArrayList<>(); // arraylista u koju smjestamo podsjetnike
	
	/** Metoda koja ispisuje podsjetnik iz arrayliste */
	public static void pregledPodsjetnika() {
		
		System.out.println("\tPodsjetnik: ");
		for(int i=0; i< podsjetnik.size(); i++) {
			System.out.println("\t" + podsjetnik.get(i));  // ispis podsjetnika iz arrayliste
		}
		
	}
	/** Metoda koja load-uje podsjetnik sa fajla */
	public static void ucitajPodsjetnik() {	 
	 
		try {
		 
		 input = new Scanner(file);
		 
		 while (input.hasNextLine()) {         // dok ima recenica u fajlu
			 podsjetnik.add(input.nextLine()); // ispis i ubacivanje u arraylistu
		 }
		 
		 } catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
		 }

	}
	/** Metoda koja unosi novi podsjetnik u arraylistu */
	public static void dodajPodsjetnik(){
			
		input = new Scanner(System.in);
		podsjetnik.clear();                          // brisanje starog podsjetnika iz arrayliste
		System.out.print("\tUnesite podsjetnik: ");  // ispis texta u konzolu
		podsjetnik.add(input.nextLine());            // ubacivanje novog texta u arraylistu
			
	}
	/** Metoda koja snima podsjetnike u fajl */
	public static void snimiPodsjetnik() throws IOException {
		
		try {
			
			File file = new File("kalendar.txt");            // za trazeni fajl sa podsjetnicima
			FileWriter writer = new FileWriter(file, false);
			
			for(String s: podsjetnik) {
				writer.write(s + " ");                       // iz arrayliste upisujemo podatke u taj fajl
			}
			
			writer.close();
		    } catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}

