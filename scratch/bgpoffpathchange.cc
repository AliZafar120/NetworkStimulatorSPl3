
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
  PrintRelation (apps, Pathvector::PATH);
  PrintRelation (apps, Pathvector::PATHDELETE);
  PrintRelation (apps, Pathvector::PATHDELETE);
  PrintRelation (apps, Pathvector::R2LOCAL1R2LINKZSEND);
  PrintRelation (apps, Pathvector::R2LOCAL2PATHSEND);
  PrintRelation (apps, Pathvector::R2LINKZ);
  PrintRelation (apps, Pathvector::R2LINKZDELETE);
  PrintRelation (apps, Pathvector::LINK);
  PrintRelation (apps, Pathvector::BESTPATH);
}

void
UpdateLinks1 ()
{
  insertlink (1, 2, 5);
  insertlink (1, 3, 5);
  insertlink (3, 8, 5);
  insertlink (3, 4, 5);
  insertlink (4, 5, 5);
  insertlink (5, 6, 5);
  insertlink (6, 7, 5);
  insertlink (7, 9, 5);
  //insertlink (3, 4, 2);
//  insertlink (4, 5, 6);
}

void
UpdateLinks2 ()
{
	insertlink (8, 9, 5);
}

void
UpdateLinks3 ()
{

 // insertlink (1, 5, 3);
}

int
main (int argc, char *argv[])
{
  LogComponentEnable("Pathvector", LOG_LEVEL_INFO);
  LogComponentEnable("RapidNetApplicationBase", LOG_LEVEL_INFO);

  apps = InitRapidNetApps (9, Create<PathvectorHelper> ());

  apps.Start (Seconds (0.0));
  apps.Stop (Seconds (10.0));

  schedule (0.0001, UpdateLinks1);
  //schedule (2.0000, Print);
  //schedule (4.0000, Print);
  schedule (4.0001, UpdateLinks2);
 // schedule (6.0000, Print);

  Simulator::Run ();
  Simulator::Destroy ();
  return 0;
}

