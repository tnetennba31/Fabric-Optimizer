import java.util.Scanner;

public class runner {
	static int fwidth, flength;
	static CutFabric alongWidth, alongLength;
	static boolean running = true;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (running)
		{
			doTheThing();
			boolean noInput = true;
			while (noInput) {
				System.out.println("Wanna optimize some more? Y or N");
				String answer = sc.next();
				if (answer.equals("N") || answer.equals("n")) {
					running = false;
					noInput = false;
				} else if (answer.equals("Y") || answer.equals("y")) {
					noInput = false;
				}
			}
		}
	}
	
	private static void doTheThing() {
		System.out.print("Enter fabric width: ");
		fwidth = sc.nextInt();
		System.out.print("Enter fabric length: ");
		flength = sc.nextInt();
		
		optimize();
	}
	
	public static void optimize() {
		System.out.println("You would get " + cutAlongWidth() + " pieces of fabric if the bottom edges aligned with the width.");
		System.out.println("You would get " + cutAlongLength() + " pieces of fabric if the bottom edges aligned with the length.");
		configureLayout();
	}
	
	// if the bottom of the masks lined up with the width
	public static int cutAlongWidth() {
		alongWidth = new CutFabric(fwidth / 9, (int) (flength / 6.25));
		return alongWidth.quantity;
	}
	
	// if the bottom of the masks lined up with the length
	public static int cutAlongLength() {
		alongLength = new CutFabric((int) (fwidth / 6.25), flength / 9);
		return alongLength.quantity;
	}
	
	public static void configureLayout() {
		int piecesW, piecesL;
		String s1 = "pieces";
		String s2 = "pieces";
		
		if (alongLength.quantity > alongWidth.quantity) {
			piecesL = alongLength.numPiecesLong;
			piecesW = alongLength.numPiecesWide;
		} else {
			piecesW = alongWidth.numPiecesWide;
			piecesL = alongWidth.numPiecesLong;
		}
		
		if (piecesW == 1) {
			s1 = "piece";
		}
		if (piecesL == 1) {
			s2 = "piece";
		}
		
		System.out.println("Cut width into " + piecesW + " " + s1 + " or length into " + piecesL + " " + s2 + ".  :)");
	}
}