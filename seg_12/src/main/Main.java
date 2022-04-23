package main;
import java.util.Scanner;
import model.*;
public class Main {
	public static Scanner sc;
	static int numShift;
	static LinkedListCircular<String> shifts;
	static LinkedListCircular<String> saw;
	static int round;
	public final static int MAX_SHIFTS=50;
	public static void main(String[]args) {
		sc=new Scanner(System.in);
		numShift=0;
		shifts=new LinkedListCircular<>();
		saw=new LinkedListCircular<>();
		boolean flag=false;
		while(!flag) {
			flag=menu();
		}
	
		
	}
	
	public static boolean  menu() {
		System.out.println(
				"1. Dar turno\n"+
				"2. Mostrar turno\n" +
				"3. Pasar turno\n"+
				"4. Eliminar turno actual\n"+
				"0. Terminar");
		int index=sc.nextInt();
		sc.nextLine();
		round=1;
		switch(index) {
			case 1:
				darTurno();
				break;
			case 2:
				showShift();
				break;
			case 3:
				pasarTurno();
				break;
			case 4:
				deleteShift();
				break;
			case 5:
				System.out.println(shifts);
				break;
			case 0:
				return true;
		}
		return false;
	}
	
	public static void darTurno() {
		
		if(shifts.getSize()==50) {
			System.out.println("Ya no hay tiquetes");
		}else {
			numShift++;
			shifts.add(numShift+"");
			if(shifts.getSize()==1) {
				shifts.reset();
			}
			if(numShift==MAX_SHIFTS) {
				numShift=0;
			}
		}
	}
	
	public static void pasarTurno() {
		shifts.up();
		if(saw.search(shifts.getActual())) {
			deleteShift();
		}else saw.add(shifts.getActual());
	}
	
	public static void showShift() {
		
		if(shifts.getActual()==null) {
			System.out.println("No hay turnos\n");
		}else System.out.println(shifts.getActual()+"\n");
		
	}
	
	public static void deleteShift() {
		saw.delete(shifts.getActual());
		shifts.delete(shifts.getActual());
		if(shifts.getSize()==0) {
			shifts.reset();
		}
	}
	
	
}
