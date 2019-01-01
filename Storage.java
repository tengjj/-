package login;
import java.util.Random;
class Storage {
	  private int [] celss = new int[10];
	  private int inPos,outPos;                          //inPos��ʾ����ʱ�����±꣬outPos��ʾȡ��ʱ�����±�
	  private int count;
	  public synchronized void put (int num) {
		  try {
			  while(count==celss.length) {              //������������ݵ���cells�ĳ��ȣ����̵߳ȴ�
				  this.wait();
			  }
			  Random r=new Random();                    //ʵ����r
			  for(int i=0;i<celss.length;i++) {          //���������
				  int random = (r.nextInt(100));
				  celss[outPos]=random;                  //�������з��������
			  }
			  System.out.println("�û�"+celss[inPos]+"��¼ϵͳ��");
			  inPos++;                                   //����Ԫ����λ��+1
			  if(inPos==celss.length)                    //��inPosλ���鳤��ʱ��������Ϊ0
				  inPos=0; 
			  count++;                                   //ÿ����һ������count+1
			  System.out.println("��ǰ��������Ϊ��"+count+"��");
			  this.notify();
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	  }
	  public synchronized void get() {
		  try {
			  while(count==0) {                            //���count=0���߳��ڴ˵ȴ�
				  this.wait();
			  }
			  int date=celss[outPos];                      //��������ȡ������
			  System.out.println("celss["+outPos+"]�û�"+ date +"ע��");
			  celss[outPos]=0;                             //ȡ����ǰλ��Ϊ0
			  outPos++;                                    //ȡ��Ԫ����λ��+1
			  if(outPos==celss.length)                     //��celss[9]ȡ��󣬴�celss[0]��ʼ
				  outPos=0;
			  count--;                                     //ÿȡ��һ�����ݣ�count-1
			  this.notify();
			  System.out.println("��ǰ��������Ϊ��"+count+"��");
		  }catch(Exception e) {
			  e.printStackTrace();
		  }

     }
}