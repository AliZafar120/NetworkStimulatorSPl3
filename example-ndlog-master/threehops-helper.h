#ifndef THREEHOPS_HELPER_H
#define THREEHOPS_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "threehops.h"

namespace ns3 {
namespace rapidnet {
namespace threehops {

class Threehops;

class ThreehopsHelper: public RapidNetApplicationHelper
{
public:
  ThreehopsHelper ()
  {
    m_factory.SetTypeId (Threehops::GetTypeId ());
  }
  virtual ~ThreehopsHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Threehops> ();
  }
};

} // namespace threehops
} // namespace rapidnet
} // namespace ns3

#endif // THREEHOPS_HELPER_H

