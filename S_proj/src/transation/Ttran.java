package transation;
import javax.swing.JTextArea;

import org.neo4j.graphdb.GraphDatabaseService;


public class Ttran {

	public void run(JTextArea textArea)
	{
		transation tran =new transation();
		
		int start=1;
		int end=1000;
		int flg;
		long starttime=System.currentTimeMillis();
		
		   while(true)
		    {
			   textArea.setText(null);
		     flg=tran.Odata(start,end);

		     if(flg==0)
		      {
			   System.out.println("transfer done!");
			  // System.exit(0);
			   break;
		      }
		     tran.Ndata();
		     start=end+1;
		     end=start+999;
		     long time = System.currentTimeMillis() - starttime;
		     System.out.println("running time= "+time+" milliseconds");
		     textArea.paintImmediately(textArea.getBounds());
		     textArea.setCaretPosition(textArea.getText().length());
		     
		   }
	
	}
}
