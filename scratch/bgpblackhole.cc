
#include "ns3/core-module.h"
#include "ns3/simulator-module.h"
#include "ns3/node-module.h"
#include "ns3/bgpblackhole-module.h"
#include "ns3/rapidnet-module.h"
#include "ns3/values-module.h"

#define link(src, next, cost) \
  tuple (Bgpblackhole::LINK, \
    attr ("link_attr1", Ipv4Value, src), \
    attr ("link_attr2", Ipv4Value, next), \
    attr ("link_attr3", Int32Value, cost))




#define insertlink(from, to, cost) \
  app(from)->Insert (link (addr (from), addr (to), cost)); \
  app(to)->Insert (link (addr (to), addr (from), cost));

#define insertblackholelink(from,to, cost) \
  app(from)->Insert (link (addr(from),to, cost));



#define deletelink(from, to, cost) \
  app(from)->Delete (link (addr (from), addr (to), cost)); \
  app(to)->Delete (link (addr (to), addr (from), cost));




using namespace std;
using namespace ns3;
using namespace ns3::rapidnet;
using namespace ns3::rapidnet::bgpblackhole;

ApplicationContainer apps;
Ipv4InterfaceContainer interfaces;
void UpdateLinks2 ()
{
}

void
UpdateLinks1 ()
{

	 insertlink (1, 2, 5);
		  insertlink (2, 3, 3);
		  insertblackholelink(3,"10.1.1.4",3);

		  insertblackholelink(3,"10.1.1.5",3);

		  insertblackholelink(3,"10.1.1.6",3);

		  insertblackholelink(3,"10.1.1.7",3);


/*

  insertlink (1, 2, 5);
  insertlink (2, 3, 3);
  insertlink (3, 4, 3);
*/



  /*
  insertblackholelink(2,2);*/

/*  insertlink (3, 8, 5);
  insertlink (3, 4, 5);
  insertlink (4, 5, 5);
  insertlink (5, 6, 5);
  insertlink (6, 7, 5);
  insertlink (7, 9, 5);*/
  //insertlink (3, 4, 2);
//  insertlink (4, 5, 6);
}

ApplicationContainer InitApp(){
	Ptr<RapidNetApplicationHelper> appHelper= Create<BgpblackholeHelper> ();
	  NodeContainer csmaNodes;
	  csmaNodes.Create (4);

	  CsmaHelper csma;

	  NetDeviceContainer csmaDevices;
	  csmaDevices = csma.Install (csmaNodes);
	//*******************

	  //******************
	  InternetStackHelper stack;
	  stack.Install (csmaNodes);

	  Ipv4AddressHelper address;
	  address.SetBase ("10.1.1.0", DEFAULT_MASK);
	  interfaces = address.Assign (csmaDevices);

/*	  UdpEchoClientHelper echoClient (interfaces.GetAddress (2), 9);
	   echoClient.SetAttribute ("MaxPackets", UintegerValue (1));
	   echoClient.SetAttribute ("Interval", TimeValue (Seconds (1.0)));
	   echoClient.SetAttribute ("PacketSize", UintegerValue (1024));

	   Ptr<OutputStreamWrapper> routingStream = Create<OutputStreamWrapper> ("test.txt", std::ios::out);


	   ApplicationContainer clientApps = echoClient.Install (nodes.Get (0));
	   clientApps.Start (Seconds (2.0));
	   clientApps.Stop (Seconds (10.0));*/

	 // blackapps.Start(Seconds (0.0));
	 // blackapps.Stop(Seconds (0.0));
//Get(0)->GetNode()->GetDevice(0)->GetAddress()
	  CsmaHelper::EnablePcapAll ("test", true);
	  csmaNodes.Get(1)->GetDevice(0)->SetAddress(csmaNodes.Get(1)->GetDevice(0)->GetAddress());

	  return appHelper->Install (csmaNodes);

}

int
main (int argc, char *argv[])
{
  LogComponentEnable("MytestHelper", LOG_LEVEL_INFO);
  LogComponentEnable("RapidNetApplicationBase", LOG_LEVEL_INFO);

  apps = InitApp();
  apps.Start (Seconds (0.0));
  apps.Stop (Seconds (10.0));

  schedule (0.0000, UpdateLinks1);

  schedule (5.0000, UpdateLinks2);
  //schedule (2.0000, Print);
  //schedule (4.0000, Print);

  Simulator::Run ();
/*
  NS_LOG_UNCOND(
		  apps.Get(0)->GetNode()->GetDevice(0)->GetAddress());

  NS_LOG_UNCOND(
		  apps.Get(1)->GetNode()->GetDevice(0)->GetAddress());

  NS_LOG_UNCOND(
		  apps.Get(2)->GetNode()->GetDevice(0)->GetAddress());*/
  NS_LOG_UNCOND(
		  interfaces.GetAddress (1));


  Simulator::Destroy ();
  return 0;
}

