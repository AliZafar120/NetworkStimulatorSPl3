#ifndef MYPROTO2_HELPER_H
#define MYPROTO2_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "myproto2.h"

namespace ns3 {
namespace rapidnet {
namespace myproto2 {

class Myproto2;

class Myproto2Helper: public RapidNetApplicationHelper
{
public:
  Myproto2Helper ()
  {
    m_factory.SetTypeId (Myproto2::GetTypeId ());
  }
  virtual ~Myproto2Helper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Myproto2> ();
  }
};

} // namespace myproto2
} // namespace rapidnet
} // namespace ns3

#endif // MYPROTO2_HELPER_H

