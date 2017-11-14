
#include "ns3/core-module.h"
#include "ns3/simulator-module.h"
#include "ns3/node-module.h"
#include "ns3/pathvector-module.h"
#include "ns3/rapidnet-module.h"
#include "ns3/values-module.h"

#define link(src, next, cost) \
  tuple (Pathvector::LINK, \
    attr ("link_attr1", Ipv4Value, src), \
    attr ("link_attr2", Ipv4Value, next), \
    attr ("link_attr3", Int32Value, cost))


#define insertlink(from, to, cost) \
  app(from)->Insert (link (addr (from), addr (to), cost)); \
  app(to)->Insert (link (addr (to), addr (from), cost));

#define insertblackholelink(from, cost) \
  app(from)->Insert (link (addr(from),"10.1.2.1", cost));

#define deletelink(from, to, cost) \
  app(from)->Delete (link (addr (from), addr (to), cost)); \
  app(to)->Delete (link (addr (to), addr (from), cost));




using namespace std;
using namespace ns3;
using namespace ns3::rapidnet;
using namespace ns3::rapidnet::pathvector;

ApplicationContainer apps;

void Print ()
{

}

void
UpdateLinks1 ()
{
  insertlink (1, 3, 5);
  insertlink (3, 4, 3);



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

void
UpdateLinks2 ()
{

	  insertlink (1, 2, 5);
	  insertlink (2, 3, 3);
}


//mac spoofed
void
UpdateLinks3 ()
{
	apps.Get(1)->GetNode()->GetDevice(0)->SetAddress(apps.Get(2)->GetNode()->GetDevice(0)->GetAddress());

 // insertlink (1, 5, 3);
}

ApplicationContainer InitApp(){
	Ptr<RapidNetApplicationHelper> appHelper= Create<PathvectorHelper> ();
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
	  address.Assign (csmaDevices);

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
  //schedule (2.0000, Print);
  //schedule (4.0000, Print);
  schedule (4.0001, UpdateLinks2);
  schedule (4.00011, UpdateLinks3);
 // schedule (6.0000, Print);

  Simulator::Run ();

  NS_LOG_UNCOND(
		  apps.Get(0)->GetNode()->GetDevice(0)->GetAddress());

  NS_LOG_UNCOND(
		  apps.Get(1)->GetNode()->GetDevice(0)->GetAddress());

  NS_LOG_UNCOND(
		  apps.Get(2)->GetNode()->GetDevice(0)->GetAddress());


  Simulator::Destroy ();
  return 0;
}

