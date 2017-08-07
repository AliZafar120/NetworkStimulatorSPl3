#ifndef MYPROTOQUERY_HELPER_H
#define MYPROTOQUERY_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "myproto-query.h"

namespace ns3 {
namespace rapidnet {
namespace myprotoquery {

class MyprotoQuery;

class MyprotoQueryHelper: public RapidNetApplicationHelper
{
public:
  MyprotoQueryHelper ()
  {
    m_factory.SetTypeId (MyprotoQuery::GetTypeId ());
  }
  virtual ~MyprotoQueryHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<MyprotoQuery> ();
  }
};

} // namespace myprotoquery
} // namespace rapidnet
} // namespace ns3

#endif // MYPROTOQUERY_HELPER_H

