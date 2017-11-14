#ifndef BGPBLACKHOLE_HELPER_H
#define BGPBLACKHOLE_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "bgpblackhole.h"

namespace ns3 {
namespace rapidnet {
namespace bgpblackhole {

class Bgpblackhole;

class BgpblackholeHelper: public RapidNetApplicationHelper
{
public:
  BgpblackholeHelper ()
  {
    m_factory.SetTypeId (Bgpblackhole::GetTypeId ());
  }
  virtual ~BgpblackholeHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Bgpblackhole> ();
  }
};

} // namespace bgpblackhole
} // namespace rapidnet
} // namespace ns3

#endif // BGPBLACKHOLE_HELPER_H

