package market.object;

public class Login extends User {

   private static Login instance = new Login();
   private Boolean login = false;

   private Login() {

   }

   public static Login getInstance() {
      return instance;
   }

   public void login() {

      inputEmail();
      if (!getEmail().equalsIgnoreCase("q")) {
         inputPassword();
         
         if (!getPassword().equalsIgnoreCase("q")) {
            
            if (checkUser()) { // 존재하는 이메일,패스워드면 break
               setNickname(getNickname(getEmail()));
               login = true;
               System.out.println("로그인 되었습니다.");
             } else {
                System.out.println("email과 패스워드가 일치하지 않습니다.");                
             }
         }
      }
   }

   private void inputEmail() {
      while (true) {
         setEmail(scanStr("이메일을 입력하세요 : "));
         if (db.checkEmail(getEmail())) // 이메일이 존재하면 break
            break;
         if (getEmail().equalsIgnoreCase("q"))
            break;
         System.out.println("존재하지 않는 이메일입니다.");
      }
   }

   private void inputPassword() {
//      while (true) {
         setPassword(scanStr("비밀번호를 입력하세요 : "));
         
//         if (getPassword().equalsIgnoreCase("q"))
//            break;
//      }
   }

   public void logout() {
      login = false;
   }

   public Boolean getLogin() {
      return login;
   }

   public void setLogin(Boolean login) {
      this.login = login;
   }

}