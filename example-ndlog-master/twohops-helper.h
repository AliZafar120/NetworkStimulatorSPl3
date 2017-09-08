#ifndef TWOHOPS_HELPER_H
#define TWOHOPS_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "twohops.h"

namespace ns3 {
namespace rapidnet {
namespace twohops {

class Twohops;

class TwohopsHelper: public RapidNetApplicationHelper
{
public:
  TwohopsHelper ()
  {
    m_factory.SetTypeId (Twohops::GetTypeId ());
  }
  virtual ~TwohopsHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Twohops> ();
  }
};

} // namespace twohops
} // namespace rapidnet
} // namespace ns3

#endif // TWOHOPS_HELPER_H

