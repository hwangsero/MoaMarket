package market;

import market.object.Login;

public class MyPage extends Market {
	
	Login user = null;
	MyPageUI mpu = null;
	
	public MyPage() {
		
	}
	
	public MyPage(Login user) {
		this.user = user;
		mpu = new MyPageUI();
	}
	
	/**
	 * 마이페이지 메인 : 3depth로 가기위한 선택 메소드 
	 */
	public void myPage() {
		while (true) {
			System.out.println("======================================");
			System.out.println("\t" + user.getNickname() + "님의 MyPage");
			System.out.println("======================================");
			System.out.println("\t1. 물품관리");
			System.out.println("\t2. 판매목록");
			System.out.println("\t3. 구매목록");
			System.out.println("\t4. 뒤로가기");
			System.out.println("======================================");
			int type = scanInt("\t원하는 메뉴를 선택하세요 : ");
		
			if(type == 4) break;

			myPageCho(type);
		}
	}
	
	/**
	 * 마이페이지 3depth 선택시 실행되는 메소드
	 * @param type 3뎁스선택 번호
	 */
	public void myPageCho(int type) {
//		while (true) {
			switch (type) {
			case 1: // 물품관리
				manProduct();
				break;
			case 2: // 판매목록
				mpu.sellList(user);
				break;
			case 3: // 구매목록
				mpu.buyList(user);
				break;
			case 4: // 뒤로가기
				break;
			default:
				System.out.println("잘못 입력하였습니다 재 입력 바랍니다");
				break;
//			}
		}
	}
	
	/**
	 * 물품관리 페이지 : 4depth로 가기위한 선택 메소드 
	 */
	public void manProduct() {
		while (true) {
			System.out.println("======================================");
			System.out.println("\t" + user.getNickname() + "님의 물품관리");
			System.out.println("======================================");
			System.out.println("\t1. 상품 추가");
			System.out.println("\t2. 상품 수정");
			System.out.println("\t3. 상품 삭제");
			System.out.println("\t4. 뒤로 가기");
			System.out.println("======================================");
			int type = scanInt("\t원하는 메뉴를 선택하세요 : ");
			
			if(type == 4) break;
			manProductCho(type);
			
			
		}
	}
	
	/**
	 * 물품관리 4depth 선택시 실행되는 메소드
	 * @param type 4depth 선택 번호
	 */
	public void manProductCho(int type) {
		switch(type) {
	    case 1 :		//상품 추가
	    	String nickname = user.getNickname();
	    	mpu.addPro(nickname);
	    	break;
	    case 2 :		//상품 수정
	    	mpu.upPro();
	    	break;
	    case 3 :		//상품 삭제
	    	mpu.dePro();
	    	break;
	    case 4 :		//뒤로 가기
	    	myPage();
	    	break;
	    }
	}
	
}
