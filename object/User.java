package market.object;

import market.db.accessDB;
import market.util.InputUI;

public class User extends InputUI{
   private String email;
   private String nickname;
   private String password;
   protected accessDB db = new accessDB();
   
   public boolean checkUser() {      
      return db.checkUser(email, password);
   }
   
   public String getNickname(String email) {
      return db.getNickname(email);
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getNickname() {
      return nickname;
   }
   
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }   
   
}