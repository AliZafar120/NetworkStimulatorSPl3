/* -*-  Mode: C++; c-file-style: "gnu"; indent-tabs-mode:nil; -*- */
/*
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation;
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

#include "tcp-transport-socket-factory-impl.h"
#include "tcp-transport-socket-impl.h"
#include "ns3/socket.h"
#include "ns3/assert.h"


namespace ns3 {

TcpTransportSocketFactoryImpl::TcpTransportSocketFactoryImpl ()
{
}

TcpTransportSocketFactoryImpl::~TcpTransportSocketFactoryImpl ()
{
}

void
TcpTransportSocketFactoryImpl::DoDispose ()
{
}

Ptr<Socket>
TcpTransportSocketFactoryImpl::CreateSocket (void)
{
  Ptr<TcpTransportSocketImpl> socket = CreateObject<TcpTransportSocketImpl> ();
  socket->SetNode (m_node);
  return socket;
}

void
TcpTransportSocketFactoryImpl::SetNode (Ptr<Node> node)
{
  m_node = node;
}
} // namespace ns3

