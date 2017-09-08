#include "ns3/core-module.h"
#include "ns3/simulator-module.h"
#include "ns3/node-module.h"
#include "ns3/rapidnet-module.h"
#include "ns3/values-module.h"
#include "ns3/helper-module.h"
#include "ns3/myproto2-module.h"
#include "ns3/myproto-query-module.h"
#include <time.h>

#define link(src, next, cost) \
  tuple (Myproto2::LINK, \
    attr ("link_attr1", Ipv4Value, src), \
    attr ("link_attr2", Ipv4Value, next), \
         attr ("link_attr3", Int32Value, cost))

#define insertlink(from, to, cost) \
  app(from)->Insert (link (addr (from), addr (to), cost)); \
  app(to)->Insert (link (addr (to), addr (from), cost));

#define deletelink(from, to, cost) \
  app(from)->Delete (link (addr (from), addr (to), cost)); \
  app(to)->Delete (link (addr (to), addr (from), cost));

//define the tuple you would like to query and how to insert it
#define tupleQuery(loc, name, attr1, attr2, attr3)         \
  tuple (MyprotoQuery::TUPLE, \
    attr ("tuple_attr1", Ipv4Value, loc), \
    attr ("tuple_attr2", StrValue, name), \
    attr ("tuple_attr3", Ipv4Value, attr1), \
    attr ("tuple_attr4", Ipv4Value, attr2), \
         attr ("tuple_attr5", Int32Value, attr3))

#define inserttuple(loc, name, attr1, attr2, attr3)     \
  queryNode->Insert (tupleQuery(queryNode->GetAddress(), name, addr(attr1), addr(attr2), attr3));


using namespace std;
using namespace ns3;
using namespace ns3::rapidnet;
using namespace ns3::rapidnet::myproto2;
using namespace ns3::rapidnet::myprotoquery;

ApplicationContainer apps;
ApplicationContainer queryapps;

void
initApps()
{
  NodeContainer mainAppNodes;
  mainAppNodes.Create (3);

  NodeContainer queryAppNodes;
  queryAppNodes.Create (1);

  NodeContainer csmaNodes;
  csmaNodes.Add(mainAppNodes);
  csmaNodes.Add(queryAppNodes);

  CsmaHelper csma;

  NetDeviceContainer csmaDevices;
  csmaDevices = csma.Install (csmaNodes);

  InternetStackHelper stack;
  stack.Install (csmaNodes);

  Ipv4AddressHelper address;
  Ipv4Address base = "10.1.1.0";

  address.SetBase (base, "255.255.255.0");
  address.Assign (csmaDevices);
  apps.Add(Create<Myproto2Helper>()->Install(mainAppNodes));
  queryapps.Add(Create<MyprotoQueryHelper>()->Install(queryAppNodes));

  SetMaxJitter (apps, 0.001);
  SetMaxJitter (queryapps, 0.001);
}

void
UpdateLinks1 ()
{
  insertlink (1, 2, 3);
  insertlink (2, 3, 2);
  insertlink (1, 3, 5);
}

void
TupleToQuery ()
{
  Ptr<RapidNetApplicationBase> queryNode = queryapps.Get(0)->GetObject<RapidNetApplicationBase>();
  inserttuple(1, "bestPath", 3, 2, 2);
  inserttuple(1, "path", 1, 3, 5);
  inserttuple(1, "link", 1, 2, 3);
}

void
Print ()
{
  PrintRelation (apps, Myproto2::BESTPATH);
  PrintRelation (apps, Myproto2::PROV);
  PrintRelation (apps, Myproto2::RULEEXEC);

  PrintRelation (queryapps, MyprotoQuery::TUPLE);
  PrintRelation (queryapps, MyprotoQuery::RECORDS);
}

int
main (int argc, char *argv[])
{
// LogComponentEnable("Myproto2", LOG_LEVEL_INFO);
  //LogComponentEnable("MyprotoQuery", LOG_LEVEL_INFO);
 LogComponentEnable("RapidNetApplicationBase", LOG_LEVEL_INFO);

  initApps();

  apps.Start (Seconds (0.0));
  apps.Stop (Seconds (5.0));
  queryapps.Start (Seconds (0.0));
  queryapps.Stop (Seconds (5.0));

  schedule (1.0, TupleToQuery);
  schedule (2.0, UpdateLinks1);
  schedule (5.0, Print);

  Simulator::Run ();
  Simulator::Destroy ();

  return 0;
}
