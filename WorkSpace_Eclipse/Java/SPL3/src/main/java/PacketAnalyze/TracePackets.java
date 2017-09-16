package PacketAnalyze;

import java.io.File;
import java.util.ArrayList;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Ip4;

public class TracePackets {
	
	public static void main(String[] args) {
		ArrayList<String> allPcapFiles= getPcapFiles("/home/ali/Education/rapidnet_v1.0/output");
		//ArrayList<String> allPcapFiles= getPcapFiles("/home/ali/Education/ns-allinone-3.26/ns-3.26/output");
			for(final String pcapfile: allPcapFiles){
		
			final StringBuilder errbuf = new StringBuilder();
		    
		    final Pcap pcap = Pcap.openOffline(pcapfile, errbuf);
		    if (pcap == null) {  
		        System.err.println(errbuf); // Error is stored in errbuf if any  
		        return;  
		    }	
		    pcap.loop(pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
		    	Ip4 ip = new Ip4(); 
		    	public void nextPacket(JPacket packet, StringBuilder errorbuff) {
		    		 if (packet.hasHeader(ip) == false) {  
						    return; // Not IP packet  
						  }  
			    	if(packet.getHeader(ip).id()==3){
			    	
							    		
			        System.out.println(pcapfile);
			        System.out.println(packet.getCaptureHeader().timestampInNanos());
						
		    		}
		    	
		    	}
		    },errbuf);
		}
	}

	

	
	public static ArrayList<String> getPcapFiles(String foldername) {
		ArrayList<String> filenames= new ArrayList<String>();
		final File folder = new File(foldername);
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isFile() && fileEntry.getAbsolutePath().contains("pcap"))
	           filenames.add(fileEntry.getAbsolutePath());
	        }
	    return  filenames;
	    }
		

}
