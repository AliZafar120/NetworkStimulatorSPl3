#ifndef BOGONLIST_HELPER_H
#define BOGONLIST_HELPER_H

#include "ns3/rapidnet-application-helper.h"
#include "bogonlist.h"

namespace ns3 {
namespace rapidnet {
namespace bogonlist {

class Bogonlist;

class BogonlistHelper: public RapidNetApplicationHelper
{
public:
  BogonlistHelper ()
  {
    m_factory.SetTypeId (Bogonlist::GetTypeId ());
  }
  virtual ~BogonlistHelper ()
  {
  }

protected:
  Ptr<RapidNetApplicationBase> CreateNewApplication ()
  {
    return m_factory.Create<Bogonlist> ();
  }
};

} // namespace bogonlist
} // namespace rapidnet
} // namespace ns3

#endif // BOGONLIST_HELPER_H

