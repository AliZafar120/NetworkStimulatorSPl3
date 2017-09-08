/* A RapidNet application. Generated by RapidNet compiler. */

#ifndef TWOHOPS_H
#define TWOHOPS_H

#include <string>
#include <iostream>
#include "ns3/ptr.h"
#include "ns3/event-id.h"
#include "ns3/ipv4-address.h"
#include "ns3/rapidnet-header.h"
#include "ns3/relation-base.h"
#include "ns3/database.h"
#include "ns3/rapidnet-application-base.h"
#include "ns3/aggregator.h"
#include "ns3/aggwrap.h"

using namespace std;
using namespace ns3;

namespace ns3 {

class Socket;
class Packet;

namespace rapidnet {
namespace twohops {

class Twohops : public RapidNetApplicationBase
{
public:
  static const string LINK;
  static const string ONEHOP;
  static const string R2LOCAL1R2LINKMIDSEND;
  static const string R2LINKMID;
  static const string R2LINKMIDDELETE;
  static const string TWOHOPS;

  static TypeId GetTypeId (void);

  Twohops ();

  virtual ~Twohops ();

protected:

  virtual void DoDispose (void);

  virtual void StartApplication (void);

  virtual void StopApplication (void);

  virtual void InitDatabase (void);

  virtual void DemuxRecv (Ptr<Tuple> tuple);

  virtual void R1Eca0Ins (Ptr<Tuple> link);

  virtual void R1Eca0Del (Ptr<Tuple> link);

  virtual void R2Local1Eca0RemoteIns (Ptr<Tuple> r2Local1r2linkMidsend);

  virtual void R2Local1Eca0RemoteDel (Ptr<Tuple> r2linkMidDelete);

  virtual void R2Local1Eca0Ins (Ptr<Tuple> link);

  virtual void R2Local1Eca0Del (Ptr<Tuple> link);

  virtual void R2Local2Eca0Ins (Ptr<Tuple> r2linkMid);

  virtual void R2Local2Eca1Ins (Ptr<Tuple> onehop);

};

} // namespace twohops
} // namespace rapidnet
} // namespace ns3

#endif // TWOHOPS_H
