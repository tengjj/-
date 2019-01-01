package login;
import java.util.Random;
class Storage {
	  private int [] celss = new int[10];
	  private int inPos,outPos;                          //inPos表示存入时数组下标，outPos表示取出时数组下标
	  private int count;
	  public synchronized void put (int num) {
		  try {
			  while(count==celss.length) {              //若果放入的数据等于cells的长度，此线程等待
				  this.wait();
			  }
			  Random r=new Random();                    //实例化r
			  for(int i=0;i<celss.length;i++) {          //产生随机数
				  int random = (r.nextInt(100));
				  celss[outPos]=random;                  //向数组中放入随机数
			  }
			  System.out.println("用户"+celss[inPos]+"登录系统！");
			  inPos++;                                   //存完元素让位置+1
			  if(inPos==celss.length)                    //当inPos位数组长度时，将其置为0
				  inPos=0; 
			  count++;                                   //每放入一个数据count+1
			  System.out.println("当前在线人数为："+count+"人");
			  this.notify();
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
	  public synchronized void get() {
		  try {
			  while(count==0) {                            //如果count=0，线程在此等待
				  this.wait();
			  }
			  int date=celss[outPos];                      //从数组中取出数据
			  System.out.println("celss["+outPos+"]用户"+ date +"注销");
			  celss[outPos]=0;                             //取出后当前位置为0
			  outPos++;                                    //取完元素让位置+1
			  if(outPos==celss.length)                     //从celss[9]取完后，从celss[0]开始
				  outPos=0;
			  count--;                                     //每取完一个数据，count-1
			  this.notify();
			  System.out.println("当前在线人数为："+count+"人");
		  }catch(Exception e) {
			  e.printStackTrace();
		  }

     }
}