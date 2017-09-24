package firstpage;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.RepaintManager;
 
/**
 *
 * @author User
 * 
 */

public class PrintSupport implements Printable {
	
    private Component print_component;
   
    
    public static void printComponent(Component c) {
        new PrintSupport(c).doPrint();
    }
 
    public PrintSupport(Component comp) {
        this.print_component = comp;
    }
 
    public Object doPrint() 
    {
    	PrinterJob printJob = PrinterJob.getPrinterJob();
    	PageFormat pf = printJob.defaultPage();
		double margin = 1.0;
		Paper paper = new Paper();
		
		double middleHeight=12.0;
		double headerHeight=2.0;
		double footerHeight=3.0;
		
		double width=convert_CM_TO_PPI(12.0);
		double height=convert_CM_TO_PPI(middleHeight + headerHeight + footerHeight);
		
		paper.setSize(width,height);
		paper.setImageableArea(0,10,width,height-convert_CM_TO_PPI(1.0));
		pf.setPaper(paper);
		pf.setOrientation(PageFormat.PORTRAIT);
		printJob.setPrintable(this, pf);
        //printJob.setPrintable(this);
        if (printJob.printDialog()) 
        {
            try {
                printJob.print();
            } catch (PrinterException pe) {
                System.out.println("Error printing: " + pe);
            }
            
        }
		return pf;
       
       /* double toPPI(double inch)
        {
        	return ;
        }*/
    }
    double convert_CM_TO_PPI(double cm)
    {
    	return (double) toPPI(cm * 0.393600787);
    }
 
    private Object toPPI(double d) {
		// TODO Auto-generated method stub
		return d*72d;
	}

	@Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        if (pageIndex > 0) {
            return (NO_SUCH_PAGE);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            disableDoubleBuffering(print_component);
            print_component.paint(g2d);
            enableDoubleBuffering(print_component);
            return (PAGE_EXISTS);
        }
    }
 
    public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }
 
    public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }
}
