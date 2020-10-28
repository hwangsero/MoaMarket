package market.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import market.object.Item;
import market.util.DBClose;
import market.util.DBConnection;

public class accessDB {
   
   /**
    * 사용자가 존재하는 지 확인해주는 함수
    * @param email 사용자 이메일
    * @param password 사용자 패스워드
    * @return true : 사용자 있음, false : 사용자 없음
    */
   public boolean checkUser(String email, String password) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select count(*) count");
         sql.append(" from t_user");
         sql.append(" where email = ?");
         sql.append(" and password = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, email);
         pstmt.setString(2, password);
         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            int cnt = rs.getInt("count");
            
            if(cnt == 1)
               return true;
         }            
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return false;   
   }
   
   /**
    * 이메일이 존재하는 지 확인하는 함수
    * @param email 확인할 이메일
    * @return true : 이메일 있음, false : 이메일 없음
    */
   public boolean checkEmail(String email) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select count(*) count");
         sql.append(" from t_user");
         sql.append(" where email = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, email);
         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            int cnt = rs.getInt("count");
            
            if(cnt == 1)
               return true;
         }            
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return false;
   }
   
   /**
    * 닉네임이 존재하는 지 확인하는 함수
    * @param nickname 확인할 닉네임
    * @return true : 닉네임 있음, false : 닉네임 없음
    */
   public boolean checkNickname(String nickname) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select count(*) count");
         sql.append(" from t_user");
         sql.append(" where nickname = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, nickname);
         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            int cnt = rs.getInt("count");
            
            if(cnt == 1)
               return true;
         }            
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return false;
   }
   
   /**
    * 사용자를 t_user에 추가해주는 함수
    * @param email 등록할 이메일
    * @param nickname 등록할 닉네임
    * @param password 등록할 패스워드
    * @return true : 등록 성공, false : 등록 실패
    */
   public boolean addUser(String email, String nickname, String password) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("insert into t_user(user_no, email, nickname, password)");
         sql.append(" values(seq_t_user_no.nextval, ?, ?, ?)");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, email);
         pstmt.setString(2, nickname);
         pstmt.setString(3, password);
         
         int cnt = pstmt.executeUpdate();
         
         if(cnt == 1)
            return true;
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return false;
   }
   
   /**
    * 사용자 로그인 후 닉네임을 가져오는 함수
    * @param email 가져올 닉네임의 사용자 아이디
    * @return nickname
    */
   public String getNickname(String email) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select nickname");
         sql.append(" from t_user");
         sql.append(" where email = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, email);
         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            String nickname = rs.getString("nickname");
            return nickname;
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return null;
   }   
   
   /**
    * t_market에 있는 물품을 가져오는 함수
    * @return List<item> : 현재 등록된 물품 리스트
    */
   public List<Item> showItem() {
      List<Item> itemlist = new ArrayList<>();

      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select *");
         sql.append(" from t_market, t_sell, t_user");
         sql.append(" where t_market.pro_no = t_sell.pro_no");
         sql.append(" and t_user.user_no = t_sell.user_no");
         
         pstmt = conn.prepareStatement(sql.toString());
         
         ResultSet rs = pstmt.executeQuery();
         
         while(rs.next()) {
            int no = rs.getInt("pro_no");
            String cate = rs.getString("pro_cate");
            String name = rs.getString("pro_name");
            int price = rs.getInt("pro_price");
            int state = rs.getInt("state");
            String writer = rs.getString("nickname");
            int request = rs.getInt("request");
            String reg_date = rs.getString("reg_date");
            
            if(state == 1) { // 판매중일 떄
               Item item = new Item(no, cate, name, price, state, writer, request, reg_date);
               itemlist.add(item);
            }            
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return itemlist;
   }
   
   /**
    * 등록된 물품의 상세정보를 가져오는 함수
    * @param num 물품의 등록번호
    * @return Item : 물품 객체
    */
   public Item showDetailItem(int num) {

      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();         
         sql.append("select *");
         sql.append(" from t_market, t_sell, t_user");
         sql.append(" where t_market.pro_no = t_sell.pro_no");
         sql.append(" and t_user.user_no = t_sell.user_no");
         sql.append(" and t_market.pro_no = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, num);
         
         ResultSet rs = pstmt.executeQuery();
         
         while(rs.next()) {
            int no = rs.getInt("pro_no");
            String cate = rs.getString("pro_cate");
            String name = rs.getString("pro_name");
            int price = rs.getInt("pro_price");
            int state = rs.getInt("state");
            String writer = rs.getString("nickname");
            int request = rs.getInt("request");
            String reg_date = rs.getString("reg_date");
            
            Item item = new Item(no, cate, name, price, state, writer, request, reg_date);
            return item;
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return null;
   }
   
   /**
    * 물품을 살 때, 구매 요청을 넣어주는 함수
    * @param num 구매할 물품의 등록번호
    * @return 0: 요청 완료, 1: 요청이 이미 있음, 2: 요청 실패, -1: 요청 오류
    */
   public int requestItem(int num) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select request");
         sql.append(" from t_market");
         sql.append(" where pro_no = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, num);
         
         ResultSet rs = pstmt.executeQuery();
         
         while(rs.next()) {
            int request = rs.getInt("request");
            
            if(request == 0) { // 요청이 없으면
               sql = new StringBuilder();
               sql.append("update t_market");
               sql.append(" set request = 1");
               sql.append(" where pro_no = ?");
               
               pstmt = conn.prepareStatement(sql.toString());
               pstmt.setInt(1, num);
               
               int cnt = pstmt.executeUpdate();
               
               if(cnt == 1) 
                  return 0; // 정상적으로 요청 완료
               else
                  return 2; // 요청 실패
            } else {
               return 1; // 요청이 이미 있음
            }      
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return -1;
   }
   
   /**
    * 물품을 t_market에 추가해주는 함수
    * @param name 물품 이름
    * @param price 물품 가격
    * @param user 물품을 등록한 사용자
    * @return true: 등록 성공, false: 등록 실패
    */
   public boolean addProduct(String name, int price, String nickname) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("insert into t_market(pro_no, pro_name, pro_price)");
         sql.append(" values(seq_t_market_no.nextval, ?, ?)");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, name);
         pstmt.setInt(2, price);
         
         int cnt = pstmt.executeUpdate();
         
         if(cnt == 1) {
            int userNo = getUserNum(nickname);
            int proNo = getProductNum();
            sql = new StringBuilder();
            sql.append("insert into t_sell(no, user_no, pro_no)");
            sql.append(" values(seq_t_sell_no.nextval, ?, ?)");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, userNo);
            pstmt.setInt(2, proNo);
            
            cnt = pstmt.executeUpdate();
            
            if(cnt == 1) {
               return true;
            }            
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return false;
   }
   
   /**
    * 물품 추가할 때, t_sell 테이블에 연결 관계 추가하기 위해
    * user_no을 가져오는 함수
    * @param nickname user_no에 연결된 nickname
    * @return user_no
    */
   public int getUserNum(String nickname) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select user_no");
         sql.append(" from t_user");
         sql.append(" where nickname = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, nickname);
         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            int user_no = rs.getInt("user_no");
            
            return user_no;
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return 0;
   }
   
   /**
    * 물품 추가할 때, t_sell 테이블에 연결 관계 추가하기 위해
    * pro_no을 가져오는 함수
    * @return pro_no
    */
   public int getProductNum() {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select pro_no");
         sql.append(" from t_market");
         sql.append(" where rownum <= 1");
         sql.append(" order by pro_no desc");
         
         pstmt = conn.prepareStatement(sql.toString());
         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            int no = rs.getInt("pro_no");
            return no;
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return 0;
   }
   
   
   /**
    * 등록된 물품의 가격을 t_market에서 수정해주는 함수
    * @param num 물품의 등록번호
    * @param price 수정할 가격
    * @return true: 수정 성공, false: 수정 실패
    */
   public boolean updateProduct(int num, int price) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("update t_market");
         sql.append(" set pro_price = ?");
         sql.append(" where pro_no = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, price);
         pstmt.setInt(2, num);
         
         int cnt = pstmt.executeUpdate();
         
         if(cnt == 1)
            return true;
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return false;
   }
   
   /**
    * 등록된 물품을 t_market에서 삭제해주는 함수
    * @param num 물품의 등록번호
    * @return true: 삭제 성공, false: 삭제 실패
    */
   public boolean deleteProduct(int num) {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("delete from t_market");
         sql.append(" where pro_no = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setInt(1, num);
         
         int cnt = pstmt.executeUpdate();
         
         if(cnt == 1)
            return true;
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return false;
   }
   
   /**
    * 사용자에 따라 t_sell과 t_market을 join하여 판매 목록을 보여주는 함수
    * @param email
    * @return
    */
   public List<Item> showSellList(String email) {
      List<Item> itemlist = new ArrayList<>();

      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();         
         sql.append("select *");
         sql.append(" from t_market, t_sell, t_user");
         sql.append(" where t_market.pro_no = t_sell.pro_no");
         sql.append(" and t_user.user_no = t_sell.user_no");
         sql.append(" and t_user.email = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, email);
         
         ResultSet rs = pstmt.executeQuery();
         
         while(rs.next()) {
            int no = rs.getInt("pro_no");
            String cate = rs.getString("pro_cate");
            String name = rs.getString("pro_name");
            int price = rs.getInt("pro_price");
            int state = rs.getInt("state");
            String writer = rs.getString("nickname");
            int request = rs.getInt("request");
            String reg_date = rs.getString("reg_date");
            
            Item item = new Item(no, cate, name, price, state, writer, request, reg_date);
            itemlist.add(item);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return itemlist;
   }
   
   /**
    * 사용자에 따라 t_buy과 t_market을 join하여 구매 목록을 보여주는 함수
    * @param email
    * @return List<Item> : 구매 목록
    */
   public List<Item> showBuyList(String email) {
      List<Item> itemlist = new ArrayList<>();

      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = new DBConnection().getConnection();
         
         StringBuilder sql = new StringBuilder();
         sql.append("select *");
         sql.append(" from t_market, t_buy, t_user");
         sql.append(" where t_market.pro_no = t_buy.pro_no");
         sql.append(" and t_user.user_no = t_buy.user_no");
         sql.append(" and t_user.email = ?");
         
         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, email);
         
         ResultSet rs = pstmt.executeQuery();
         
         while(rs.next()) {
            int no = rs.getInt("pro_no");
            String cate = rs.getString("pro_cate");
            String name = rs.getString("pro_name");
            int price = rs.getInt("pro_price");
            int state = rs.getInt("state");
            String writer = rs.getString("email");
            int request = rs.getInt("request");
            String reg_date = rs.getString("reg_date");
            String owner = rs.getString("nickname");
            
            if(owner.equals(email)) {
               Item item = new Item(no, cate, name, price, state, owner, request, reg_date);
               itemlist.add(item);
            }
            
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBClose.close(pstmt, conn);
      }
      
      return itemlist;
   }
   
}