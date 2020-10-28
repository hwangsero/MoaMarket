package market;

import market.object.Join;
import market.object.Login;
import market.util.InputUI;

public class MainUI extends InputUI {
	
	Login user = null;
	Market market = null;
	
	public void execute() {
		while(true) {
			try {
				int type = start();
				switch(type) {
				case 1 :
					new Join().addUser();
					break;
				case 2 :
					user = Login.getInstance();
					user.login();
					
					if(user.getLogin() == true) {
						market = new Market(user);
						market.mainPage();
					} else {
						System.out.println("로그인을 실패하였습니다. 재입력 바랍니다");
					}
					break;
				case 3 :
					System.exit(0);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int start() {
//	    Login User;
//	    Market market;
	    
	    System.out.println("======================================");
	    System.out.println("\tMoaMarket");
	    System.out.println("======================================");
	    System.out.println("\t1. 회원가입");
	    System.out.println("\t2. 로그인");
	    System.out.println("\t3. 종료");
	    System.out.println("======================================");
	    int type = scanInt("\t원하는 메뉴를 선택하세요 : ");
	    
	    return type;
	 }
	
}
