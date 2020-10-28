package market;

import java.util.List;

import market.db.accessDB;
import market.object.Item;
import market.object.Login;
import market.util.InputUI;

public class Market extends InputUI {
	
	private Login user;
	protected static accessDB adb = new accessDB();
	protected MyPage mp = null;
	
	public Market() {

	}
	
	public Market(Login user) {
		this.user = user;
	}

	/**
	 * 로그인 후 메인페이지 메소드 : 장터 및 마이페이지 선택
	 */
	public void mainPage() {
		Loop: while (true) {
			System.out.println("======================================");
			System.out.println("\tMoaMarket");
			System.out.println("======================================");
			System.out.println("\t1. 장터");
			System.out.println("\t2. 마이페이지");
			System.out.println("\t3. 로그아웃");
			System.out.println("======================================");
			int type = scanInt("\t원하는 메뉴를 선택하세요 : ");

			try {
				switch (type) {
				case 1:
					showItem();
					break;
				case 2:
					user = Login.getInstance();
					mp = new MyPage(user);
					mp.myPage();
					break;
				case 3 :
					user.logout();
					if(user.getLogin() == false)
						break Loop;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 장터 전체 리스트 메소드
	 */
	public void showItem() {
		List<Item> itemList = null;
				
		System.out.println("======================================");
	    System.out.println("\tMoaMarket AllList");
	    System.out.println("======================================");
	    System.out.println("번호\t카데고리\t상품명\t가격\t판매유무\t작성자\t요청유무\t작성일");
	    
	    itemList = adb.showItem();
	    
	    for(Item item : itemList) {
	    	System.out.println(item.getNo()+"\t"+item.getCate()+"\t"+item.getName()+"\t"+item.getPrice()+"\t"+item.getState()+"\t"+item.getWriter()+"\t"+item.getRequest()+"\t"+item.getReg_date());
	    }
	    
	    System.out.println("======================================");
	    int no = scanInt("\t보고 싶은 상품번호를 선택하세요(뒤로가기 0) : ");
	    
	    if(no == 0 ) 
	    	mainPage();
	    else
	        showDateilItem(no);
		
	}
	
	/**
	 * 선택한 상품(no) 상세페이지 메소드
	 * @param no 선택한 상품번호
	 */
	public void showDateilItem(int no) {
		
		Item result = adb.showDetailItem(no);
		
		if(result == null) {
			System.out.println(no+"번의 상품은 존재하지 않습니다");
		} else {
			System.out.println("======================================");
		    System.out.println("\t"+no+"번의 상품 상세페이지");
		    System.out.println("======================================");
		    System.out.println("\t 카 테 고 리 : "+ result.getCate());
		    System.out.println("\t 상   품   명 : "+ result.getName());
		    System.out.println("\t 가         격 : "+ result.getPrice());
		    System.out.println("\t 판 매 유 무 : "+ result.getState());
		    System.out.println("\t 작   성   자 : "+ result.getWriter());
		    System.out.println("\t 요 청 유 무 : "+ result.getRequest());
		    System.out.println("\t 작   성   일 : "+ result.getReg_date());
		    
		    System.out.println("======================================");
		    System.out.println("\t1. 구매요청");
		    System.out.println("\t2. 뒤로가기");
		    System.out.println("======================================");
		    int type = scanInt("\t원하는 메뉴를 선택하세요 : ");
		    
		    if(type == 2) 
		    	showItem();
		    
		    int num = adb.requestItem(type);

		    if(num == 0)
		    	System.out.println("요청이 완료되었습니다");
		    else if(num == 1)
		    	System.out.println("다른 고객이 요청한 상품입니다");
		    else if(num == 2)
		    	System.out.println("요청이 실패되었습니다");
		    else if(num == -1)
		    	System.out.println("요청 오류입니다");
		}
	}
	
	
	

}
