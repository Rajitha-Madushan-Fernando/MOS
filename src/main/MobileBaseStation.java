package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.labels.BubbleXYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;  
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYZDataset;  
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.Layer; 

public class MobileBaseStation extends JFrame {
	XYZDataset dataset;
	private ArrayList<SmartPhoneData> sp;		
	private ArrayList<IntersectPoints> ip;		
	private Intersect in;						
	private ArrayList<IntersectPoints> ip2; 
	private float xA, yB;					
	
	MobileBaseStation() {
		sp = new ArrayList<>();
		ip = new ArrayList<>();
		ip2 = new ArrayList<>();
				
		createData();
		createChart();
	}
	
	public void createData( ) {					//X			//Y			//Radius
		SmartPhoneData data1 = new SmartPhoneData(1450		, 1040		, 510);
		SmartPhoneData data2 = new SmartPhoneData(936		, 752		, 80);
		SmartPhoneData data3 = new SmartPhoneData(850		, 600		, 250);
		SmartPhoneData data4 = new SmartPhoneData(827.2f	, 850.4f	, 180);
		SmartPhoneData data5 = new SmartPhoneData(904		, 728		, 120);
		SmartPhoneData data6 = new SmartPhoneData(1468f		, 2716.8f	, 343.2f);
		SmartPhoneData data7 = new SmartPhoneData(219.2f	, 2000		, 580.8f);
		SmartPhoneData data8 = new SmartPhoneData(339.2f	, 2614.4f	, 602.4f);
		SmartPhoneData data9 = new SmartPhoneData(784		, 1262		, 510);
		SmartPhoneData data10 = new SmartPhoneData(1720		, 2050		, 370);
		SmartPhoneData data11 = new SmartPhoneData(1720		, 2050		, 530);
		SmartPhoneData data12 = new SmartPhoneData(1984		, 2313.6f	, 393.6f);
		SmartPhoneData data13 = new SmartPhoneData(2216		, 2118.4f	, 561.5f);
		SmartPhoneData data14 = new SmartPhoneData(800		, 2434.4f	, 434.4f);
		SmartPhoneData data15 = new SmartPhoneData(161.6f	, 2748.8f	, 602.4f);
		
		addSmartPhoneData(data1);
		addSmartPhoneData(data2);
		addSmartPhoneData(data3);
		addSmartPhoneData(data4);
		addSmartPhoneData(data5);
		addSmartPhoneData(data6);
		addSmartPhoneData(data7);
		addSmartPhoneData(data8);
		addSmartPhoneData(data9);
		addSmartPhoneData(data10);
		addSmartPhoneData(data11);
		addSmartPhoneData(data12);
		addSmartPhoneData(data13);
		addSmartPhoneData(data14);
		addSmartPhoneData(data15);
	}
	
	//check intersections
	public void intersect() {
		ArrayList<SmartPhoneData> sp2 = sp;
		for (int i = 0; i < sp2.size(); i ++) {
			for (int j = i + 1; j < sp2.size(); j ++) {
			    in = new Intersect(sp.get(i), sp.get(j));
				if(in.f) {
					ip.add(in.getPoints());
					System.out.print("(" + ip.get(i).getX1() + "," + ip.get(i).getY1() + ")");
					System.out.print("(" + ip.get(i).getX2() + "," + ip.get(i).getY2() + ")");
					System.out.println("");
				}
			}
		}
	}
	
	//removing false stations
	public void removeFalseStations() {
		float distance = 0;
		int c = 0;
		for (int i = 0; i < sp.size(); i ++) {
			for (int j = 0; j < ip.size(); j ++) {
				c ++;
				float x1 = ip.get(j).getX1();
				float y1 = ip.get(j).getY1();
				float x2 = ip.get(j).getX2();
				float y2 = ip.get(j).getY2();
				
				int r = 600;
			
				distance = in.calcDistance(sp.get(i).getXCoordinates(), sp.get(i).getYCoordinates(), x1, y1);
				if(distance < r) {
					if(distance == sp.get(i).getRadius()) {
						IntersectPoints ipt = new IntersectPoints(x1, y1);
						if(ip2.contains(ipt) == false) {
							ip2.add(ipt);
						}
					}
				}
				
				distance = in.calcDistance(sp.get(i).getXCoordinates(), sp.get(i).getYCoordinates(), x2, y2);
				if(distance < r) {
					if(distance == sp.get(i).getRadius()) {
						IntersectPoints ipt = new IntersectPoints(x2, y2);
						if(ip2.contains(ipt) == false) {
							ip2.add(ipt);
						}
					}
				}
			}
												
		}	
	
		//To plot true base stations
		createChart();	
	
	}
	
	public void addSmartPhoneData(SmartPhoneData spd) {
		sp.add(spd);
	}
	
	public int getSmartPhoneDataCount() {
		return sp.size();
	}
	
	 private XYZDataset createDataset() {
		return dataset;  
	} 
	 
	 //to plot graph
	 private void createChart() {
		    dataset = createDataset();  
		 // Create chart  
		    JFreeChart chart = ChartFactory.createBubbleChart("Data",  "X-Values",   "Y-Values", dataset, PlotOrientation.HORIZONTAL , rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled);  
		    
		    // Set range for X-Axis  
		    XYPlot plot = chart.getXYPlot();
		    
		    NumberAxis domain = (NumberAxis) plot.getDomainAxis();  
		    domain.setRange(-500, 3500);  
		  
		    // Set range for Y-Axis  
		    NumberAxis range = (NumberAxis) plot.getRangeAxis();  
		    range.setRange(0, 3500);   
			
		  //Format label  
		    XYBubbleRenderer renderer=(XYBubbleRenderer)plot.getRenderer();
		    BasicStroke stroke = new BasicStroke(1.5f);
			//data = createDataset();
			
		    for(SmartPhoneData s : sp) {
		    	float x = s.getXCoordinates();
		    	float y = s.getYCoordinates();
		    	float diameter = s.getDiameter();
		    	float radius = s.getRadius();
		    	
		    	 Ellipse2D.Double circle = new Ellipse2D.Double(x - radius, y - radius, diameter, diameter);
		    	 Ellipse2D.Double circle2 = new Ellipse2D.Double(x - 5, y - 5, 10, 10);
		    	 
		    	 renderer.addAnnotation(new XYShapeAnnotation(
		                 circle, stroke, Color.BLUE), Layer.BACKGROUND);
		    	 renderer.addAnnotation(new XYShapeAnnotation(
		                 circle2, stroke, Color.BLACK), Layer.BACKGROUND);
		    }
		    
		    for(IntersectPoints i: ip2) {
		    	xA = i.getX1();
		    	yB = i.getY1();
		    	BasicStroke stroke1 = new BasicStroke(3.5f);
			
		    		Ellipse2D.Double circle3 = new Ellipse2D.Double(xA-10, yB-10, 20, 20);
			    	
			    	renderer.addAnnotation(new XYShapeAnnotation(
			                 circle3, stroke1, Color.RED), Layer.BACKGROUND); 
			    	XYTextAnnotation xyt = new XYTextAnnotation("(" + xA + "," + yB + ")", (int) xA + 250, (int) yB - 50);
			    	xyt.setPaint(Color.RED);
			    	renderer.addAnnotation(xyt);
		    	}
		       
		    // Create Panel  
		    ChartPanel panel = new ChartPanel(chart);  
		    setContentPane(panel);  
		    
		    plot.setBackgroundPaint(Color.WHITE);
		    chart.setBackgroundPaint(Color.WHITE);
		    panel.setBackground(Color.WHITE);  
	 }
	
	 public static void main(String args[]) {
		 SwingUtilities.invokeLater(() -> {  
			 MobileBaseStation mbs= new MobileBaseStation();  
		 
		      mbs.setSize(800, 700);  
		      mbs.setLocationRelativeTo(null);  
		      mbs.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		      mbs.setVisible(true);
		      mbs.intersect();
		      mbs.removeFalseStations();
		 });
	 }
}
