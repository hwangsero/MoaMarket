package market;

import java.util.List;

import market.object.Item;
import market.object.Login;

public class MyPageUI extends MyPage {
	
	/**
	 * 판매목록 전체 리스트
	 */
	public void sellList(Login user) {
		
		System.out.println("======================================");
	    System.out.println("\t"+user.getNickname()+"님의 판매목록");
	    System.out.println("======================================");
	    System.out.println("번호\t카데고리\t상품명\t가격\t판매유무\t작성자\t요청유무\t작성일");
	    
//	    String email = user.getEmail();
		List<Item> itemList = adb.showSellList(user.getEmail());
		
	    for(Item item : itemList) {
	    	System.out.println(item.getNo()+"\t"+item.getCate()+"\t"+item.getName()+"\t"+item.getPrice()+"\t"+item.getState()+"\t"+item.getWriter()+"\t"+item.getRequest()+"\t"+item.getReg_date());
	    }
	    
	    System.out.println("======================================");
	    
	    int backNum = scanInt("\t1. 뒤로 가기 : ");
	    
//	    if(backNum == 1)
//	    	myPage();
	}

	/**
	 * 구매목록 전체 리스트
	 */
	public void buyList(Login user) {
		
		System.out.println("======================================");
	    System.out.println("\t"+user.getNickname()+"님의 구매목록");
	    System.out.println("======================================");
	    System.out.println("번호\t카데고리\t상품명\t가격\t판매유무\t작성자\t요청유무\t작성일");
	    
		String email = user.getEmail();
		List<Item> itemList = adb.showBuyList(email);
		
		for(Item item : itemList) {
	    	System.out.println(item.getNo()+"\t"+item.getCate()+"\t"+item.getName()+"\t"+item.getPrice()+"\t"+item.getState()+"\t"+item.getWriter()+"\t"+item.getRequest()+"\t"+item.getReg_date());
	    }
		
		System.out.println("======================================");
	    
		int backNum2 = scanInt("\t1. 뒤로 가기 : ");
	    
//		if(backNum2 == 1)
//	    	myPage();
	}

	/**
	 * 상품 추가 메소드
	 */
	public void addPro(String nickname) {
		
		String name = scanStr("상품명을 입력하세요 : ");
    	int addPrice = scanInt("상품 가격을 입력하세요 : ");
    	
    	Boolean addBool = adb.addProduct(name, addPrice, nickname);
    	
    	if(addBool)
    		System.out.println("상품 등록을 완료하였습니다");
    	else
    		System.out.println("상품 등록에 실패하였습니다");
		
	}
	
	/**
	 * 상품 수정 메소드
	 */
	public void upPro() {
		
		int upNum = scanInt("수정할 상품번호를 입력하세요 : ");
    	int upPrice = scanInt("수정할 가격을 입력하세요 : ");
    	
    	Boolean upBool = adb.updateProduct(upNum, upPrice);
    	
    	if(upBool)
    		System.out.println("상품 수정을 완료하였습니다");
    	else
    		System.out.println("상품 수정을 실패하였습니다");
	}

	/**
	 * 상품 삭제 리스트
	 */
	public void dePro() {
		
		int deNum = scanInt("삭제할 상품번호를 입력하세요 : ");
		
    	Boolean deBool = adb.deleteProduct(deNum);
    	
    	if(deBool)
    		System.out.println("상품 삭제를 완료하였습니다");
    	else
    		System.out.println("상품 삭제를 실패하였습니다");
    	
	}
	
	
}
