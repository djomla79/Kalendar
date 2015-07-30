package kalendar;

import java.util.Scanner;

public class Kalendar {

	public static void main(String[] args) {
		/*
		 program koji trebamo napisati treba da pita korisnika da unese mjesec i
		 godinu te da mu ispiše, kalendar za taj mjesec i tu godinu.
		 Kada program ispiše dati mjesec korisniku na konzoli, program onda pita korisnika
		 da li želi unijeti neki reminder za odreðeni dan ili želi vidjeti kalendar za neki drugi mjesec.
		 Ukoliko korisnik želi neki podsjetnik/reminder/note za odreðeni dan, program treba da saèuva
		 korisnikov unos i da ga prikaže pri sljedeæem pokretanju programa.
		*/
		
		Scanner input = new Scanner(System.in);
		// niz stringova sa svih 12 mjeseci u godini
		String [] mjeseci = {"January", "February", "March", "April", "May", "Jun", "July", "August", "Septembar", "Oktombar", "Novembar", "Decembar"};
		
		String [] dani = {"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};
		
		System.out.print("Unesite mjesec i godinu: ");
		int mjesec = input.nextInt();            // unos mjeseca  
		int godina = input.nextInt();            // unos godine
		
		while(godina > 2015) {                   // dok se uslov ne ispuni petlja se 'vrti'
			System.out.print("Unesite godinu ponovo: ");
			godina = input.nextInt();            // ponovni unos godine
		}
		for(int i=0; i<mjeseci.length-1; i++) {  // petlja koja prolazi kroz niz sa mjesecima
		}
		System.out.println();
		System.out.println("\t\t\t   " + mjeseci[mjesec-1] + " " + godina + "\t\t\t");
		System.out.println("\t___________________________________________________");
		for(int i=0; i<dani.length; i++) {
			System.out.print("\t" + dani[i]);
		}
		int i = 0;
		System.out.println();
		for (i = 0; i < pocetniDan(mjesec, godina); i++) {
			System.out.print("\t");
		}
		for (i = 1; i <= daniUMjesecu(mjesec, godina); i++) {
			System.out.print("\t" + i);
			if ((i + pocetniDan(mjesec, godina)) % 7 == 0) {
			System.out.println();
			}
		}
		System.out.println();
		input.close();
	}
	/** Metoda koja vraca pocetni dan unesenog mjeseca, unesene godine */
	public static int pocetniDan(int mjesec, int godina) {
		
		final int POCETNI_DAN_1800 = 3;              // pocetni dan od 1.1.1800 godine
		// formula za izracunavanje pocetnog dana za uneseni mjesec i godinu
		return (daniGodina(mjesec, godina) + POCETNI_DAN_1800) % 7; // vraca pocetni dan za unesenu godinu i mjesec
	}
	/** Metoda koja vraca ukupno dana do unesenog broja godina */
	public static int daniGodina(int mjesec, int godina) {
		
		int suma = 0;
		
		for(int i = 1800; i < godina; i++) { // petlja koja ide od 1800 do unosa godine
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
	
	/** Metod koji snima sve korisnike iz arrayliste u fajl 
	public void saveKorisnikToFile() {
		
		try {
			File file = new File("kalendar.txt");         // za trazeni fajl
			FileWriter writer = new FileWriter(file, false); // vrsimo upis u taj fajl
			
		for (Korisnik list: lista) {                     // za sve korisnike iz liste
				writer.write(list.getId() + " ");        // upis imena i prezimena
				writer.write(list.getPassword() + " ");  // upis passworda
				writer.write(list.getBalance() + " \n"); // upis ukupnog stanja na racunu
			}
		    writer.close();
		    } catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	} */
	
}
