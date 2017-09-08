#ifndef ONEHOP_HELPER_H
#define ONEHOP_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "onehop.h"

namespace ns3 {
namespace rapidnet {
namespace onehop {

class Onehop;

class OnehopHelper: public RapidNetApplicationHelper
{
public:
  OnehopHelper ()
  {
    m_factory.SetTypeId (Onehop::GetTypeId ());
  }
  virtual ~OnehopHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Onehop> ();
  }
};

} // namespace onehop
} // namespace rapidnet
} // namespace ns3

#endif // ONEHOP_HELPER_H

