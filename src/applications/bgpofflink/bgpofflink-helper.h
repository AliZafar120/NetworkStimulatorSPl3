#ifndef BGPOFFLINK_HELPER_H
#define BGPOFFLINK_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "bgpofflink.h"

namespace ns3 {
namespace rapidnet {
namespace bgpofflink {

class Bgpofflink;

class BgpofflinkHelper: public RapidNetApplicationHelper
{
public:
  BgpofflinkHelper ()
  {
    m_factory.SetTypeId (Bgpofflink::GetTypeId ());
  }
  virtual ~BgpofflinkHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Bgpofflink> ();
  }
};

} // namespace bgpofflink
} // namespace rapidnet
} // namespace ns3

#endif // BGPOFFLINK_HELPER_H

