package market.object;

public class Item {
   private int no;
   private String cate; // 카테고리(상품종류)
   private String name;
   private int price;
   private int state;
   private String writer;
   private int request;
   private String reg_date;
   private String owner;
   
   public Item() {
      
   }
   
   public Item(int no, String cate, String name, int price, int state, String writer, int request, String reg_date) {
      super();
      this.no = no;
      this.cate = cate;
      this.name = name;
      this.price = price;
      this.state = state;
      this.writer = writer;
      this.request = request;
      this.reg_date = reg_date;
   }
   
   public Item(int no, String cate, String name, int price, int state, String writer, int request, String reg_date,
         String owner) {
      super();
      this.no = no;
      this.cate = cate;
      this.name = name;
      this.price = price;
      this.state = state;
      this.writer = writer;
      this.request = request;
      this.reg_date = reg_date;
      this.owner = owner;
   }

   public int getNo() {
      return no;
   }

   public void setNo(int no) {
      this.no = no;
   }

   public String getCate() {
      return cate;
   }

   public void setCate(String cate) {
      this.cate = cate;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public String getWriter() {
      return writer;
   }

   public void setWriter(String writer) {
      this.writer = writer;
   }

   public int getRequest() {
      return request;
   }

   public void setRequest(int request) {
      this.request = request;
   }

   public String getReg_date() {
      return reg_date;
   }

   public void setReg_date(String reg_date) {
      this.reg_date = reg_date;
   }

   public String getOwner() {
      return owner;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }

   @Override
   public String toString() {
      return "item [no=" + no + ", cate=" + cate + ", name=" + name + ", price=" + price + ", state=" + state
            + ", writer=" + writer + ", request=" + request + ", reg_date=" + reg_date + ", owner=" + owner + "]";
   }

}