

#include "ns3/core-module.h"
#include "ns3/simulator-module.h"
#include "ns3/node-module.h"
#include "ns3/bogonlist-module.h"
#include "ns3/rapidnet-module.h"
#include "ns3/values-module.h"

#define neighbour(src, next, cost) \
  tuple (Bogonlist::NEIGHBOUR, \
    attr ("neighbour_attr1", Ipv4Value, src), \
    attr ("neighbour_attr2", Ipv4Value, next), \
    attr ("neighbour_attr3", Int32Value, cost))


#define insertlink(from, to, cost) \
  app(from)->Insert (neighbour (addr (from), addr (to), cost)); \
  app(to)->Insert (neighbour (addr (to), addr (from), cost));



#define filter(src, next,cost) \
  tuple (Bogonlist::IMPORTFILTER, \
    attr ("importfilter_attr1", Ipv4Value, src), \
    attr ("importfilter_attr2", Ipv4Value, next),\
    attr ("importfilter_attr3", Int32Value, cost))


#define insertfilter(from, to,cost) \
  app(from)->Insert (filter (addr (from), addr (to), cost));


/*

#define insertlink(from, to, cost) \
  app(from)->Insert (link (addr (from), addr (to), cost)); \
  app(to)->Insert (link (addr (to), addr (from), cost));


#define deletelink(from, to, cost) \
  app(from)->Delete (link (addr (from), addr (to), cost)); \
  app(to)->Delete (link (addr (to), addr (from), cost));

*/



using namespace std;
using namespace ns3;
using namespace ns3::rapidnet;
using namespace ns3::rapidnet::bogonlist;

ApplicationContainer apps;
Ipv4InterfaceContainer interfaces;
void UpdateLinks2 ()
{
	insertfilter(1,5,2);
}

void
UpdateLinks1 ()
{
	 insertlink (1, 5, 2);

	 insertlink (1, 2, 5);
	 insertlink (2, 3, 3);
     insertlink (3, 4, 2);
      insertlink (4, 5, 6);
 }

ApplicationContainer InitApp(){
	Ptr<RapidNetApplicationHelper> appHelper= Create<BogonlistHelper> ();
	  NodeContainer csmaNodes;
	  csmaNodes.Create (5);

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


	  return appHelper->Install (csmaNodes);

}

int
main (int argc, char *argv[])
{
  LogComponentEnable("MytestHelper", LOG_LEVEL_INFO);
  LogComponentEnable("RapidNetApplicationBase", LOG_LEVEL_INFO);

  apps = InitApp();
  apps.Start (Seconds (0.0));
  apps.Stop (Seconds (20.0));

  schedule (0.0000, UpdateLinks1);

  schedule (5.0000, UpdateLinks2);

  //schedule (2.0000, Print);
  //schedule (4.0000, Print);

  Simulator::Run ();

  NS_LOG_UNCOND(
		  apps.Get(0)->GetNode()->GetDevice(0)->GetAddress());

  NS_LOG_UNCOND(
		  apps.Get(1)->GetNode()->GetDevice(0)->GetAddress());

  NS_LOG_UNCOND(
		  apps.Get(2)->GetNode()->GetDevice(0)->GetAddress());
  NS_LOG_UNCOND(interfaces.GetAddress (1));


  Simulator::Destroy ();
  return 0;
}

