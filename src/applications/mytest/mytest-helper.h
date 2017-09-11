#ifndef MYTEST_HELPER_H
#define MYTEST_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "mytest.h"

namespace ns3 {
namespace rapidnet {
namespace mytest {

class Mytest;

class MytestHelper: public RapidNetApplicationHelper
{
public:
  MytestHelper ()
  {
    m_factory.SetTypeId (Mytest::GetTypeId ());
  }
  virtual ~MytestHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Mytest> ();
  }
};

} // namespace mytest
} // namespace rapidnet
} // namespace ns3

#endif // MYTEST_HELPER_H

