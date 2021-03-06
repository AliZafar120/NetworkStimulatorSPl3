/* Copyright (C) 1991-2014 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses ISO/IEC 10646 (2nd ed., published 2011-03-15) /
   Unicode 6.0.  */
/* We do not support C11 <threads.h>.  */
materialize(link,infinity,infinity,keys(1,2)).
materialize(path,infinity,infinity,keys(4:list)).
materialize(bestPath,infinity,infinity,keys(2)).
/* Rules */
r1 path(@X,Y,C,P) :- link(@X,Y,C),
        P1:=f_append(X),
        P2:=f_append(Y),
        P:=f_concat(P1,P2).
r2 path(@X,Y,C,P) :- link(@X,Z,C1),
       bestPath(@Z,Y,C2,P2),
       C:=C1+C2,
       f_member(P2,X)==0,
       P1:=f_append(X),
       P:=f_concat(P1,P2).
r3 bestPath(@X,Y,a_MIN<C>, P) :- path(@X,Y,C,P).
r4 packet(@Next,Src):-
    periodic(@Src,E,5),
   path(@Src,Next,C,P).
// When a node receive a ePing event from its neighbor, it sent back a ePong message
//r5  rpacket(@Src):-
//    packet(@Src,Next).
//'applications/mytest',
