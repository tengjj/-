package login;

class Input implements Runnable{
  private Storage st;
  private int num;                   //定义一个变量num
  Input(Storage st){                 //通过构造方法接收一个Storage对象
	  this.st=st;
  }
  public void run() {
	  while(true) {
		  st.put(num++);            //将num存入数组，每次存入后num自加
	  }
  }
}
class Output implements Runnable{
	private Storage st;
	Output(Storage st){                 //通过构造方法接收一个Storage对象
		this.st=st;
	}
	public void run() {
		while (true) {
			st.get();                    //循环取出元素
		}
	}
}
