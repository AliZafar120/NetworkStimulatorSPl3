materialize(link,infinity,infinity,keys(2)).
materialize(route,infinity,infinity,keys(2,4:list)).
materialize(prefixs,infinity,infinity,keys(2)).
materialize(bestRoute,infinity,infinity,keys(2)).
materialize(publicKeys,infinity,infinity,keys(2,3:str)).
materialize(privateKeys,infinity,1,keys(2:str)).

r1 route(@N, Prefix, Cost, Path, SigList, TimeList) :-
        prefixs(@N,Prefix),
        List := f_empty(),
        Path := f_prepend(N,List),
        SigList := f_empty(),
	TimeList := f_empty(),
        Cost := 0.

r2 bestRoute(@N, Prefix, Cost, Path, SigList, TimeList) :-
        route(@N, Prefix, Cost, Path, SigList, TimeList).

r3 verifyPath(@N, Neighbor, Prefix, PathToVerify, SigList, TimeList, OrigPath, OrigSigList, OrigTimeList) :-
        advertisements(@N, Neighbor, Prefix, ReceivedPath, SigList, TimeList),
	link(@N, Neighbor),
	PathToVerify := f_prepend(N, ReceivedPath),
	OrigPath := PathToVerify,
	OrigSigList := SigList,
	OrigTimeList := TimeList,
        f_member(ReceivedPath,N) == 0,
        Nei := f_first(ReceivedPath),
        Neighbor == Nei.

r4 verifyPath(@N, Neighbor, Prefix, PathTemp, SigList1, NewTimeList, OrigPath, OrigSigList, OrigTimeList) :-
        verifyPath(@N, Neighbor, Prefix, PathToVerify, SigList, TimeList, OrigPath, OrigSigList, OrigTimeList),
        f_size(SigList) > 0,
        f_size(PathToVerify) > 1,
	f_size(TimeList) > 0,
        PathTemp := f_removeFirst(PathToVerify),
        Node2 := f_first(PathTemp),
        SigInfo := f_first(SigList),
	Timing := f_first(TimeList),
	RouteToVerify := f_prepend(Prefix, PathToVerify),
	InfoToVerify := f_append2(Timing, RouteToVerify),
        publicKeys(@N, Node2, PubKey),
        f_verify(InfoToVerify, SigInfo, PubKey) == 1,
        SigList1 := f_removeFirst(SigList),
	NewTimeList := f_removeFirst(TimeList).

r5 route(@N,Prefix, Cost, OrigPath, OrigSigList, OrigTimeList) :-
        verifyPath(@N, Node, Prefix, PathToVerify, SigList, TimeList, OrigPath, OrigSigList, OrigTimeList),
        f_size(SigList) == 0,
	f_size(TimeList) == 0,
        f_size(PathToVerify) == 1,
        Cost:= f_size(OrigPath) - 1.

r6 signature(@N, RouteToSign, Timing, Sig) :-
   	bestRoute(@N, Prefix, Cost, BestPath, SigList, TimeList),
        link(@N,Neighbor),
        privateKeys(@N, PrivateKey),
        PathToSign := f_prepend(Neighbor, BestPath),
	RouteToSign := f_prepend(Prefix, PathToSign),
	Timing := f_now(),
	InfoToSign := f_append2(Timing, RouteToSign),
        Sig := f_sign(InfoToSign, PrivateKey).

r7 advertisements(@Neighbor, N, Prefix, BestPath, NewSigList, NewTimeList) :-
        bestRoute(@N, Prefix, Cost, BestPath, SigList, TimeList),
        link(@N,Neighbor),
        PathToSign := f_prepend(Neighbor, BestPath),
	signature(@N, RouteToSign, Timing, Sig),
	RouteToSign == f_prepend(Prefix, PathToSign),
        NewSigList := f_prepend(Sig, SigList),
	NewTimeList := f_prepend(Timing, TimeList).


