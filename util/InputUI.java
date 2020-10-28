package market.util;

import java.util.Scanner;

public class InputUI {
	
	private Scanner sc;
	
	public InputUI() {
		sc = new Scanner(System.in);
	}
	
	/**
	 * 정수 입력받는 메소드
	 */
	protected int scanInt(String msg) {
		System.out.print(msg);
		int num = Integer.parseInt(sc.nextLine());
		return num;
	}
	
	/**
	 * 문자열 입력받는 메소드
	 */
	protected String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

}
