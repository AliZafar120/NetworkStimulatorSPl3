package PacketAnalyze;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
//import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JFlow;  
import org.jnetpcap.packet.JFlowKey;  
import org.jnetpcap.packet.JFlowMap;  
import org.jnetpcap.packet.JHeader;
import org.jnetpcap.packet.JHeaderPool;
import org.jnetpcap.packet.JPacket;  
import org.jnetpcap.packet.JPacketHandler;  
import org.jnetpcap.packet.JScanner;  
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;  
import org.jnetpcap.packet.annotate.Header;
import org.jnetpcap.packet.structure.AnnotatedHeader;
import org.jnetpcap.packet.structure.JField;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;  
import org.jnetpcap.protocol.tcpip.Tcp;  
import org.jnetpcap.protocol.tcpip.Udp;

import static org.jnetpcap.packet.format.FormatUtils.asString;

public class PcapAnalysis {
	public static void main(String[] args) throws IOException {

		final String FILENAME = "/home/ali/Workspace/Java/SPL3/test-0-0.pcap";  
	    final StringBuilder errbuf = new StringBuilder();
	    
	    final Pcap pcap = Pcap.openOffline(FILENAME, errbuf);
	    if (pcap == null) {  
	        System.err.println(errbuf); // Error is stored in errbuf if any  
	        return;  
	    }
		List<PcapIf> alldevs = new ArrayList<PcapIf>();
		Pcap.findAllDevs(alldevs, errbuf);
	    
	    pcap.loop(pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {

	    	 final Tcp tcp = new Tcp();  
	    	 final Http http = new Http();
	    	 final Icmp icmp =new Icmp();
	    	 final Udp udp =new Udp();
	    	 final Arp arp= new Arp();
	 	    
	    	
	    	
		//	@Override
			public void nextPacket(JPacket packet, StringBuilder errorbuff) {
				// TODO Auto-generated method stub
				
				
				 if (packet.hasHeader(udp)) {  
					 //System.out.println(udp.getDescription());

					 //System.out.println(udp.length());
					 
					 System.out.println(packet.getFrameNumber());
//udp=packet.get;
					 //System.out.printf("%d%n",udp.destination());
						 //System.out.println(udp.length());
					 
					//System.out.println("Got a Udp packet at "+i);
				 }
				 Ip4 ip = new Ip4(); // Should be outside the callback method for efficiency  
				  if (packet.hasHeader(ip) == false) {  
				    return; // Not IP packet  
				  }  
				  
				  byte[] sIP = new byte[4]; // Should be outside the callback method for efficiency  
				  byte[] dIP = new byte[4];  
				  sIP=packet.getHeader(ip).source();
				  dIP=packet.getHeader(ip).destination();

				  
				  String sourceIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);  
				  String destinationIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP)  ;
				  System.out.println("Source :"+sourceIP);
				  System.out.println("Destination :"+destinationIP);
				  System.out.println("Source port "+ packet.getHeader(udp).source());
				  System.out.println("Destination port "+ packet.getHeader(udp).destination());
				  System.out.println("ipid "+packet.getHeader(ip).id());
			
				/*  System.out.println("index "+packet.getHeader(ip).getIndex());
				  System.out.println("name "+packet.getHeader(ip).getName());
				  System.out.println("length "+packet.getHeader(ip).getLength());
				  System.out.println("index "+packet.getHeader(ip).getIndex());*/
					 					 

				  //	  String strPayloadContent  =org.jnetpcap.packet.format.FormatUtils.hexdump(packet.getHeader(udp).getPayload()); // offset, length
				 // String strPayloadContent  =new String( org.jnetpcap.packet.format.FormatUtils.asString(packet.getHeader(udp).getPayload())); // offset, length
				  String strPayloadContent  =new String( asString(packet.getHeader(udp).getPayload())); // offset, length
			
			
				  System.out.println("Payload : "+strPayloadContent);
				 
				  
				  System.out.println(packet.getCaptureHeader().timestampInMicros());
				/*  
				  final JHeaderPool headers = new JHeaderPool();  
				  final int count = packet.getHeaderCount();  
				  for (int i = 0; i < count; i++) {  
				    final int id = packet.getHeaderIdByIndex(i); // Numerical ID of the header  
				    final JHeader header = headers.getHeader(id);  
				    final String name = header.getName();  
				    final String nicname = header.getNicname();  
				    final String description = header.getDescription();  
				    final JField[] fields = header.getFields();  
				    final AnnotatedHeader annotatedHeader = header.getAnnotatedHeader(); // Annotation  
				    System.out.println("name "+name+" nickname "+nicname+" description"+description+ " fields "+ fields+ " annotateheader "+ annotatedHeader);
				  }  */
				 
				/* 
				 if (packet.hasHeader(Tcp.ID)) {  
						
						System.out.println("Got a Tcp packet at "+packet.getFrameNumber());
						System.out.println(packet.getHeaderCount());
						
						System.out.println(packet.getPacketWirelen());

						System.out.println(packet.getState());

						//System.out.println(packet);
					 }
				 */
				 //ICMP and ARP is currently out of Scope of project to investigate
				/* if (packet.hasHeader(icmp.ID)) {  
					 
				 System.out.println("Got a icmp packet at ");
				 }
				 if(packet.hasHeader(arp))  {
					 //in cases of ARP requests
			
				 }*/
				
				
			}  
	    	
	    	
	    }, errbuf);




}
	
	public static String convert(byte[] data) {
	    StringBuilder sb = new StringBuilder(data.length);
	    for (int i = 0; i < data.length; ++ i) {
	        if (data[i] < 0) throw new IllegalArgumentException();
	        sb.append((char) data[i]);
	    }
	    return sb.toString();
	}

}
