#include "ns3/core-module.h"
#include "ns3/simulator-module.h"
#include "ns3/node-module.h"
#include "ns3/mytest-module.h"
#include "ns3/rapidnet-module.h"
#include "ns3/values-module.h"

#define tlink(src, next) \
  tuple (Mytest::TLINK, \
    attr ("tLink_attr1", Ipv4Value, src), \
    attr ("tLink_attr2", Ipv4Value, next))

#define insertlink(from, to) \
  app(from)->Insert (tlink (addr (from), addr (to))); \
  app(to)->Insert (tlink (addr (to), addr (from)));

#define deletelink(from, to) \
  app(from)->Delete (tlink (addr (from), addr (to))); \
  app(to)->Delete (tlink (addr (to), addr (from)));

using namespace std;
using namespace ns3;
using namespace ns3::rapidnet;
using namespace ns3::rapidnet::mytest;

ApplicationContainer apps;

/** Create a 2 nodes */
void
UpdateLinks1 ()
{
  insertlink (1, 2);
}

int
main (int argc, char *argv[])
{
  LogComponentEnable("Mytest", LOG_LEVEL_INFO);
  LogComponentEnable("RapidNetApplicationBase", LOG_LEVEL_INFO);

  apps = InitRapidNetApps (2, Create<MytestHelper> ());
  apps.Start (Seconds (0.0));
  apps.Stop (Seconds (10.0));

  schedule (0.0001, UpdateLinks1);

  Simulator::Run ();
  Simulator::Destroy ();
  return 0;
}

