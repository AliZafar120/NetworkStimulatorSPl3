#ifndef FOURHOPS_HELPER_H
#define FOURHOPS_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "fourhops.h"

namespace ns3 {
namespace rapidnet {
namespace fourhops {

class Fourhops;

class FourhopsHelper: public RapidNetApplicationHelper
{
public:
  FourhopsHelper ()
  {
    m_factory.SetTypeId (Fourhops::GetTypeId ());
  }
  virtual ~FourhopsHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Fourhops> ();
  }
};

} // namespace fourhops
} // namespace rapidnet
} // namespace ns3

#endif // FOURHOPS_HELPER_H

