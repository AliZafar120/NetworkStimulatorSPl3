package PacketAnalyze;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import FinalRapidnetOutputAnalyis.LogFormat;
import FinalRapidnetOutputAnalyis.Tuples.Attribute.TupleAttribute;
import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

public class TracePackets {
	static ArrayList<String> allPcapFiles= new ArrayList<String>();
	public static void main(String[] args) {

	    getPcapsFromFile("/home/ali/Education/rapidnet_v1.0/output");

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




    public static ArrayList<LogFormat> getPcapsFromFile(final String foldername) {
        final ArrayList<LogFormat> logs= new ArrayList<LogFormat>();
        final StringBuilder errbuf = new StringBuilder();

        allPcapFiles= getPcapFiles(foldername);
        for(final String pcapfile: allPcapFiles){



        final Pcap pcap = Pcap.openOffline(pcapfile, errbuf);
        if (pcap == null) {
            System.err.println(errbuf); // Error is stored in errbuf if any
            return null;
        }
        switch (pcap.loop(pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
            Ip4 ip = new Ip4();
            Tcp tcp = new Tcp();
            Icmp icmp = new Icmp();
            Udp udp = new Udp();
            Arp arp = new Arp();


            public void nextPacket(JPacket packet, StringBuilder errorbuff) {
               /* if (packet.hasHeader(ip) == false) {
                    return; // Not IP packet
                }*/

                if (packet.hasHeader(arp)) {
                    packet.getHeader(arp);
                    Tuple tuple= new Tuple();
                    tuple.type="Packet";
                    tuple.tupleSource=org.jnetpcap.packet.format.FormatUtils.ip(arp.spa());
                    tuple.tupleDestination=org.jnetpcap.packet.format.FormatUtils.ip(arp.tpa());
                    TupleAttribute attr= new TupleAttribute();
                    attr.tupleAttributeName="Protocol";
                    attr.tupleAttributeValue="ARP";
                    TupleAttribute attr1= new TupleAttribute();
                    attr1.tupleAttributeName="id";
                    attr1.tupleAttributeValue=packet.getHeader(arp).getId()+"";
                    TupleAttribute attr2= new TupleAttribute();
                    attr1.tupleAttributeName="operation";
                    attr1.tupleAttributeValue=arp.operation()+"";
                    TupleAttribute attr3= new TupleAttribute();
                    attr1.tupleAttributeName="Source Mac";
                    attr1.tupleAttributeValue=org.jnetpcap.packet.format.FormatUtils.mac(arp.sha())+"";
                    TupleAttribute attr4= new TupleAttribute();
                    attr1.tupleAttributeName="Destination Mac";
                    attr1.tupleAttributeValue=org.jnetpcap.packet.format.FormatUtils.mac(arp.tha())+"";
                    tuple.attributes.add(attr);
                    tuple.attributes.add(attr1);

                    LogFormat log = new LogFormat(tuple,1,"10.1.1."+pcapfile.split("-")[1],packet.getCaptureHeader().timestampInNanos()+"",null,1);
                    logs.add(log);

                }
                if (packet.hasHeader(udp) == true) {
                    byte[] sIP = new byte[4]; // Should be outside the callback method for efficiency
                    byte[] dIP = new byte[4];
                    sIP=packet.getHeader(ip).source();
                    dIP=packet.getHeader(ip).destination();


                    String sourceIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);
                    String destinationIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP)  ;

                    Tuple tuple= new Tuple();
                    tuple.type="Packet";
                    tuple.tupleSource=sourceIP;
                    tuple.tupleDestination=destinationIP;
                    TupleAttribute attr= new TupleAttribute();
                    attr.tupleAttributeName="Protocol";
                    attr.tupleAttributeValue="UDP";
                    TupleAttribute attr1= new TupleAttribute();
                    attr1.tupleAttributeName="id";
                    attr1.tupleAttributeValue=packet.getHeader(ip).id()+"";

                    tuple.attributes.add(attr);
                    tuple.attributes.add(attr1);

                    LogFormat log = new LogFormat(tuple,1,"10.1.1."+pcapfile.split("-")[1],packet.getCaptureHeader().timestampInNanos()+"",null,1);
                    logs.add(log);
                     // Not IP packet
                }

            }
        }, errbuf)) {
        }
        }

        return logs;
    }
}
