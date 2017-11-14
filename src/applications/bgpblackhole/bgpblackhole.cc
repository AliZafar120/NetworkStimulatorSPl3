/* A RapidNet application. Generated by RapidNet compiler. */

#include "bgpblackhole.h"
#include <cstdlib>
#include "ns3/nstime.h"
#include "ns3/simulator.h"
#include "ns3/type-ids.h"
#include "ns3/rapidnet-types.h"
#include "ns3/rapidnet-utils.h"
#include "ns3/assignor.h"
#include "ns3/selector.h"
#include "ns3/rapidnet-functions.h"

using namespace std;
using namespace ns3;
using namespace ns3::rapidnet;
using namespace ns3::rapidnet::bgpblackhole;

const string Bgpblackhole::BESTPATH = "bestPath";
const string Bgpblackhole::LINK = "link";
const string Bgpblackhole::PACKET = "packet";
const string Bgpblackhole::PATH = "path";
const string Bgpblackhole::PATHDELETE = "pathDelete";
const string Bgpblackhole::PERIODIC = "periodic";
const string Bgpblackhole::R2LOCAL1R2LINKZSEND = "r2Local1r2linkZsend";
const string Bgpblackhole::R2LOCAL2PATHSEND = "r2Local2pathsend";
const string Bgpblackhole::R2LINKZ = "r2linkZ";
const string Bgpblackhole::R2LINKZDELETE = "r2linkZDelete";
const string Bgpblackhole::R4_ECAPERIODIC = "r4_ecaperiodic";

NS_LOG_COMPONENT_DEFINE ("Bgpblackhole");
NS_OBJECT_ENSURE_REGISTERED (Bgpblackhole);

TypeId
Bgpblackhole::GetTypeId (void)
{
  static TypeId tid = TypeId ("ns3::rapidnet::bgpblackhole::Bgpblackhole")
    .SetParent<RapidNetApplicationBase> ()
    .AddConstructor<Bgpblackhole> ()
    ;
  return tid;
}

Bgpblackhole::Bgpblackhole()
{
  NS_LOG_FUNCTION_NOARGS ();
}

Bgpblackhole::~Bgpblackhole()
{
  NS_LOG_FUNCTION_NOARGS ();
}

void
Bgpblackhole::DoDispose (void)
{
  NS_LOG_FUNCTION_NOARGS ();

  RapidNetApplicationBase::DoDispose ();
}

void
Bgpblackhole::StartApplication (void)
{
  NS_LOG_FUNCTION_NOARGS ();

  RapidNetApplicationBase::StartApplication ();
  m_event_r4_ecaperiodic=
    Simulator::Schedule (Seconds (0), &Bgpblackhole::R4_ecaperiodic, this);
  RAPIDNET_LOG_INFO("Bgpblackhole Application Started");
}

void
Bgpblackhole::StopApplication ()
{
  NS_LOG_FUNCTION_NOARGS ();

  RapidNetApplicationBase::StopApplication ();
  Simulator::Cancel(m_event_r4_ecaperiodic);
  RAPIDNET_LOG_INFO("Bgpblackhole Application Stopped");
}

void
Bgpblackhole::InitDatabase ()
{
  //RapidNetApplicationBase::InitDatabase ();

  AddRelationWithKeys (BESTPATH, attrdeflist (
    attrdef ("bestPath_attr2", IPV4)));

  AddRelationWithKeys (LINK, attrdeflist (
    attrdef ("link_attr1", IPV4),
    attrdef ("link_attr2", IPV4)));

  AddRelationWithKeys (PATH, attrdeflist (
    attrdef ("path_attr4", LIST)));

  AddRelationWithKeys (R2LINKZ, attrdeflist (
    attrdef ("r2linkZ_attr1", IPV4),
    attrdef ("r2linkZ_attr2", IPV4)));

  m_aggr_bestpathMinC = AggrMin::New (BESTPATH,
    this,
    attrdeflist (
      attrdeftype ("bestPath_attr1", ANYTYPE),
      attrdeftype ("bestPath_attr2", ANYTYPE),
      attrdeftype ("bestPath_attr3", ANYTYPE),
      attrdeftype ("bestPath_attr4", ANYTYPE)),
    3);

}

void
Bgpblackhole::DemuxRecv (Ptr<Tuple> tuple)
{
  RapidNetApplicationBase::DemuxRecv (tuple);

  if (IsInsertEvent (tuple, LINK))
    {
      R1Eca0Ins (tuple);
    }
  if (IsDeleteEvent (tuple, LINK))
    {
      R1Eca0Del (tuple);
    }
  if (IsRecvEvent (tuple, R2LOCAL1R2LINKZSEND))
    {
      R2Local1Eca0RemoteIns (tuple);
    }
  if (IsRecvEvent (tuple, R2LINKZDELETE))
    {
      R2Local1Eca0RemoteDel (tuple);
    }
  if (IsInsertEvent (tuple, LINK))
    {
      R2Local1Eca0Ins (tuple);
    }
  if (IsDeleteEvent (tuple, LINK))
    {
      R2Local1Eca0Del (tuple);
    }
  if (IsRecvEvent (tuple, R2LOCAL2PATHSEND))
    {
      R2Local2Eca0RemoteIns (tuple);
    }
  if (IsRecvEvent (tuple, PATHDELETE))
    {
      R2Local2Eca0RemoteDel (tuple);
    }
  if (IsInsertEvent (tuple, R2LINKZ))
    {
      R2Local2Eca0Ins (tuple);
    }
  if (IsDeleteEvent (tuple, R2LINKZ))
    {
      R2Local2Eca0Del (tuple);
    }
  if (IsInsertEvent (tuple, BESTPATH))
    {
      R2Local2Eca1Ins (tuple);
    }
  if (IsDeleteEvent (tuple, BESTPATH))
    {
      R2Local2Eca1Del (tuple);
    }
  if (IsInsertEvent (tuple, PATH))
    {
      R3eca (tuple);
    }
  if (IsDeleteEvent (tuple, PATH))
    {
      R3eca2 (tuple);
    }
  if (IsRecvEvent (tuple, R4_ECAPERIODIC))
    {
      R4_eca (tuple);
    }
}

void
Bgpblackhole::R1Eca0Ins (Ptr<Tuple> link)
{
  RAPIDNET_LOG_INFO ("R1Eca0Ins triggered");

  Ptr<Tuple> result = link;

  result->Assign (Assignor::New ("P1",
    FAppend::New (
      VarExpr::New ("link_attr1"))));

  result->Assign (Assignor::New ("P2",
    FAppend::New (
      VarExpr::New ("link_attr2"))));

  result->Assign (Assignor::New ("P",
    FConcat::New (
      VarExpr::New ("P1"),
      VarExpr::New ("P2"))));

  result = result->Project (
    PATH,
    strlist ("link_attr1",
      "link_attr2",
      "link_attr3",
      "P"),
    strlist ("path_attr1",
      "path_attr2",
      "path_attr3",
      "path_attr4"));

  Insert (result);
}

void
Bgpblackhole::R1Eca0Del (Ptr<Tuple> link)
{
  RAPIDNET_LOG_INFO ("R1Eca0Del triggered");

  Ptr<Tuple> result = link;

  result->Assign (Assignor::New ("P1",
    FAppend::New (
      VarExpr::New ("link_attr1"))));

  result->Assign (Assignor::New ("P2",
    FAppend::New (
      VarExpr::New ("link_attr2"))));

  result->Assign (Assignor::New ("P",
    FConcat::New (
      VarExpr::New ("P1"),
      VarExpr::New ("P2"))));

  result = result->Project (
    PATH,
    strlist ("link_attr1",
      "link_attr2",
      "link_attr3",
      "P"),
    strlist ("path_attr1",
      "path_attr2",
      "path_attr3",
      "path_attr4"));

  Delete (result);
}

void
Bgpblackhole::R2Local1Eca0RemoteIns (Ptr<Tuple> r2Local1r2linkZsend)
{
  RAPIDNET_LOG_INFO ("R2Local1Eca0RemoteIns triggered");

  Ptr<Tuple> result = r2Local1r2linkZsend;

  result = result->Project (
    R2LINKZ,
    strlist ("r2Local1r2linkZsend_attr1",
      "r2Local1r2linkZsend_attr2",
      "r2Local1r2linkZsend_attr3"),
    strlist ("r2linkZ_attr1",
      "r2linkZ_attr2",
      "r2linkZ_attr3"));

  Insert (result);
}

void
Bgpblackhole::R2Local1Eca0RemoteDel (Ptr<Tuple> r2linkZDelete)
{
  RAPIDNET_LOG_INFO ("R2Local1Eca0RemoteDel triggered");

  Ptr<Tuple> result = r2linkZDelete;

  result = result->Project (
    R2LINKZ,
    strlist ("r2linkZDelete_attr1",
      "r2linkZDelete_attr2",
      "r2linkZDelete_attr3"),
    strlist ("r2linkZ_attr1",
      "r2linkZ_attr2",
      "r2linkZ_attr3"));

  Delete (result);
}

void
Bgpblackhole::R2Local1Eca0Ins (Ptr<Tuple> link)
{
  RAPIDNET_LOG_INFO ("R2Local1Eca0Ins triggered");

  Ptr<Tuple> result = link;

  result = result->Project (
    R2LOCAL1R2LINKZSEND,
    strlist ("link_attr1",
      "link_attr2",
      "link_attr3",
      "link_attr2"),
    strlist ("r2Local1r2linkZsend_attr1",
      "r2Local1r2linkZsend_attr2",
      "r2Local1r2linkZsend_attr3",
      RN_DEST));

  Send (result);
}

void
Bgpblackhole::R2Local1Eca0Del (Ptr<Tuple> link)
{
  RAPIDNET_LOG_INFO ("R2Local1Eca0Del triggered");

  Ptr<Tuple> result = link;

  result = result->Project (
    R2LINKZDELETE,
    strlist ("link_attr1",
      "link_attr2",
      "link_attr3",
      "link_attr2"),
    strlist ("r2linkZDelete_attr1",
      "r2linkZDelete_attr2",
      "r2linkZDelete_attr3",
      RN_DEST));

  Send (result);
}

void
Bgpblackhole::R2Local2Eca0RemoteIns (Ptr<Tuple> r2Local2pathsend)
{
  RAPIDNET_LOG_INFO ("R2Local2Eca0RemoteIns triggered");

  Ptr<Tuple> result = r2Local2pathsend;

  result = result->Project (
    PATH,
    strlist ("r2Local2pathsend_attr1",
      "r2Local2pathsend_attr2",
      "r2Local2pathsend_attr3",
      "r2Local2pathsend_attr4"),
    strlist ("path_attr1",
      "path_attr2",
      "path_attr3",
      "path_attr4"));

  Insert (result);
}

void
Bgpblackhole::R2Local2Eca0RemoteDel (Ptr<Tuple> pathDelete)
{
  RAPIDNET_LOG_INFO ("R2Local2Eca0RemoteDel triggered");

  Ptr<Tuple> result = pathDelete;

  result = result->Project (
    PATH,
    strlist ("pathDelete_attr1",
      "pathDelete_attr2",
      "pathDelete_attr3",
      "pathDelete_attr4"),
    strlist ("path_attr1",
      "path_attr2",
      "path_attr3",
      "path_attr4"));

  Delete (result);
}

void
Bgpblackhole::R2Local2Eca0Ins (Ptr<Tuple> r2linkZ)
{
  RAPIDNET_LOG_INFO ("R2Local2Eca0Ins triggered");

  Ptr<RelationBase> result;

  result = GetRelation (BESTPATH)->Join (
    r2linkZ,
    strlist ("bestPath_attr1"),
    strlist ("r2linkZ_attr2"));

  result->Assign (Assignor::New ("C",
    Operation::New (RN_PLUS,
      VarExpr::New ("r2linkZ_attr3"),
      VarExpr::New ("bestPath_attr3"))));

  result->Assign (Assignor::New ("P1",
    FAppend::New (
      VarExpr::New ("r2linkZ_attr1"))));

  result->Assign (Assignor::New ("P",
    FConcat::New (
      VarExpr::New ("P1"),
      VarExpr::New ("bestPath_attr4"))));

  result = result->Select (Selector::New (
    Operation::New (RN_EQ,
      FMember::New (
        VarExpr::New ("bestPath_attr4"),
        VarExpr::New ("r2linkZ_attr1")),
      ValueExpr::New (Int32Value::New (0)))));

  result = result->Project (
    R2LOCAL2PATHSEND,
    strlist ("r2linkZ_attr1",
      "bestPath_attr2",
      "C",
      "P",
      "r2linkZ_attr1"),
    strlist ("r2Local2pathsend_attr1",
      "r2Local2pathsend_attr2",
      "r2Local2pathsend_attr3",
      "r2Local2pathsend_attr4",
      RN_DEST));

  Send (result);
}

void
Bgpblackhole::R2Local2Eca0Del (Ptr<Tuple> r2linkZ)
{
  RAPIDNET_LOG_INFO ("R2Local2Eca0Del triggered");

  Ptr<RelationBase> result;

  result = GetRelation (BESTPATH)->Join (
    r2linkZ,
    strlist ("bestPath_attr1"),
    strlist ("r2linkZ_attr2"));

  result->Assign (Assignor::New ("C",
    Operation::New (RN_PLUS,
      VarExpr::New ("r2linkZ_attr3"),
      VarExpr::New ("bestPath_attr3"))));

  result->Assign (Assignor::New ("P1",
    FAppend::New (
      VarExpr::New ("r2linkZ_attr1"))));

  result->Assign (Assignor::New ("P",
    FConcat::New (
      VarExpr::New ("P1"),
      VarExpr::New ("bestPath_attr4"))));

  result = result->Select (Selector::New (
    Operation::New (RN_EQ,
      FMember::New (
        VarExpr::New ("bestPath_attr4"),
        VarExpr::New ("r2linkZ_attr1")),
      ValueExpr::New (Int32Value::New (0)))));

  result = result->Project (
    PATHDELETE,
    strlist ("r2linkZ_attr1",
      "bestPath_attr2",
      "C",
      "P",
      "r2linkZ_attr1"),
    strlist ("pathDelete_attr1",
      "pathDelete_attr2",
      "pathDelete_attr3",
      "pathDelete_attr4",
      RN_DEST));

  Send (result);
}

void
Bgpblackhole::R2Local2Eca1Ins (Ptr<Tuple> bestPath)
{
  RAPIDNET_LOG_INFO ("R2Local2Eca1Ins triggered");

  Ptr<RelationBase> result;

  result = GetRelation (R2LINKZ)->Join (
    bestPath,
    strlist ("r2linkZ_attr2"),
    strlist ("bestPath_attr1"));

  result->Assign (Assignor::New ("C",
    Operation::New (RN_PLUS,
      VarExpr::New ("r2linkZ_attr3"),
      VarExpr::New ("bestPath_attr3"))));

  result->Assign (Assignor::New ("P1",
    FAppend::New (
      VarExpr::New ("r2linkZ_attr1"))));

  result->Assign (Assignor::New ("P",
    FConcat::New (
      VarExpr::New ("P1"),
      VarExpr::New ("bestPath_attr4"))));

  result = result->Select (Selector::New (
    Operation::New (RN_EQ,
      FMember::New (
        VarExpr::New ("bestPath_attr4"),
        VarExpr::New ("r2linkZ_attr1")),
      ValueExpr::New (Int32Value::New (0)))));

  result = result->Project (
    R2LOCAL2PATHSEND,
    strlist ("r2linkZ_attr1",
      "bestPath_attr2",
      "C",
      "P",
      "r2linkZ_attr1"),
    strlist ("r2Local2pathsend_attr1",
      "r2Local2pathsend_attr2",
      "r2Local2pathsend_attr3",
      "r2Local2pathsend_attr4",
      RN_DEST));

  Send (result);
}

void
Bgpblackhole::R2Local2Eca1Del (Ptr<Tuple> bestPath)
{
  RAPIDNET_LOG_INFO ("R2Local2Eca1Del triggered");

  Ptr<RelationBase> result;

  result = GetRelation (R2LINKZ)->Join (
    bestPath,
    strlist ("r2linkZ_attr2"),
    strlist ("bestPath_attr1"));

  result->Assign (Assignor::New ("C",
    Operation::New (RN_PLUS,
      VarExpr::New ("r2linkZ_attr3"),
      VarExpr::New ("bestPath_attr3"))));

  result->Assign (Assignor::New ("P1",
    FAppend::New (
      VarExpr::New ("r2linkZ_attr1"))));

  result->Assign (Assignor::New ("P",
    FConcat::New (
      VarExpr::New ("P1"),
      VarExpr::New ("bestPath_attr4"))));

  result = result->Select (Selector::New (
    Operation::New (RN_EQ,
      FMember::New (
        VarExpr::New ("bestPath_attr4"),
        VarExpr::New ("r2linkZ_attr1")),
      ValueExpr::New (Int32Value::New (0)))));

  result = result->Project (
    PATHDELETE,
    strlist ("r2linkZ_attr1",
      "bestPath_attr2",
      "C",
      "P",
      "r2linkZ_attr1"),
    strlist ("pathDelete_attr1",
      "pathDelete_attr2",
      "pathDelete_attr3",
      "pathDelete_attr4",
      RN_DEST));

  Send (result);
}

void
Bgpblackhole::R3eca (Ptr<Tuple> path)
{
  RAPIDNET_LOG_INFO ("R3eca triggered");

  Ptr<Tuple> result = path;

  result = result->Project (
    BESTPATH,
    strlist ("path_attr1",
      "path_attr2",
      "path_attr3",
      "path_attr4"),
    strlist ("bestPath_attr1",
      "bestPath_attr2",
      "bestPath_attr3",
      "bestPath_attr4"));

  m_aggr_bestpathMinC->Insert (result);
}

void
Bgpblackhole::R3eca2 (Ptr<Tuple> path)
{
  RAPIDNET_LOG_INFO ("R3eca2 triggered");

  Ptr<Tuple> result = path;

  result = result->Project (
    BESTPATH,
    strlist ("path_attr1",
      "path_attr2",
      "path_attr3",
      "path_attr4"),
    strlist ("bestPath_attr1",
      "bestPath_attr2",
      "bestPath_attr3",
      "bestPath_attr4"));

  m_aggr_bestpathMinC->Delete (result);
}

void
Bgpblackhole::R4_ecaperiodic ()
{
  RAPIDNET_LOG_INFO ("R4_ecaperiodic triggered");

  SendLocal (tuple (R4_ECAPERIODIC, attrlist (
    attr ("r4_ecaperiodic_attr1", Ipv4Value, GetAddress ()),
    attr ("r4_ecaperiodic_attr2", Int32Value, rand ()))));

  m_event_r4_ecaperiodic = Simulator::Schedule (Seconds(5),
    &Bgpblackhole::R4_ecaperiodic, this);
}

void
Bgpblackhole::R4_eca (Ptr<Tuple> r4_ecaperiodic)
{
  RAPIDNET_LOG_INFO ("R4_eca triggered");

  Ptr<RelationBase> result;

  result = GetRelation (PATH)->Join (
    r4_ecaperiodic,
    strlist ("path_attr1"),
    strlist ("r4_ecaperiodic_attr1"));

  result = result->Project (
    PACKET,
    strlist ("path_attr2",
      "r4_ecaperiodic_attr1",
      "path_attr2"),
    strlist ("packet_attr1",
      "packet_attr2",
      RN_DEST));

  Send (result);
}
